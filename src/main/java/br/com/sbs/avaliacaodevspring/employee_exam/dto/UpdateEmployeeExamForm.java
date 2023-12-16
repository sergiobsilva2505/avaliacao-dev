package br.com.sbs.avaliacaodevspring.employee_exam.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateEmployeeExamForm(@NotNull Long rowid, @NotNull Long employeeId, @NotNull Long examId) {
}
