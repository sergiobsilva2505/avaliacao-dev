package br.com.sbs.avaliacaodevspring.exame.dto;

import br.com.sbs.avaliacaodevspring.exame.Exame;
import jakarta.validation.constraints.NotBlank;

public record ExameView(Long rowid, @NotBlank String nome) {

    public ExameView(Exame exame) {
        this(exame.getRowid(), exame.getNome());
    }
}
