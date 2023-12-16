package br.com.sbs.avaliacaodevspring.funcionario.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateFuncionarioForm(Long rowid, @NotBlank String nome) {

}
