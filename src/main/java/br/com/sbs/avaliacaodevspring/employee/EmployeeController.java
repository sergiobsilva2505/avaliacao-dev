package br.com.sbs.avaliacaodevspring.employee;

import br.com.sbs.avaliacaodevspring.employee.dto.EmployeeView;
import br.com.sbs.avaliacaodevspring.employee.dto.NewEmployeeForm;
import br.com.sbs.avaliacaodevspring.employee.dto.UpdateEmployeeForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/funcionarios")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String findAll(@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, Model model) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
        Page<EmployeeView> employees = employeeService.findAll(pageRequest);
        model.addAttribute("employees", employees);

        int totalPages = employees.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

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
        EmployeeView employeeView = employeeService.findById(id);
        model.addAttribute("employeeView", employeeView);

        return "employee/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid UpdateEmployeeForm updateEmployeeForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showEmployee(id, updateEmployeeForm, model);
        }

        EmployeeView employee = employeeService.update(id, updateEmployeeForm);
        model.addAttribute("funcionarioView", employee);

        return "redirect:/funcionarios";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.deleteById(id);

        return "redirect:/funcionarios";
    }

}
