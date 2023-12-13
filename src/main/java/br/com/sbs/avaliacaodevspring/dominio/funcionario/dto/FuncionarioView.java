package br.com.sbs.avaliacaodevspring.dominio.funcionario.dto;

import br.com.sbs.avaliacaodevspring.dominio.funcionario.entity.Funcionario;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioView(Long rowid, @NotBlank String nome) {

    public FuncionarioView(Funcionario funcionario) {
        this(funcionario.getRowid(), funcionario.getNome());
    }
}
