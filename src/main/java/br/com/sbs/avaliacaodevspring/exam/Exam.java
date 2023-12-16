package br.com.sbs.avaliacaodevspring.exam;

import br.com.sbs.avaliacaodevspring.exam.dto.UpdateExamForm;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowid;
    @NotBlank
    @Column(length = 30)
    private String name;

    public Exam() {
    }

    public Exam(Long rowid, String name) {
        this.rowid = rowid;
        this.name = name;
    }

    public Exam(String name) {
        this.name = name;
    }

    public void merge(UpdateExamForm examForm) {
        this.name = examForm.name();
    }

    public Long getRowid() {
        return rowid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Exam { id= " + rowid + ", name= " + name + " }";
    }
}
