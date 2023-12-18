package br.com.sbs.avaliacaodevspring.employee_exam;

import br.com.sbs.avaliacaodevspring.employee_exam.dto.EmployeeExamView;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.NewEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.UpdateEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.employee_exam.validator.NewEmployeeExamFormValidator;
import br.com.sbs.avaliacaodevspring.exam.ExamService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/exames-funcionarios")
public class EmployeeExamController {

    private final EmployeeExamService employeeExamService;
    private final NewEmployeeExamFormValidator newEmployeeExamFormValidator;
    private final ExamService examService;

    public EmployeeExamController(EmployeeExamService employeeExamService, NewEmployeeExamFormValidator newEmployeeExamFormValidator, ExamService examService) {
        this.employeeExamService = employeeExamService;
        this.newEmployeeExamFormValidator = newEmployeeExamFormValidator;
        this.examService = examService;
    }

    @InitBinder("newEmployeeExamForm")
    void initBinderNewEmployeeExamFormForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newEmployeeExamFormValidator);
    }

    @GetMapping("/form")
    public String showForm(NewEmployeeExamForm newEmployeeExamForm, Model model) {
        model.addAttribute("newEmployeeExamForm", newEmployeeExamForm);
        model.addAttribute("exam", examService.findAll());

        return "exam_employee/newForm";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute NewEmployeeExamForm newEmployeeExamForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(newEmployeeExamForm, model);
        }

        employeeExamService.save(newEmployeeExamForm);

        return "redirect:/exames-funcionarios";
    }

    @GetMapping
    public String findAll(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, Model model) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
        Page<EmployeeExamView> employeeExamViews = employeeExamService.findAll(pageRequest);
        model.addAttribute("employeeExamViews", employeeExamViews);

        int totalPages = employeeExamViews.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "exam_employee/list";
    }

    @GetMapping("/{id}")
    public String showExamEmployee(@PathVariable Long id, UpdateEmployeeExamForm updateEmployeeExamForm, Model model) {
        EmployeeExamView employeeExamView = employeeExamService.findById(id);
        model.addAttribute("employeeExamView", employeeExamView);

        return "exam_employee/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute UpdateEmployeeExamForm updateEmployeeExamForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showExamEmployee(id, updateEmployeeExamForm, model);
        }
        employeeExamService.update(id, updateEmployeeExamForm);

        return "redirect:/exames-funcionarios";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        employeeExamService.deleteById(id);

        return "redirect:/exames-funcionarios";
    }
}
