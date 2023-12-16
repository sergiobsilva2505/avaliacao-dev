package br.com.sbs.avaliacaodevspring.employee.dto;

import br.com.sbs.avaliacaodevspring.employee.Funcionario;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioView(Long rowid, @NotBlank String nome) {

    public FuncionarioView(Funcionario funcionario) {
        this(funcionario.getRowid(), funcionario.getNome());
    }
}
