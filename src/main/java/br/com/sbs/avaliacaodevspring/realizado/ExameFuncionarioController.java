package br.com.sbs.avaliacaodevspring.realizado;

import br.com.sbs.avaliacaodevspring.realizado.dto.ExameFuncionarioView;
import br.com.sbs.avaliacaodevspring.realizado.dto.NewExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.realizado.dto.UpdateExameFuncionarioForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/exames-funcionarios")
public class ExameFuncionarioController {

    private final ExameFuncionarioService exameFuncionarioService;

    public ExameFuncionarioController(ExameFuncionarioService exameFuncionarioService) {
        this.exameFuncionarioService = exameFuncionarioService;
    }

    @GetMapping("/form")
    public String showForm(NewExameFuncionarioForm newExameFuncionarioForm, Model model) {
        model.addAttribute("newExameFuncionarioForm", newExameFuncionarioForm);

        return "realizado/realizadoForm";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute NewExameFuncionarioForm newExameFuncionarioForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(newExameFuncionarioForm, model);
        }

        exameFuncionarioService.save(newExameFuncionarioForm);

        return "redirect:/exames-funcionarios";
    }

    @GetMapping
    public String findAll(Model model) {
        Collection<ExameFuncionarioView> exameFuncionarioViews = exameFuncionarioService.findAll();
        model.addAttribute("exameFuncionarioViews", exameFuncionarioViews);

        return "realizado/list";
    }

    @GetMapping("/{id}")
    public String showExameRealizado(@PathVariable Long id, Model model) {
        ExameFuncionarioView exameFuncionarioView = exameFuncionarioService.findById(id);
        model.addAttribute("exameFuncionarioView", exameFuncionarioView);

        return "realizado/updateForm";
    }
}
