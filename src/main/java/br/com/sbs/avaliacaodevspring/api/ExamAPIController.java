package br.com.sbs.avaliacaodevspring.api;

import br.com.sbs.avaliacaodevspring.exam.ExamService;
import br.com.sbs.avaliacaodevspring.exam.dto.ExamView;
import br.com.sbs.avaliacaodevspring.exam.dto.NewExamForm;
import br.com.sbs.avaliacaodevspring.exam.dto.UpdateExamForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/exames")
public class ExamAPIController {

    public final ExamService examService;

    public ExamAPIController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    ResponseEntity<ExamView> save(@Valid @RequestBody NewExamForm newExamForm) {
        ExamView exam = examService.save(newExamForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(exam.rowid()).toUri();

        return ResponseEntity.created(uri).body(exam);
    }

    @GetMapping
    ResponseEntity<Page<ExamView>> findAll(@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);
        Page<ExamView> exams = examService.findAll(pageRequest);

        return ResponseEntity.ok().body(exams);
    }

    @GetMapping("/{id}")
    ResponseEntity<ExamView> findById(@PathVariable Long id) {
        ExamView examView = examService.findById(id);

        return ResponseEntity.ok().body(examView);
    }

    @PutMapping("/{id}")
    ResponseEntity<ExamView> update(@PathVariable Long id, @Valid @RequestBody UpdateExamForm updateExamForm) {
        ExamView exam = examService.update(id, updateExamForm);

        return ResponseEntity.ok(exam);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable Long id) {
        examService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
