package br.com.sbs.avaliacaodevspring.exame.dto;

import br.com.sbs.avaliacaodevspring.exame.Exame;
import jakarta.validation.constraints.NotBlank;

public record NewExameForm(Long rowid, @NotBlank String nome) {

    public Exame toEntity() {
        return new Exame(nome);
    }
}
