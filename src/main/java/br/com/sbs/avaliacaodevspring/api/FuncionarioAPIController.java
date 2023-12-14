package br.com.sbs.avaliacaodevspring.api;

import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.FuncionarioView;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.NewFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.UpdateFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.entity.Funcionario;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioAPIController {

    private final FuncionarioService funcionarioService;

    public FuncionarioAPIController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    ResponseEntity<FuncionarioView> save(@Valid @RequestBody NewFuncionarioForm newFuncionarioForm) {
        FuncionarioView funcionarioView = funcionarioService.save(newFuncionarioForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionarioView.rowid()).toUri();

        return ResponseEntity.created(uri).body(funcionarioView);
    }

    @GetMapping
    ResponseEntity<Collection<FuncionarioView>> findAll() {
        Collection<FuncionarioView> funcionarios = funcionarioService.findAll();

        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    ResponseEntity<FuncionarioView> findById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.findById(id);

        return ResponseEntity.ok().body(new FuncionarioView(funcionario));
    }

    @PutMapping("/{id}")
    ResponseEntity<FuncionarioView> update(@PathVariable Long id, @Valid @RequestBody UpdateFuncionarioForm updateFuncionarioForm) {
        Funcionario funcionario = funcionarioService.update(id, updateFuncionarioForm);

        return ResponseEntity.ok(new FuncionarioView(funcionario));
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable Long id, boolean isRequestedByApi) {
            funcionarioService.deleteById(id, isRequestedByApi);

        return ResponseEntity.noContent().build();
    }
}
