package br.com.sbs.avaliacaodevspring.filter;

import br.com.sbs.avaliacaodevspring.exception.BusinessException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum OpcoesComboBuscarExames {
    ID("1", "ID"),
    NOME("2", "NOME");

    private String codigo;
    private String descricao;
    private final static Map<String, OpcoesComboBuscarExames> opcoes = new HashMap<>();

    static {
        Arrays.asList(OpcoesComboBuscarExames.values())
                .forEach(
                        opcao -> opcoes.put(opcao.getCodigo(), opcao)
                );
    }

    private OpcoesComboBuscarExames(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static OpcoesComboBuscarExames buscarPor(String codigo) throws IllegalArgumentException {
        if(codigo == null)
            throw new IllegalArgumentException("informe um codigo valido");

        OpcoesComboBuscarExames opcao = getOpcao(codigo)
                .orElseThrow(() -> new BusinessException("Codigo informado nao existe"));

        return opcao;
    }

    private static Optional<OpcoesComboBuscarExames> getOpcao(String codigo){
        return Optional.ofNullable(opcoes.get(codigo));
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
