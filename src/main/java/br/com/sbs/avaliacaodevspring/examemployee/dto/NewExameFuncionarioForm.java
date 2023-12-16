package br.com.sbs.avaliacaodevspring.examemployee.dto;

import br.com.sbs.avaliacaodevspring.exam.Exame;
import br.com.sbs.avaliacaodevspring.employee.Funcionario;
import br.com.sbs.avaliacaodevspring.examemployee.ExameFuncionario;
import jakarta.validation.constraints.NotNull;

public record NewExameFuncionarioForm(@NotNull Long exameId, @NotNull Long funcionarioId) {

    public ExameFuncionario toEntity(Exame exame, Funcionario funcionario) {
        return new ExameFuncionario(exame, funcionario);
    }
}
