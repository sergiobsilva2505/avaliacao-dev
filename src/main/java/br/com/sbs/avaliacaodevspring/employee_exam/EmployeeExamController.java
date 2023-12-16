package br.com.sbs.avaliacaodevspring.employee_exam;

import br.com.sbs.avaliacaodevspring.exam.ExamService;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.EmployeeExamView;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.NewEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.UpdateEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.employee_exam.validator.NewEmployeeExamFormValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @InitBinder("newExameFuncionarioForm")
    void initBinderNewExameFuncionarioForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newEmployeeExamFormValidator);
    }

    @GetMapping("/form")
    public String showForm(NewEmployeeExamForm newEmployeeExamForm, Model model) {
        model.addAttribute("newEmployeeExamForm", newEmployeeExamForm);
        model.addAttribute("exam", examService.findAll());

        return "realizado/realizadoForm";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute NewEmployeeExamForm newEmployeeExamForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("messageError", "O funcionário já realizou esse exame");
            return showForm(newEmployeeExamForm, model);
        }

        employeeExamService.save(newEmployeeExamForm);

        return "redirect:/exames-funcionarios";
    }

    @GetMapping
    public String findAll(Model model) {
        Collection<EmployeeExamView> employeeExamViews = employeeExamService.findAll();
        model.addAttribute("employeeExamViews", employeeExamViews);

        return "realizado/list";
    }

    @GetMapping("/{id}")
    public String showExameRealizado(@PathVariable Long id, UpdateEmployeeExamForm updateEmployeeExamForm, Model model) {
        EmployeeExam employeeExam = employeeExamService.findById(id);
        model.addAttribute("employeeExamView", new EmployeeExamView(employeeExam));

        return "realizado/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute UpdateEmployeeExamForm updateEmployeeExamForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showExameRealizado(id, updateEmployeeExamForm, model);
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
