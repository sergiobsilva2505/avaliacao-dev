package br.com.sbs.avaliacaodevspring.employee.dto;

import br.com.sbs.avaliacaodevspring.employee.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewEmployeeForm(@NotBlank @Size(min = 3, max = 170) String name) {

    public Employee toEntity() {
        return new Employee(name);
    }
}
