package br.com.sbs.avaliacaodevspring.employee_exam;

import br.com.sbs.avaliacaodevspring.employee.Employee;
import br.com.sbs.avaliacaodevspring.exam.Exam;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.StringJoiner;

@Entity
public class EmployeeExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowid;
    @NotNull
    @PastOrPresent
    private LocalDate accomplishedAt;
    @NotNull
    @JoinColumn(name = "exam_id")
    @ManyToOne
    private Exam exam;
    @NotNull
    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    public EmployeeExam() {}

    public EmployeeExam(LocalDate accomplishedAt, Employee employee, Exam exam) {
        this.accomplishedAt = accomplishedAt;
        this.employee = employee;
        this.exam = exam;
    }

    public void merge(Employee employee, Exam exam) {
        this.employee = employee;
        this.exam = exam;
    }

    public String getExamName() {
        return getExam().getName();
    }

    public String getEmployeeExamName() {
        return getEmployee().getName();
    }

    public Long getRowid() {
        return rowid;
    }

    public LocalDate getAccomplishedAt() {
        return accomplishedAt;
    }

    public Exam getExam() {
        return exam;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EmployeeExam.class.getSimpleName() + "[", "]")
                .add("rowid=" + rowid)
                .add("accomplishedAt=" + accomplishedAt)
                .add("exam=" + exam)
                .add("employee=" + employee)
                .toString();
    }
}
