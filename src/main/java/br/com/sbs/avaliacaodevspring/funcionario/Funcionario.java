package br.com.sbs.avaliacaodevspring.funcionario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowid;
    @NotBlank
    @Column(name = "nm_funcionario")
    private String nome;

    public Funcionario() {
    }

    public Funcionario(Long rowid, String nome) {
        this.rowid = rowid;
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Funcionario { id= " + rowid + ", nome= " + nome + " }";
    }
}
