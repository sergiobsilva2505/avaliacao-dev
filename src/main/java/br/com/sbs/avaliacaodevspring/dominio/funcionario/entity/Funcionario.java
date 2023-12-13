package br.com.sbs.avaliacaodevspring.dominio.funcionario.entity;

import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.UpdateFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.realizado.entity.ExameFuncionario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowid;
    @NotBlank
    @Column(name = "nm_funcionario")
    private String nome;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ExameFuncionario> exameFuncionarios = new ArrayList<>();

    public Funcionario() {
    }

    public Funcionario(Long rowid, String nome) {
        this.rowid = rowid;
        this.nome = nome;
    }

    public void merge(UpdateFuncionarioForm updateFuncionarioForm) {
        this.nome = updateFuncionarioForm.nome();
    }

    public Funcionario(String nome) {
        this.nome = nome;
    }

    public Long getRowid() {
        return rowid;
    }

    public String getNome() {
        return nome;
    }

    public List<ExameFuncionario> getExameFuncionarios() {
        return exameFuncionarios;
    }

    @Override
    public String toString() {
        return "Funcionario { id= " + rowid + ", nome= " + nome + " }";
    }

}
