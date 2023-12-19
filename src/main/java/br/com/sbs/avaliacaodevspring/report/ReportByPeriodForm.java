package br.com.sbs.avaliacaodevspring.report;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReportByPeriodForm(@NotNull @PastOrPresent LocalDate initialDate, @NotNull @PastOrPresent LocalDate finishDate) {
}
