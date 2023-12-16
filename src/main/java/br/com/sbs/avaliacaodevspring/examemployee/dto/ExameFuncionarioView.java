package br.com.sbs.avaliacaodevspring.examemployee.dto;

import br.com.sbs.avaliacaodevspring.examemployee.ExameFuncionario;

import java.time.LocalDate;

public record ExameFuncionarioView(Long rowid, LocalDate realizadoEm, Long exameId, String nomeExame, Long funcionarioId, String nomeFuncionario) {

   public ExameFuncionarioView(ExameFuncionario exameFuncionario) {
       this(exameFuncionario.getRowid(),
               exameFuncionario.getRealizadoEm(),
               exameFuncionario.getExame().getRowid(),
               exameFuncionario.getExame().getNome(),
               exameFuncionario.getFuncionario().getRowid(),
               exameFuncionario.getFuncionario().getNome());
   }
}
