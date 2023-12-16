package br.com.sbs.avaliacaodevspring.employee.dto;

import br.com.sbs.avaliacaodevspring.employee.Employee;
import jakarta.validation.constraints.NotBlank;

public record NewEmployeeForm(@NotBlank String name) {

    public Employee toEntity() {
        return new Employee(name);
    }
}
