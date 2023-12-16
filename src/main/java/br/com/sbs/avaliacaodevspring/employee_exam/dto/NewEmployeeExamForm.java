package br.com.sbs.avaliacaodevspring.employee_exam.dto;

import br.com.sbs.avaliacaodevspring.exam.Exam;
import br.com.sbs.avaliacaodevspring.employee.Employee;
import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExam;
import jakarta.validation.constraints.NotNull;

public record NewEmployeeExamForm(@NotNull Long examId, @NotNull Long employeeId) {

    public EmployeeExam toEntity(Employee employee, Exam exam) {
        return new EmployeeExam(employee, exam);
    }
}
