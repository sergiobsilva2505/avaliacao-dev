package br.com.sbs.avaliacaodevspring.exame;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "EXAME")
public class ExameVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowid;
    @NotBlank
    @Column(name = "nm_exame")
    private String nome;

    public ExameVo() {
    }

    public ExameVo(String nome) {
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
        return "ExameVo { id= " + rowid + ", nome= " + nome + " }";
    }
}
