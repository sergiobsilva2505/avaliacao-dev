package br.com.sbs.avaliacaodevspring.employee;

import br.com.sbs.avaliacaodevspring.employee.dto.FuncionarioView;
import br.com.sbs.avaliacaodevspring.employee.dto.NewFuncionarioForm;
import br.com.sbs.avaliacaodevspring.employee.dto.UpdateFuncionarioForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public String findAll(Model model) {
        Collection<FuncionarioView> funcionarios = funcionarioService.findAll();
        model.addAttribute("funcionarios", funcionarios);

        return "funcionario/list";
    }

    @GetMapping("/form")
    public String showForm(NewFuncionarioForm newFuncionarioForm, Model model) {
        model.addAttribute("newFuncionarioForm", newFuncionarioForm);

        return "funcionario/newForm";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute NewFuncionarioForm newFuncionarioForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(newFuncionarioForm, model);
        }
        funcionarioService.save(newFuncionarioForm);

        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}")
    public String showFuncionario(@PathVariable Long id, UpdateFuncionarioForm updateFuncionarioForm, Model model) {
        Funcionario funcionario = funcionarioService.findById(id);
        model.addAttribute("funcionarioView", new FuncionarioView(funcionario));

        return "funcionario/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid UpdateFuncionarioForm updateFuncionarioForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showFuncionario(id, updateFuncionarioForm, model);
        }

        Funcionario funcionario = funcionarioService.update(id, updateFuncionarioForm);
        model.addAttribute("funcionarioView", new FuncionarioView(funcionario));

        return "redirect:/funcionarios";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, boolean isRequestedByApi) {
        funcionarioService.deleteById(id, isRequestedByApi);

        return "redirect:/funcionarios";
    }

}
