package br.com.sbs.avaliacaodevspring.exame;

import br.com.sbs.avaliacaodevspring.exame.dto.ExameVoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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

    public ExameVo(Long rowid, String nome) {
        this.rowid = rowid;
        this.nome = nome;
    }

    public ExameVo(String nome) {
        this.nome = nome;
    }

    public void merge(ExameVo exameVo) {
        this.rowid = exameVo.rowid;
        this.nome = exameVo.nome;
    }

    public ExameVoDTO toDTO(ExameVo exameVo) {
        return new ExameVoDTO(exameVo.getRowid(), exameVo.getNome());
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
