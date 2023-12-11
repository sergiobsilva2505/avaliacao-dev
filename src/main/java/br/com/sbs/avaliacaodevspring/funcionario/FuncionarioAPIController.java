package br.com.sbs.avaliacaodevspring.funcionario;

import br.com.sbs.avaliacaodevspring.funcionario.dto.FuncionarioView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioAPIController {

    private final FuncionarioService funcionarioService;

    public FuncionarioAPIController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    ResponseEntity<Collection<FuncionarioView>> findAll() {
        Collection<FuncionarioView> funcionarios = funcionarioService.findAll();

        return ResponseEntity.ok(funcionarios);
    }
}
