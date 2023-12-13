package br.com.sbs.avaliacaodevspring.realizado.dto;

import br.com.sbs.avaliacaodevspring.realizado.ExameFuncionario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExameFuncionarioView(Long rowid, LocalDate realizadoEm, Long exameId, Long funcionarioId) {

   public ExameFuncionarioView(ExameFuncionario exameFuncionario) {
       this(exameFuncionario.getRowid(),
               exameFuncionario.getRealizadoEm(),
               exameFuncionario.getExame().getRowid(),
               exameFuncionario.getFuncionario().getRowid());
   }
}
