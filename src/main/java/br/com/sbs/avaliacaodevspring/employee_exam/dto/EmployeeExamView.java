package br.com.sbs.avaliacaodevspring.employee_exam.dto;

import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExam;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmployeeExamView(Long rowid, LocalDate accomplishedAt, Long examId, String examName, Long employeeId, String employeeName) {

   public EmployeeExamView(EmployeeExam employeeExam) {
       this(employeeExam.getRowid(),
               employeeExam.getAccomplishedAt(),
               employeeExam.getExam().getRowid(),
               employeeExam.getExam().getName(),
               employeeExam.getEmployee().getRowid(),
               employeeExam.getEmployee().getName());
   }
}
