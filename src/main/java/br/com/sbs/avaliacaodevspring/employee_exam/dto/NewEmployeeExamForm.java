package br.com.sbs.avaliacaodevspring.employee_exam.dto;

import br.com.sbs.avaliacaodevspring.employee.Employee;
import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExam;
import br.com.sbs.avaliacaodevspring.exam.Exam;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewEmployeeExamForm(@NotNull LocalDate accomplishedAt, @NotNull Long examId, @NotNull Long employeeId) {

    public EmployeeExam toEntity(Employee employee, Exam exam) {
        return new EmployeeExam(accomplishedAt, employee, exam);
    }
}
