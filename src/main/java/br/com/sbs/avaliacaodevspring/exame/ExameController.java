package br.com.sbs.avaliacaodevspring.exame;

import br.com.sbs.avaliacaodevspring.exame.dto.ExameView;
import br.com.sbs.avaliacaodevspring.exame.dto.NewExameForm;
import br.com.sbs.avaliacaodevspring.exame.dto.UpdateExameForm;
import br.com.sbs.avaliacaodevspring.filter.OpcoesComboBuscarExames;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exames")
public class ExameController {

    private final ExameService exameService;

    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }

    @GetMapping("/form")
    public String showForm(NewExameForm newExameForm, Model model) {
        model.addAttribute("exameVoDTO", newExameForm);

        return "exameVo/newForm";
    }

    @PostMapping
    public String save(@Valid NewExameForm newExameForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newExameForm", newExameForm);
            return "exameVo/newForm";
        }
        exameService.save(newExameForm);

        return "redirect:/exames";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("opcoesComBuscarExames", OpcoesComboBuscarExames.values());
        model.addAttribute("exames", exameService.findall());

        return "exameVo/list";
    }

    @GetMapping("/{id}")
    public String showExame(@PathVariable Long id, UpdateExameForm updateExameForm, Model model) {
        ExameView exameView = exameService.findById(id);
        model.addAttribute("exameView", exameView);

        return "exameVo/updateForm";
    }

    @PutMapping
    public String update(@Valid UpdateExameForm updateExameForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("updateExameForm", updateExameForm);
            return "exameVo/updateForm";
        }

        exameService.update(updateExameForm);

        return "redirect:/exames";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        exameService.deleteById(id);

        return "redirect:/exames";
    }

//    TODO confirm modal controller in list html

}
