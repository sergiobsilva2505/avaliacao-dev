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
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

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
    public ExameFuncionario save(NewExameFuncionarioForm newExameFuncionarioForm, boolean isRequestedByAPI) {
        Exame exame = exameService.findById(newExameFuncionarioForm.exameId(), isRequestedByAPI);
        Funcionario funcionario = funcionarioService.findById(newExameFuncionarioForm.funcionarioId(), isRequestedByAPI);

        ExameFuncionario exameFuncionario = newExameFuncionarioForm.toEntity(exame, funcionario);
        exameFuncionarioRepository.save(exameFuncionario);

        return exameFuncionario;
    }

    public Collection<ExameFuncionarioView> findAll() {
        Collection<ExameFuncionario> examesFuncionarios = exameFuncionarioRepository.findAll();

        return examesFuncionarios.stream().map(ExameFuncionarioView::new).toList();
    }

    public ExameFuncionario findById(Long id, boolean isRequestedByAPI) {
        Optional<ExameFuncionario> possibleExameFuncionario = exameFuncionarioRepository.findById(id);
        if (possibleExameFuncionario.isEmpty()) {
            if (isRequestedByAPI) {
                throw new ResourceNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id));
            }
            throw new ObjectNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id));
        }

        return possibleExameFuncionario.get();
    }

    @Transactional
    public ExameFuncionarioView update(Long id, UpdateExameFuncionarioForm updateExameFuncionarioForm, boolean isRequestedByAPI) {
        Exame exame = exameService.findById(updateExameFuncionarioForm.exameId(), isRequestedByAPI);
        Funcionario funcionario = funcionarioService.findById(updateExameFuncionarioForm.funcionarioId(), isRequestedByAPI);
        Optional<ExameFuncionario> possibleExameFuncionario = exameFuncionarioRepository.findById(id);
        if (possibleExameFuncionario.isEmpty()) {
            if (isRequestedByAPI) {
                throw new ResourceNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id));
            }
            throw new ObjectNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id));
        }

        ExameFuncionario exameFuncionario = possibleExameFuncionario.get();
        exameFuncionario.merge(exame, funcionario);

        return new ExameFuncionarioView(exameFuncionario);
    }

    @Transactional
    public void delete(Long id, boolean isRequestesByAPI) {
        ExameFuncionario exameFuncionario = exameFuncionarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id)));
        exameFuncionarioRepository.delete(exameFuncionario);
    }
}
