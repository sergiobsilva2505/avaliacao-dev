package br.com.sbs.avaliacaodevspring.relatorio;

import java.time.LocalDate;
import java.util.List;

public record ReportByPeriodDTO(Long rowIdEmployee, String nomeEmployee, Long rowIdExam, String nomeExam, LocalDate createdAt) {
}