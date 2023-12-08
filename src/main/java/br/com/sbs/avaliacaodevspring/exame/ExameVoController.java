package br.com.sbs.avaliacaodevspring.exame;

import br.com.sbs.avaliacaodevspring.exame.dto.ExameVoView;
import br.com.sbs.avaliacaodevspring.exame.dto.NewExameVoForm;
import br.com.sbs.avaliacaodevspring.filter.OpcoesComboBuscarExames;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exames")
public class ExameVoController {

    private final ExameVoService exameVoService;

    public ExameVoController(ExameVoService exameVoService) {
        this.exameVoService = exameVoService;
    }

    @GetMapping("/form")
    public String showForm(@ModelAttribute NewExameVoForm newExameVoForm, Model model) {
        model.addAttribute("newExameVoForm", newExameVoForm);

        return "exameVo/newExameForm";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("opcoesComBuscarExames", OpcoesComboBuscarExames.values());
        model.addAttribute("exames", exameVoService.findall());

        return "exameVo/list";
    }

    @PostMapping
    public String save(@Valid NewExameVoForm newExameVoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("newExameVoForm", newExameVoForm);
            return showForm(newExameVoForm, model);
        }
        exameVoService.save(newExameVoForm);

        return "redirect:/exames";
    }

}
