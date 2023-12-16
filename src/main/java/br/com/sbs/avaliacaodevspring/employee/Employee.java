package br.com.sbs.avaliacaodevspring.employee;

import br.com.sbs.avaliacaodevspring.employee.dto.UpdateEmployeeForm;
import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExam;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowid;

    @NotBlank
    @Size(min = 3, max = 170)
    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<EmployeeExam> employeeExams = new ArrayList<>();

    public Employee() {
    }

    public Employee(Long rowid, String name) {
        this.rowid = rowid;
        this.name = name;
    }

    public void merge(UpdateEmployeeForm updateEmployeeForm) {
        this.name = updateEmployeeForm.name();
    }

    public Employee(String name) {
        this.name = name;
    }

    public Long getRowid() {
        return rowid;
    }

    public String getName() {
        return name;
    }

    public List<EmployeeExam> getEmployeeExams() {
        return employeeExams;
    }

    @Override
    public String toString() {
        return "Employee { id= " + rowid + ", name= " + name + " }";
    }

}
