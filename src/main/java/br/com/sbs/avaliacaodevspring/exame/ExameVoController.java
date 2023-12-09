package br.com.sbs.avaliacaodevspring.exame;

import br.com.sbs.avaliacaodevspring.exame.dto.ExameVoDTO;
import br.com.sbs.avaliacaodevspring.filter.OpcoesComboBuscarExames;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exames")
public class ExameVoController {

    private final ExameVoService exameVoService;

    public ExameVoController(ExameVoService exameVoService) {
        this.exameVoService = exameVoService;
    }

    @GetMapping("/form")
    public String showForm(ExameVoDTO exameVoDTO, Model model) {
        model.addAttribute("exameVoDTO", exameVoDTO);

        return "exameVo/newForm";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute ExameVoDTO exameVoDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exameVoDTO", exameVoDTO);
            return "exameVo/newForm";
        }
        exameVoService.save(exameVoDTO);

        return "redirect:/exames";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("opcoesComBuscarExames", OpcoesComboBuscarExames.values());
        model.addAttribute("exames", exameVoService.findall());

        return "exameVo/list";
    }

    @GetMapping("/{id}")
    public String showExame(@PathVariable Long id, ExameVoDTO exameVoDTO, Model model) {
        ExameVoDTO exameDTO = exameVoService.findById(id);
        model.addAttribute("exameVoDTO", exameDTO);

        return "exameVo/updateForm";
    }

    @PutMapping
    public String update(@ModelAttribute @Valid ExameVoDTO exameVoDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exameVoDTO", exameVoDTO);
            return "exameVo/updateForm";
        }

        exameVoService.update(exameVoDTO);

        return "redirect:/exames";
    }

}
