package br.com.sbs.avaliacaodevspring.api;

import br.com.sbs.avaliacaodevspring.examemployee.ExameFuncionarioService;
import br.com.sbs.avaliacaodevspring.relatorio.ReportByPeriod;
import br.com.sbs.avaliacaodevspring.relatorio.ReportByPeriodForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class ReportAPIController {

    private final ExameFuncionarioService exameFuncionarioService;

    public ReportAPIController(ExameFuncionarioService exameFuncionarioService) {
        this.exameFuncionarioService = exameFuncionarioService;
    }

    @PostMapping
    ResponseEntity<List<ReportByPeriod>> showForm(@RequestBody ReportByPeriodForm reportByPeriodForm) {
        List<ReportByPeriod> rows = exameFuncionarioService.getReportByPeriod(reportByPeriodForm);

        return ResponseEntity.ok(rows);
    }

}