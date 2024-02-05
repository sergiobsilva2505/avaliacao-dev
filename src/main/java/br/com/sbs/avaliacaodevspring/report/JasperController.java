package br.com.sbs.avaliacaodevspring.report;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/relatorios")
public class JasperController {

    private final JasperService jasperService;

    public JasperController(JasperService jasperService) {
        this.jasperService = jasperService;
    }

    @GetMapping
    public String open(ReportByPeriodForm reportByPeriodForm, Model model) {
        model.addAttribute("reportByPeriodForm", reportByPeriodForm);
        return "report/reports";
    }

    @GetMapping("/pdf/jr1/{code}")
    public void showReportO1(@PathVariable("code") String code,
                             @RequestParam(name="initialDate") LocalDate initialDate,
                             @RequestParam(name="finishDate") LocalDate finishDate,
                             HttpServletResponse response) throws IOException {

        jasperService.addParams("INITIAL_DATE", initialDate);
        jasperService.addParams("FINISH_DATE", finishDate);
        byte[] bytes = jasperService.exportPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "inline; filename=relatorio-" + code + ".pdf");
        response.getOutputStream().write(bytes);
    }


}
