package br.com.sbs.avaliacaodevspring.employee_exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateEmployeeExamForm(@NotNull Long rowid, @NotNull LocalDate accomplishedAt, @NotNull Long employeeId, @NotNull Long examId) {
}
