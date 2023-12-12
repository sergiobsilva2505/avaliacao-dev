package br.com.sbs.avaliacaodevspring.realizado.dto;

import br.com.sbs.avaliacaodevspring.realizado.ExameFuncionario;

import java.time.LocalDateTime;

public record ExameFuncionarioView(Long rowid, LocalDateTime realizadoEm, Long exameId, String nomeExame, Long funcionarioId, String nomeFuncionario) {

   public ExameFuncionarioView(ExameFuncionario exameFuncionario) {
       this(exameFuncionario.getRowid(),
               exameFuncionario.getRealizadoEm(),
               exameFuncionario.getExame().getRowid(),
               exameFuncionario.getNomeExame(),
               exameFuncionario.getFuncionario().getRowid(),
               exameFuncionario.getNomeFuncionario());
   }
}
