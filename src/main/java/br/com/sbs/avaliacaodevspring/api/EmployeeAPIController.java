package br.com.sbs.avaliacaodevspring.api;

import br.com.sbs.avaliacaodevspring.employee.EmployeeService;
import br.com.sbs.avaliacaodevspring.employee.dto.EmployeeView;
import br.com.sbs.avaliacaodevspring.employee.dto.NewEmployeeForm;
import br.com.sbs.avaliacaodevspring.employee.dto.UpdateEmployeeForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/funcionarios")
public class EmployeeAPIController {

    private final EmployeeService employeeService;

    public EmployeeAPIController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    ResponseEntity<EmployeeView> save(@Valid @RequestBody NewEmployeeForm newEmployeeForm) {
        EmployeeView employeeView = employeeService.save(newEmployeeForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeView.rowid()).toUri();

        return ResponseEntity.created(uri).body(employeeView);
    }

    @GetMapping
    ResponseEntity<Collection<EmployeeView>> findAll() {
        Collection<EmployeeView> employees = employeeService.findAll();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeView> findById(@PathVariable Long id) {
        EmployeeView employee = employeeService.findById(id);

        return ResponseEntity.ok().body(employee);
    }

    @PutMapping("/{id}")
    ResponseEntity<EmployeeView> update(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeForm updateEmployeeForm) {
        EmployeeView employeeView = employeeService.update(id, updateEmployeeForm);

        return ResponseEntity.ok(employeeView);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable Long id) {
            employeeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
