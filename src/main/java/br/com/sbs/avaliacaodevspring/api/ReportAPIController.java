package br.com.sbs.avaliacaodevspring.api;

import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExamService;
import br.com.sbs.avaliacaodevspring.report.ReportByPeriod;
import br.com.sbs.avaliacaodevspring.report.ReportByPeriodForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class ReportAPIController {

    private final EmployeeExamService employeeExamService;

    public ReportAPIController(EmployeeExamService employeeExamService) {
        this.employeeExamService = employeeExamService;
    }

    @PostMapping
    ResponseEntity<List<ReportByPeriod>> showForm(@RequestBody ReportByPeriodForm reportByPeriodForm) {
        List<ReportByPeriod> rows = employeeExamService.getReportByPeriod(reportByPeriodForm);

        return ResponseEntity.ok(rows);
    }

}
