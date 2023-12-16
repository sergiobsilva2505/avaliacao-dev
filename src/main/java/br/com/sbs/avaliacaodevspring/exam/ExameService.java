package br.com.sbs.avaliacaodevspring.exam;

import br.com.sbs.avaliacaodevspring.exam.dto.ExameView;
import br.com.sbs.avaliacaodevspring.exam.dto.NewExameForm;
import br.com.sbs.avaliacaodevspring.exam.dto.UpdateExameForm;
import br.com.sbs.avaliacaodevspring.realizado.repository.ExameFuncionarioRepository;
import br.com.sbs.avaliacaodevspring.exception.BusinessException;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ExameService {

    private final ExameRepository exameRepository;
    private final ExameFuncionarioRepository exameFuncionarioRepository;

    public ExameService(ExameRepository exameRepository, ExameFuncionarioRepository exameFuncionarioRepository) {
        this.exameRepository = exameRepository;
        this.exameFuncionarioRepository = exameFuncionarioRepository;
    }

    @Transactional
    public ExameView save(NewExameForm newExameForm) {
        Exame exame = newExameForm.toEntity();

        return new ExameView(exameRepository.save(exame));
    }

    public Collection<ExameView> findAll() {
        Collection<Exame> examesVo = exameRepository.findAll();

        return examesVo.stream().map(ExameView::new).collect(Collectors.toList());
    }

    public Exame findById(Long id) {
        return exameRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Exame não encontrado id: %s".formatted(id)));
    }

    @Transactional
    public ExameView update(Long id, UpdateExameForm updateExameForm) {
        Exame exame = exameRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Exame não encontrado id: %s".formatted(id)));
        exame.merge(updateExameForm);

        return new ExameView(exame);
    }

    @Transactional
    public void deleteById(Long id) {
        if (exameFuncionarioRepository.existsByExame_Rowid(id)) {
            throw new BusinessException("Violação da integridade da base dados. Este recurso possui chave estrangeira.");
        }
        exameRepository.deleteById(id);
    }

}
