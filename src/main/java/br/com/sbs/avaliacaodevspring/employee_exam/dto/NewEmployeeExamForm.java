package br.com.sbs.avaliacaodevspring.employee_exam.dto;

import br.com.sbs.avaliacaodevspring.exam.Exam;
import br.com.sbs.avaliacaodevspring.employee.Employee;
import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExam;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record NewEmployeeExamForm(@NotBlank String accomplishedAt, @NotNull Long examId, @NotNull Long employeeId) {

    public EmployeeExam toEntity(Employee employee, Exam exam) {
        return new EmployeeExam(LocalDateTime.parse(accomplishedAt), employee, exam);
    }
}
