package br.com.sbs.avaliacaodevspring.relatorio;

import java.time.LocalDate;

public interface ReportByPeriod {

    Long getRowIdEmployee();
    String getNomeEmployee();
    Long getRowIdExam();
    String getNomeExam();
    LocalDate getCreatedAt();

}
