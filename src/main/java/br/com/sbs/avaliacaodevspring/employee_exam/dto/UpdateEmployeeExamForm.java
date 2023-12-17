package br.com.sbs.avaliacaodevspring.employee_exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateEmployeeExamForm(@NotNull Long rowid, @NotBlank String accomplishedAt, @NotNull Long employeeId, @NotNull Long examId) {
}
