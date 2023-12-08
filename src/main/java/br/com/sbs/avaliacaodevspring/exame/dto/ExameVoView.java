package br.com.sbs.avaliacaodevspring.exame.dto;

import br.com.sbs.avaliacaodevspring.exame.ExameVo;

public record ExameVoView(Long rowid, String nome) {

    public ExameVoView(ExameVo exameVo) {
        this(exameVo.getRowid(), exameVo.getNome());
    }
}
