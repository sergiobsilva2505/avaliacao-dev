package br.com.sbs.avaliacaodevspring.employee.dto;

import br.com.sbs.avaliacaodevspring.employee.Employee;
import jakarta.validation.constraints.NotBlank;

public record EmployeeView(Long rowid, @NotBlank String name) {

    public EmployeeView(Employee employee) {
        this(employee.getRowid(), employee.getName());
    }
}
