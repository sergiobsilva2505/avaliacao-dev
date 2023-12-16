package br.com.sbs.avaliacaodevspring.employee.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateFuncionarioForm(Long rowid, @NotBlank String nome) {

}
