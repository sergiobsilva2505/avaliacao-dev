package br.com.sbs.avaliacaodevspring.exam;

import br.com.sbs.avaliacaodevspring.exam.dto.ExamView;
import br.com.sbs.avaliacaodevspring.exam.dto.NewExamForm;
import br.com.sbs.avaliacaodevspring.exam.dto.UpdateExamForm;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/exames")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/form")
    public String showForm(NewExamForm newExamForm, Model model) {
        model.addAttribute("newExamForm", newExamForm);

        return "exam/newForm";
    }

    @PostMapping
    public String save(@Valid NewExamForm newExamForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(newExamForm, model);
        }
        examService.save(newExamForm);

        return "redirect:/exames";
    }

    @GetMapping
    public String findAll(@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, Model model) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
        Page<ExamView> exams = examService.findAll(pageRequest);
        model.addAttribute("exams", exams);

        int totalPages = exams.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "exam/list";
    }

    @GetMapping("/{id}")
    public String showExam(@PathVariable Long id, UpdateExamForm updateExamForm, Model model) {
        ExamView examView = examService.findById(id);
        model.addAttribute("exameView", examView);

        return "exam/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid UpdateExamForm updateExamForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showExam(id, updateExamForm, model);
        }
        examService.update(id, updateExamForm);

        return "redirect:/exames";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        examService.deleteById(id);

        return "redirect:/exames";
    }

}
