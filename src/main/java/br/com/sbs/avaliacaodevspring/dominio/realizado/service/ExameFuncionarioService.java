package br.com.sbs.avaliacaodevspring.dominio.realizado.service;

import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import br.com.sbs.avaliacaodevspring.dominio.exame.service.ExameService;
import br.com.sbs.avaliacaodevspring.dominio.realizado.entity.ExameFuncionario;
import br.com.sbs.avaliacaodevspring.dominio.realizado.repository.ExameFuncionarioRepository;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.entity.Funcionario;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.service.FuncionarioService;
import br.com.sbs.avaliacaodevspring.dominio.realizado.dto.ExameFuncionarioView;
import br.com.sbs.avaliacaodevspring.dominio.realizado.dto.NewExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.realizado.dto.UpdateExameFuncionarioForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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

//    @Transactional
//    public ExameFuncionarioView save(NewExameFuncionarioForm newExameFuncionarioForm) {
//        Exame exame = exameService.findById(newExameFuncionarioForm.exameId());
//        Funcionario funcionario = funcionarioService.getById(newExameFuncionarioForm.funcionarioId());
//
//        ExameFuncionario exameFuncionario = newExameFuncionarioForm.toEntity(exame, funcionario);
//        exameFuncionarioRepository.save(exameFuncionario);
//
//        return new ExameFuncionarioView(exameFuncionario);
//    }

    public Collection<ExameFuncionarioView> findAll() {
        Collection<ExameFuncionario> examesFuncionarios = exameFuncionarioRepository.findAll();

        return examesFuncionarios.stream().map(ExameFuncionarioView::new).toList();
    }

    public ExameFuncionarioView findById(Long id) {
        ExameFuncionario exameFuncionario = exameFuncionarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id)));

        return new ExameFuncionarioView(exameFuncionario);
    }

//    @Transactional
//    public ExameFuncionarioView update(Long id, UpdateExameFuncionarioForm updateExameFuncionarioForm) {
//        Exame exame = exameService.findById(updateExameFuncionarioForm.exameId());
//        Funcionario funcionario = funcionarioService.getById(updateExameFuncionarioForm.funcionarioId());
//
//        ExameFuncionario exameFuncionario = exameFuncionarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id)));
//        exameFuncionario.merge(exame, funcionario);
//
//        return new ExameFuncionarioView(exameFuncionario);
//    }

    @Transactional
    public void delete(Long id) {
        ExameFuncionario exameFuncionario = exameFuncionarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id)));
        exameFuncionarioRepository.delete(exameFuncionario);
    }
}
