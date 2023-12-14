package br.com.sbs.avaliacaodevspring.api;

import br.com.sbs.avaliacaodevspring.dominio.exame.dto.ExameView;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.NewExameForm;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.UpdateExameForm;
import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import br.com.sbs.avaliacaodevspring.dominio.exame.service.ExameService;
import br.com.sbs.avaliacaodevspring.dominio.exame.service.exception.ResourceDatabaseException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/exames")
public class ExameAPIController {

    private final boolean isRequestedByApi = true;
    public final ExameService exameService;

    public ExameAPIController(ExameService exameService) {
        this.exameService = exameService;
    }

    @PostMapping
    ResponseEntity<ExameView> save(@Valid @RequestBody NewExameForm newExameForm) {
        ExameView exame = exameService.save(newExameForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(exame.rowid()).toUri();

        return ResponseEntity.created(uri).body(exame);
    }

    @GetMapping
    ResponseEntity<Collection<ExameView>> findAll() {
        Collection<ExameView> exames = exameService.findAll();

        return ResponseEntity.ok().body(exames);
    }

    @GetMapping("/{id}")
    ResponseEntity<ExameView> findById(@PathVariable Long id) {
        Exame exame = exameService.findById(id, isRequestedByApi);

        return ResponseEntity.ok().body(new ExameView(exame));
    }

    @PutMapping("/{id}")
    ResponseEntity<ExameView> update(@PathVariable Long id, @Valid @RequestBody UpdateExameForm updateExameForm) {
        ExameView exame = exameService.update(id, updateExameForm, isRequestedByApi);

        return ResponseEntity.ok(exame);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable Long id) {
        try {
            System.out.println("Pegou na Controller");
            exameService.deleteById(id, isRequestedByApi);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResourceDatabaseException("Não pode ser deletado!");
        }

        return ResponseEntity.noContent().build();
    }

}
