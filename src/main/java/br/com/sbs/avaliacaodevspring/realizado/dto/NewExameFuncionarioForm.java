package br.com.sbs.avaliacaodevspring.realizado.dto;

import br.com.sbs.avaliacaodevspring.exame.Exame;
import br.com.sbs.avaliacaodevspring.funcionario.Funcionario;
import br.com.sbs.avaliacaodevspring.realizado.ExameFuncionario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record NewExameFuncionarioForm(@NotNull Long exameId, @NotNull Long funcionarioId) {

    public ExameFuncionario toEntity(Exame exame, Funcionario funcionario) {
        return new ExameFuncionario(exame, funcionario);
    }
}
