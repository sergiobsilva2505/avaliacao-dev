package br.com.sbs.avaliacaodevspring.employee;

import br.com.sbs.avaliacaodevspring.employee.dto.EmployeeView;
import br.com.sbs.avaliacaodevspring.employee.dto.NewEmployeeForm;
import br.com.sbs.avaliacaodevspring.employee.dto.UpdateEmployeeForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/funcionarios")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String findAll(Model model) {
        Collection<EmployeeView> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "employee/list";
    }

    @GetMapping("/form")
    public String showForm(NewEmployeeForm newEmployeeForm, Model model) {
        model.addAttribute("newEmployeeForm", newEmployeeForm);

        return "employee/newForm";
    }

    @PostMapping
    public String save(@Valid NewEmployeeForm newEmployeeForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(newEmployeeForm, model);
        }
        employeeService.save(newEmployeeForm);

        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}")
    public String showEmployee(@PathVariable Long id, UpdateEmployeeForm updateEmployeeForm, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employeeView", new EmployeeView(employee));

        return "employee/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid UpdateEmployeeForm updateEmployeeForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showEmployee(id, updateEmployeeForm, model);
        }

        Employee employee = employeeService.update(id, updateEmployeeForm);
        model.addAttribute("funcionarioView", new EmployeeView(employee));

        return "redirect:/funcionarios";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, boolean isRequestedByApi) {
        employeeService.deleteById(id);

        return "redirect:/funcionarios";
    }

}
