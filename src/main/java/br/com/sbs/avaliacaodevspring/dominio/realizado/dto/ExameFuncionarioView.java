package br.com.sbs.avaliacaodevspring.dominio.realizado.dto;

import br.com.sbs.avaliacaodevspring.dominio.realizado.entity.ExameFuncionario;

import java.time.LocalDate;

public record ExameFuncionarioView(Long rowid, LocalDate realizadoEm, Long exameId, Long funcionarioId) {

   public ExameFuncionarioView(ExameFuncionario exameFuncionario) {
       this(exameFuncionario.getRowid(),
               exameFuncionario.getRealizadoEm(),
               exameFuncionario.getExame().getRowid(),
               exameFuncionario.getFuncionario().getRowid());
   }
}
