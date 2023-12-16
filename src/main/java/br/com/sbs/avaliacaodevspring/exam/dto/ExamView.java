package br.com.sbs.avaliacaodevspring.exam.dto;

import br.com.sbs.avaliacaodevspring.exam.Exam;
import jakarta.validation.constraints.NotBlank;

public record ExamView(Long rowid, @NotBlank String name) {

    public ExamView(Exam exam) {
        this(exam.getRowid(), exam.getName());
    }
}
