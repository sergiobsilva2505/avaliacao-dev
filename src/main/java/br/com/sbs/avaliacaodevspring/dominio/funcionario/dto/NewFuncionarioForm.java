package br.com.sbs.avaliacaodevspring.dominio.funcionario.dto;

import br.com.sbs.avaliacaodevspring.dominio.funcionario.entity.Funcionario;
import jakarta.validation.constraints.NotBlank;

public record NewFuncionarioForm(Long rowid, @NotBlank String nome) {

    public Funcionario toEntity() {
        return new Funcionario(nome);
    }
}