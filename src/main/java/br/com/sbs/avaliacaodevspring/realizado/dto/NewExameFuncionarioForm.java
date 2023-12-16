package br.com.sbs.avaliacaodevspring.realizado.dto;

import br.com.sbs.avaliacaodevspring.exam.Exame;
import br.com.sbs.avaliacaodevspring.funcionario.entity.Funcionario;
import br.com.sbs.avaliacaodevspring.realizado.entity.ExameFuncionario;
import jakarta.validation.constraints.NotNull;

public record NewExameFuncionarioForm(@NotNull Long exameId, @NotNull Long funcionarioId) {

    public ExameFuncionario toEntity(Exame exame, Funcionario funcionario) {
        return new ExameFuncionario(exame, funcionario);
    }
}
