package br.com.sbs.avaliacaodevspring.dominio.realizado.dto;

import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.entity.Funcionario;
import br.com.sbs.avaliacaodevspring.dominio.realizado.entity.ExameFuncionario;
import jakarta.validation.constraints.NotNull;

public record NewExameFuncionarioForm(@NotNull Long exameId, @NotNull Long funcionarioId) {

    public ExameFuncionario toEntity(Exame exame, Funcionario funcionario) {
        return new ExameFuncionario(exame, funcionario);
    }
}
