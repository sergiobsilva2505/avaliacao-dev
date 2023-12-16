package br.com.sbs.avaliacaodevspring.examemployee;

import br.com.sbs.avaliacaodevspring.exam.Exame;
import br.com.sbs.avaliacaodevspring.exam.ExameService;
import br.com.sbs.avaliacaodevspring.employee.Funcionario;
import br.com.sbs.avaliacaodevspring.employee.FuncionarioService;
import br.com.sbs.avaliacaodevspring.examemployee.dto.ExameFuncionarioView;
import br.com.sbs.avaliacaodevspring.examemployee.dto.NewExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.examemployee.dto.UpdateExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import br.com.sbs.avaliacaodevspring.relatorio.ReportByPeriod;
import br.com.sbs.avaliacaodevspring.relatorio.ReportByPeriodForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ExameFuncionarioService {

    private final ExameFuncionarioRepository exameFuncionarioRepository;
    private final ExameService exameService;
    private final FuncionarioService funcionarioService;

    public ExameFuncionarioService(ExameFuncionarioRepository exameFuncionarioRepository, ExameService exameService, FuncionarioService funcionarioService) {
        this.exameFuncionarioRepository = exameFuncionarioRepository;
        this.exameService = exameService;
        this.funcionarioService = funcionarioService;
    }

    @Transactional
    public ExameFuncionario save(NewExameFuncionarioForm newExameFuncionarioForm) {
        Exame exame = exameService.findById(newExameFuncionarioForm.exameId());
        Funcionario funcionario = funcionarioService.findById(newExameFuncionarioForm.funcionarioId());

        ExameFuncionario exameFuncionario = newExameFuncionarioForm.toEntity(exame, funcionario);
        exameFuncionarioRepository.save(exameFuncionario);

        return exameFuncionario;
    }

    public Collection<ExameFuncionarioView> findAll() {
        Collection<ExameFuncionario> examesFuncionarios = exameFuncionarioRepository.findAll();

        return examesFuncionarios.stream().map(ExameFuncionarioView::new).toList();
    }

    public ExameFuncionario findById(Long id) {
        return exameFuncionarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id)));
    }

    @Transactional
    public ExameFuncionarioView update(Long id, UpdateExameFuncionarioForm updateExameFuncionarioForm) {
        Exame exame = exameService.findById(updateExameFuncionarioForm.exameId());
        Funcionario funcionario = funcionarioService.findById(updateExameFuncionarioForm.funcionarioId());
        ExameFuncionario exameFuncionario = exameFuncionarioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id)));
        exameFuncionario.merge(exame, funcionario);

        return new ExameFuncionarioView(exameFuncionario);
    }

    @Transactional
    public void delete(Long id) {
        exameFuncionarioRepository.deleteById(id);
    }

    public List<ReportByPeriod> getReportByPeriod(ReportByPeriodForm reportByPeriodForm) {
        return exameFuncionarioRepository.getReportByPeriod(reportByPeriodForm.initialDate(), reportByPeriodForm.finishDate());
    }
}
