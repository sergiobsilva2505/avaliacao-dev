package br.com.sbs.avaliacaodevspring.exam;

import br.com.sbs.avaliacaodevspring.exam.dto.ExamView;
import br.com.sbs.avaliacaodevspring.exam.dto.NewExamForm;
import br.com.sbs.avaliacaodevspring.exam.dto.UpdateExamForm;
import br.com.sbs.avaliacaodevspring.filter.OpcoesComboBuscarExames;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String findAll(Model model) {
        model.addAttribute("exams", examService.findAll());

        return "exam/list";
    }

    @GetMapping("/{id}")
    public String showExame(@PathVariable Long id, UpdateExamForm updateExamForm, Model model) {
        Exam exam = examService.findById(id);
        model.addAttribute("exameView", new ExamView(exam));

        return "exam/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid UpdateExamForm updateExamForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showExame(id, updateExamForm, model);
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
