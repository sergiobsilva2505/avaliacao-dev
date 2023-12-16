package br.com.sbs.avaliacaodevspring.exam.dto;

import br.com.sbs.avaliacaodevspring.exam.Exame;
import jakarta.validation.constraints.NotBlank;

public record NewExameForm(@NotBlank String nome) {

    public Exame toEntity() {
        return new Exame(nome);
    }
}
