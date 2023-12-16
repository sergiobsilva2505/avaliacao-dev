package br.com.sbs.avaliacaodevspring.funcionario.dto;

import br.com.sbs.avaliacaodevspring.funcionario.entity.Funcionario;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioView(Long rowid, @NotBlank String nome) {

    public FuncionarioView(Funcionario funcionario) {
        this(funcionario.getRowid(), funcionario.getNome());
    }
}
