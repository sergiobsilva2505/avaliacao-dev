package br.com.sbs.avaliacaodevspring.api;

import br.com.sbs.avaliacaodevspring.dominio.exame.dto.ExameView;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.NewExameForm;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.UpdateExameForm;
import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import br.com.sbs.avaliacaodevspring.dominio.exame.service.exception.ResourceDatabaseException;
import br.com.sbs.avaliacaodevspring.dominio.realizado.dto.ExameFuncionarioView;
import br.com.sbs.avaliacaodevspring.dominio.realizado.dto.NewExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.realizado.dto.UpdateExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.realizado.entity.ExameFuncionario;
import br.com.sbs.avaliacaodevspring.dominio.realizado.service.ExameFuncionarioService;
import br.com.sbs.avaliacaodevspring.dominio.realizado.validator.NewExameFuncionarioFormValidator;
import br.com.sbs.avaliacaodevspring.dominio.realizado.validator.UpdateExameFuncionarioFormValidator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/exames-funcionarios")
public class ExameFuncionarioAPIController {

    private final boolean isRequestesByAPI = true;
    private final ExameFuncionarioService exameFuncionarioService;
    private final NewExameFuncionarioFormValidator newExameFuncionarioFormValidator;
    private final UpdateExameFuncionarioFormValidator updateExameFuncionarioFormValidator;

    public ExameFuncionarioAPIController(ExameFuncionarioService exameFuncionarioService, NewExameFuncionarioFormValidator newExameFuncionarioFormValidator, UpdateExameFuncionarioFormValidator updateExameFuncionarioFormValidator) {
        this.exameFuncionarioService = exameFuncionarioService;
        this.newExameFuncionarioFormValidator = newExameFuncionarioFormValidator;
        this.updateExameFuncionarioFormValidator = updateExameFuncionarioFormValidator;
    }

    @InitBinder("updateExameFuncionarioForm")
    void initBinderUpdateExameFuncionarioForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(updateExameFuncionarioFormValidator);
    }

    @InitBinder("newExameFuncionarioForm")
    void initBinderNewExameFuncionarioForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newExameFuncionarioFormValidator);
    }

    @PostMapping
    ResponseEntity<ExameFuncionarioView> save(@Valid @RequestBody NewExameFuncionarioForm newExameFuncionarioForm) {
        ExameFuncionario exameFuncionario = exameFuncionarioService.save(newExameFuncionarioForm, isRequestesByAPI);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(exameFuncionario.getRowid()).toUri();

        return ResponseEntity.created(uri).body(new ExameFuncionarioView(exameFuncionario));
    }

    @GetMapping
    ResponseEntity<Collection<ExameFuncionarioView>> findAll() {
        Collection<ExameFuncionarioView> exameFuncionarios = exameFuncionarioService.findAll();

        return ResponseEntity.ok().body(exameFuncionarios);
    }

    @GetMapping("/{id}")
    ResponseEntity<ExameFuncionarioView> findById(@PathVariable Long id) {
        ExameFuncionario exameFuncinario = exameFuncionarioService.findById(id, isRequestesByAPI);

        return ResponseEntity.ok().body(new ExameFuncionarioView(exameFuncinario));
    }

    @PutMapping("/{id}")
    ResponseEntity<ExameFuncionarioView> update(@PathVariable Long id, @Valid @RequestBody UpdateExameFuncionarioForm updateExameFuncionarioForm) {
        ExameFuncionarioView exameFuncionarioView = exameFuncionarioService.update(id, updateExameFuncionarioForm, isRequestesByAPI);

        return ResponseEntity.ok(exameFuncionarioView);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable Long id) {
        exameFuncionarioService.delete(id, isRequestesByAPI);

        return ResponseEntity.noContent().build();
    }
}
