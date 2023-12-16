package br.com.sbs.avaliacaodevspring.employee.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateEmployeeForm(Long rowid, @NotBlank String name) {

}
