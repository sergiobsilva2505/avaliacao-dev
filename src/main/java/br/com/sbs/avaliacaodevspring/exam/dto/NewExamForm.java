package br.com.sbs.avaliacaodevspring.exam.dto;

import br.com.sbs.avaliacaodevspring.exam.Exam;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewExamForm(@NotBlank @Size(min = 3, max = 30) String name) {

    public Exam toEntity() {
        return new Exam(name);
    }
}
