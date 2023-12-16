package br.com.sbs.avaliacaodevspring.employee.dto;

import br.com.sbs.avaliacaodevspring.employee.Funcionario;
import jakarta.validation.constraints.NotBlank;

public record NewFuncionarioForm(@NotBlank String nome) {

    public Funcionario toEntity() {
        return new Funcionario(nome);
    }
}
