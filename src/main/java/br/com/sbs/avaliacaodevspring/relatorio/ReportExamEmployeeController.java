package br.com.sbs.avaliacaodevspring.relatorio;

import br.com.sbs.avaliacaodevspring.examemployee.ExameFuncionarioService;
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

    private final ExameFuncionarioService exameFuncionarioService;

    public ReportExamEmployeeController(ExameFuncionarioService exameFuncionarioService) {
        this.exameFuncionarioService = exameFuncionarioService;
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
        List<ReportByPeriod> rows = exameFuncionarioService.getReportByPeriod(reportByPeriodForm);
        model.addAttribute("rows", rows);

        return "redirect:/relatorios";
    }
}
