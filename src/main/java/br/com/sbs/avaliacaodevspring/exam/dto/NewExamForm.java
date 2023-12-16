package br.com.sbs.avaliacaodevspring.exam.dto;

import br.com.sbs.avaliacaodevspring.exam.Exam;
import jakarta.validation.constraints.NotBlank;

public record NewExamForm(@NotBlank String name) {

    public Exam toEntity() {
        return new Exam(name);
    }
}
