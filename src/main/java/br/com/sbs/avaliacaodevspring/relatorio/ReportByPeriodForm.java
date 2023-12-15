package br.com.sbs.avaliacaodevspring.relatorio;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ReportByPeriodForm(@NotNull @PastOrPresent LocalDate initialDate, @NotNull @PastOrPresent LocalDate finishDate) {
//    @NotNull @PastOrPresent
}
