package br.com.sbs.avaliacaodevspring.dominio.exame.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateExameForm(Long rowid, @NotBlank String nome) {

}
