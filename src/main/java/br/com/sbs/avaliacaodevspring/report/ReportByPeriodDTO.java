package br.com.sbs.avaliacaodevspring.report;

import java.time.LocalDate;

public record ReportByPeriodDTO(Long rowIdEmployee, String nomeEmployee, Long rowIdExam, String nomeExam, LocalDate accomplishedAt) {

    public ReportByPeriodDTO(ReportByPeriod reportByPeriod) {
        this(reportByPeriod.getRowIdEmployee(), reportByPeriod.getNomeEmployee(), reportByPeriod.getRowIdExam(), reportByPeriod.getNomeExam(), reportByPeriod.getAccomplishedAt());
    }
}