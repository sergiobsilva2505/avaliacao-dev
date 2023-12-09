package br.com.sbs.avaliacaodevspring.exame.dto;

import br.com.sbs.avaliacaodevspring.exame.ExameVo;
import jakarta.validation.constraints.NotBlank;

public record ExameVoDTO(Long rowid, @NotBlank String nome) {

    public ExameVoDTO(Long rowid, String nome) {
        this.rowid = rowid;
        this.nome = nome;
    }

    public ExameVo toEntity() {
        return new ExameVo(rowid, nome);
    }

    public static ExameVoDTO toDTO(ExameVo exameVo) {
        return new ExameVoDTO(exameVo.getRowid(), exameVo.getNome());
    }
}
