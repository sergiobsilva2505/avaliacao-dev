package br.com.sbs.avaliacaodevspring.report;

import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExamService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/relatorios")
public class ReportExamEmployeeController {

    private final EmployeeExamService employeeExamService;

    public ReportExamEmployeeController(EmployeeExamService employeeExamService) {
        this.employeeExamService = employeeExamService;
    }

    @GetMapping
    public String showForm(ReportByPeriodForm reportByPeriodForm, Model model) {
        model.addAttribute("reportByPeriodForm", reportByPeriodForm);

        return "report/report";
    }

    @PostMapping
    public String sendRequest(@Valid ReportByPeriodForm reportByPeriodForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(reportByPeriodForm, model);
        }
        List<ReportByPeriod> rows = employeeExamService.getReportByPeriod(reportByPeriodForm);
        model.addAttribute("rows", rows);

        return "redirect:/relatorios";
    }
}
