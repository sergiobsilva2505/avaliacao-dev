package br.com.sbs.avaliacaodevspring.dominio.exame.entity;

import br.com.sbs.avaliacaodevspring.dominio.exame.dto.UpdateExameForm;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "EXAME")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowid;
    @NotBlank
    @Column(name = "nm_exame")
    private String nome;

    public Exame() {
    }

    public Exame(Long rowid, String nome) {
        this.rowid = rowid;
        this.nome = nome;
    }

    public Exame(String nome) {
        this.nome = nome;
    }

    public void merge(UpdateExameForm exame) {
        this.nome = exame.nome();
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
