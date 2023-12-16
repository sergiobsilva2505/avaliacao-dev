package br.com.sbs.avaliacaodevspring.examemployee.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateExameFuncionarioForm(@NotNull Long rowid, @NotNull Long exameId, @NotNull Long funcionarioId) {


}
