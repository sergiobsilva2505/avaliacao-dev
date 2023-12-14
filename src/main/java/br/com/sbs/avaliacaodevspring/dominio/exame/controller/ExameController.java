package br.com.sbs.avaliacaodevspring.dominio.exame.controller;

import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import br.com.sbs.avaliacaodevspring.dominio.exame.service.ExameService;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.ExameView;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.NewExameForm;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.UpdateExameForm;
import br.com.sbs.avaliacaodevspring.filter.OpcoesComboBuscarExames;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exames")
public class ExameController {

    private static final boolean IS_API_REQUEST = false;
    private final ExameService exameService;

    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }

    @GetMapping("/form")
    public String showForm(NewExameForm newExameForm, Model model) {
        model.addAttribute("newExameForm", newExameForm);

        return "exame/newForm";
    }

    @PostMapping
    public String save(@Valid NewExameForm newExameForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(newExameForm, model);
        }
        exameService.save(newExameForm);

        return "redirect:/exames";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("opcoesComBuscarExames", OpcoesComboBuscarExames.values());
        model.addAttribute("exames", exameService.findAll());

        return "exame/list";
    }

    @GetMapping("/{id}")
    public String showExame(@PathVariable Long id, UpdateExameForm updateExameForm, Model model) {
        Exame exame = exameService.findById(id, IS_API_REQUEST);
        model.addAttribute("exameView", new ExameView(exame));

        return "exame/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid UpdateExameForm updateExameForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showExame(id, updateExameForm, model);
        }
        exameService.update(id, updateExameForm, IS_API_REQUEST);

        return "redirect:/exames";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        exameService.deleteById(id);

        return "redirect:/exames";
    }

}
