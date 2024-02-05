package br.com.sbs.avaliacaodevspring.report;

import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExamRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final EmployeeExamRepository employeeExamRepository;

    public ReportController(EmployeeExamRepository employeeExamRepository) {
        this.employeeExamRepository = employeeExamRepository;
    }


}
