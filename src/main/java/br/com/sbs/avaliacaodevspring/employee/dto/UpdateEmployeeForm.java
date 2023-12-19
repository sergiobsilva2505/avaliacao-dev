package br.com.sbs.avaliacaodevspring.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEmployeeForm(Long rowid, @NotBlank @Size(min = 3, max = 170) String name) {

}
