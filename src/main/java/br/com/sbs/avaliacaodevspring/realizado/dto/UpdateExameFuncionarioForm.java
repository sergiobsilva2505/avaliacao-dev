package br.com.sbs.avaliacaodevspring.realizado.dto;

import br.com.sbs.avaliacaodevspring.exame.Exame;
import br.com.sbs.avaliacaodevspring.funcionario.Funcionario;
import br.com.sbs.avaliacaodevspring.realizado.ExameFuncionario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record UpdateExameFuncionarioForm(@NotNull Long exameId, @NotNull Long funcionarioId) {


}
