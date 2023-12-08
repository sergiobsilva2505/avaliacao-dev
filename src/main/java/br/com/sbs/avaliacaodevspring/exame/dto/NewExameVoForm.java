package br.com.sbs.avaliacaodevspring.exame.dto;

import br.com.sbs.avaliacaodevspring.exame.ExameVo;
import jakarta.validation.constraints.NotBlank;

public record NewExameVoForm(Long rowid, @NotBlank String nome) {

    public ExameVo toEntity() {
        return new ExameVo(nome);
    }
}
