package br.com.sbs.avaliacaodevspring.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateExamForm(Long rowid, @NotBlank @Size(min = 3, max = 30) String name) {

}
