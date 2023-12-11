package br.com.sbs.avaliacaodevspring.exame;

import br.com.sbs.avaliacaodevspring.exame.dto.ExameView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/exames")
public class ExameAPIController {

    public final ExameService exameService;

    public ExameAPIController(ExameService exameService) {
        this.exameService = exameService;
    }

    @GetMapping
    ResponseEntity<Collection<ExameView>> findAll() {
        Collection<ExameView> exames = exameService.findAll();

        return ResponseEntity.ok().body(exames);
    }
}
