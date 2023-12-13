package br.com.sbs.avaliacaodevspring.dominio.exame.service;

import br.com.sbs.avaliacaodevspring.dominio.exame.dto.ExameView;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.NewExameForm;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.UpdateExameForm;
import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import br.com.sbs.avaliacaodevspring.dominio.exame.repository.ExameRepository;
import br.com.sbs.avaliacaodevspring.dominio.exame.service.exception.DatabaseException;
import br.com.sbs.avaliacaodevspring.dominio.exame.service.exception.ResourceDatabaseException;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExameService {

    private final ExameRepository exameRepository;

    public ExameService(ExameRepository exameRepository) {
        this.exameRepository = exameRepository;
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

    public Exame findById(Long id, boolean isRequestedByAPI) {
        Optional<Exame> possibleExame = exameRepository.findById(id);
        if (possibleExame.isEmpty()) {
            if (isRequestedByAPI) {
                throw new ResourceNotFoundException("Exame não encontrado id: %s".formatted(id));
            }
            throw new ObjectNotFoundException("Exame não encontrado id: %s".formatted(id));
        }

        return possibleExame.get();
    }

    @Transactional
    public ExameView update(Long id, UpdateExameForm updateExameForm, boolean isRequestedByAPI) {
        Optional<Exame> possibleExame = exameRepository.findById(id);
        if (possibleExame.isEmpty()) {
            if (isRequestedByAPI) {
                throw new ResourceNotFoundException("Exame não encontrado id: %s".formatted(id));
            }
            throw new ObjectNotFoundException("Exame não encontrado id: %s".formatted(id));
        }
        Exame exame = possibleExame.get();
        exame.merge(updateExameForm);

        return new ExameView(exame);
    }

    @Transactional
    public void deleteById(Long id, boolean isRequestedByAPI) {
        String message = "Não pode ser deletado! Está associado a outros objetos";
        try {
            exameRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            if (isRequestedByAPI) {
                throw new ResourceDatabaseException(message);
            }
            throw new DatabaseException(message);
        }
    }

}
