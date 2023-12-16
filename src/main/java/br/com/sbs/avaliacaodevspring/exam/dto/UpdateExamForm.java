package br.com.sbs.avaliacaodevspring.exam.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateExamForm(Long rowid, @NotBlank String name) {

}
