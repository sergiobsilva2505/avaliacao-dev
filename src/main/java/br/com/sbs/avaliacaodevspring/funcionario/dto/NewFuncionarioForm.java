package br.com.sbs.avaliacaodevspring.funcionario.dto;

import br.com.sbs.avaliacaodevspring.funcionario.Funcionario;
import jakarta.validation.constraints.NotBlank;

public record NewFuncionarioForm(Long rowid, @NotBlank String nome) {

    public Funcionario toEntity() {
        return new Funcionario(nome);
    }
}
