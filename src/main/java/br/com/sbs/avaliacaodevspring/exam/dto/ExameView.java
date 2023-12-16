package br.com.sbs.avaliacaodevspring.exam.dto;

import br.com.sbs.avaliacaodevspring.exam.Exame;
import jakarta.validation.constraints.NotBlank;

public record ExameView(Long rowid, @NotBlank String nome) {

    public ExameView(Exame exame) {
        this(exame.getRowid(), exame.getNome());
    }
}
