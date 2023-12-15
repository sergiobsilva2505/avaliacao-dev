package br.com.sbs.avaliacaodevspring.dominio.realizado.controller;

import br.com.sbs.avaliacaodevspring.dominio.exame.service.ExameService;
import br.com.sbs.avaliacaodevspring.dominio.realizado.dto.ExameFuncionarioView;
import br.com.sbs.avaliacaodevspring.dominio.realizado.dto.NewExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.realizado.dto.UpdateExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.realizado.entity.ExameFuncionario;
import br.com.sbs.avaliacaodevspring.dominio.realizado.service.ExameFuncionarioService;
import br.com.sbs.avaliacaodevspring.dominio.realizado.validator.NewExameFuncionarioFormValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/exames-funcionarios")
public class ExameFuncionarioController {

    private final ExameFuncionarioService exameFuncionarioService;
    private final NewExameFuncionarioFormValidator newExameFuncionarioFormValidator;
    private final ExameService exameService;

    public ExameFuncionarioController(ExameFuncionarioService exameFuncionarioService, NewExameFuncionarioFormValidator newExameFuncionarioFormValidator, ExameService exameService) {
        this.exameFuncionarioService = exameFuncionarioService;
        this.newExameFuncionarioFormValidator = newExameFuncionarioFormValidator;
        this.exameService = exameService;
    }

    @InitBinder("newExameFuncionarioForm")
    void initBinderNewExameFuncionarioForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newExameFuncionarioFormValidator);
    }

    @GetMapping("/form")
    public String showForm(NewExameFuncionarioForm newExameFuncionarioForm, Model model) {
        model.addAttribute("newExameFuncionarioForm", newExameFuncionarioForm);
        model.addAttribute("exames", exameService.findAll());

        return "realizado/realizadoForm";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute NewExameFuncionarioForm newExameFuncionarioForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("messageError", "O funcionário já realizou esse exame");
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
    public String showExameRealizado(@PathVariable Long id, UpdateExameFuncionarioForm updateExameFuncionarioForm, Model model) {
        ExameFuncionario exameFuncionario = exameFuncionarioService.findById(id);
        model.addAttribute("exameFuncionarioView", new ExameFuncionarioView(exameFuncionario));

        return "realizado/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute UpdateExameFuncionarioForm updateExameFuncionarioForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showExameRealizado(id, updateExameFuncionarioForm, model);
        }
        exameFuncionarioService.update(id, updateExameFuncionarioForm);

        return "redirect:/exames-funcionarios";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        exameFuncionarioService.delete(id);

        return "redirect:/exames-funcionarios";
    }
}
