package br.com.sbs.avaliacaodevspring.funcionario.dto;

import br.com.sbs.avaliacaodevspring.funcionario.entity.Funcionario;
import jakarta.validation.constraints.NotBlank;

public record NewFuncionarioForm(@NotBlank String nome) {

    public Funcionario toEntity() {
        return new Funcionario(nome);
    }
}
