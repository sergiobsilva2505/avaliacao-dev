package br.com.sbs.avaliacaodevspring.exam.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateExameForm(Long rowid, @NotBlank String nome) {

}
