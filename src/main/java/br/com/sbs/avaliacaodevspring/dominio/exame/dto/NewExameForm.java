package br.com.sbs.avaliacaodevspring.dominio.exame.dto;

import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import jakarta.validation.constraints.NotBlank;

public record NewExameForm(@NotBlank String nome) {

    public Exame toEntity() {
        return new Exame(nome);
    }
}
