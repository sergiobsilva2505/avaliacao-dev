package br.com.sbs.avaliacaodevspring.api;

import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExam;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.EmployeeExamView;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.NewEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.UpdateEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExamService;
import br.com.sbs.avaliacaodevspring.employee_exam.validator.NewEmployeeExamFormValidator;
import br.com.sbs.avaliacaodevspring.employee_exam.validator.UpdateEmployeeExamFormValidator;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/exames-funcionarios")
public class EmployeeExamAPIController {

    private final EmployeeExamService employeeExamService;
    private final NewEmployeeExamFormValidator newEmployeeExamFormValidator;
    private final UpdateEmployeeExamFormValidator updateEmployeeExamFormValidator;

    public EmployeeExamAPIController(EmployeeExamService employeeExamService, NewEmployeeExamFormValidator newEmployeeExamFormValidator, UpdateEmployeeExamFormValidator updateEmployeeExamFormValidator) {
        this.employeeExamService = employeeExamService;
        this.newEmployeeExamFormValidator = newEmployeeExamFormValidator;
        this.updateEmployeeExamFormValidator = updateEmployeeExamFormValidator;
    }

    @InitBinder("updateEmployeeExamForm")
    void initBinderUpdateEmployeeExamForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(updateEmployeeExamFormValidator);
    }

    @InitBinder("newEmployeeExamForm")
    void initBinderNewEmployeeExamForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newEmployeeExamFormValidator);
    }

    @PostMapping
    ResponseEntity<EmployeeExamView> save(@Valid @RequestBody NewEmployeeExamForm newEmployeeExamForm) {
        EmployeeExam employeeExam = employeeExamService.save(newEmployeeExamForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeExam.getRowid()).toUri();

        return ResponseEntity.created(uri).body(new EmployeeExamView(employeeExam));
    }

    @GetMapping
    ResponseEntity<Page<EmployeeExamView>> findAll(@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
        Page<EmployeeExamView> employeesExams = employeeExamService.findAll(pageRequest);

        return ResponseEntity.ok().body(employeesExams);
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeExamView> findById(@PathVariable Long id) {
        EmployeeExamView employeeExamView = employeeExamService.findById(id);

        return ResponseEntity.ok().body(employeeExamView);
    }

    @PutMapping("/{id}")
    ResponseEntity<EmployeeExamView> update(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeExamForm updateEmployeeExamForm) {
        EmployeeExamView employeeExamView = employeeExamService.update(id, updateEmployeeExamForm);

        return ResponseEntity.ok(employeeExamView);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable Long id) {
        employeeExamService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
