package br.com.sbs.avaliacaodevspring.realizado;

import br.com.sbs.avaliacaodevspring.exame.Exame;
import br.com.sbs.avaliacaodevspring.exame.ExameService;
import br.com.sbs.avaliacaodevspring.exame.dto.ExameView;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import br.com.sbs.avaliacaodevspring.funcionario.Funcionario;
import br.com.sbs.avaliacaodevspring.funcionario.FuncionarioService;
import br.com.sbs.avaliacaodevspring.funcionario.dto.FuncionarioView;
import br.com.sbs.avaliacaodevspring.realizado.dto.ExameFuncionarioView;
import br.com.sbs.avaliacaodevspring.realizado.dto.NewExameFuncionarioForm;
import org.springframework.stereotype.Service;

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

    public ExameFuncionarioView save(NewExameFuncionarioForm newExameFuncionarioForm) {
        Exame exame = exameService.getById(newExameFuncionarioForm.exameId());
        Funcionario funcionario = funcionarioService.getById(newExameFuncionarioForm.funcionarioId());

        ExameFuncionario exameFuncionario = newExameFuncionarioForm.toEntity(exame, funcionario);
        exameFuncionarioRepository.save(exameFuncionario);

        return new ExameFuncionarioView(exameFuncionario);
    }

    public Collection<ExameFuncionarioView> findAll() {
        Collection<ExameFuncionario> examesFuncionarios = exameFuncionarioRepository.findAll();

        return examesFuncionarios.stream().map(ExameFuncionarioView::new).toList();
    }

    public ExameFuncionarioView findById(Long id) {
        ExameFuncionario exameFuncionario = exameFuncionarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id)));

        return new ExameFuncionarioView(exameFuncionario);
    }
}
