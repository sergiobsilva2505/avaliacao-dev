package br.com.sbs.avaliacaodevspring.report;

import java.time.LocalDate;

public record ReportByPeriodDTO(Long rowIdEmployee, String nomeEmployee, Long rowIdExam, String nomeExam, LocalDate createdAt) {
}