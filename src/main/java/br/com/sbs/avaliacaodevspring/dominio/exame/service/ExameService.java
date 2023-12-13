package br.com.sbs.avaliacaodevspring.dominio.exame.service;

import br.com.sbs.avaliacaodevspring.dominio.exame.dto.NewExameForm;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.ExameView;
import br.com.sbs.avaliacaodevspring.dominio.exame.dto.UpdateExameForm;
import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import br.com.sbs.avaliacaodevspring.dominio.exame.repository.ExameRepository;
import br.com.sbs.avaliacaodevspring.dominio.exame.service.exception.DatabaseException;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
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
        Collection<Exame> examesVo = (Collection<Exame>) exameRepository.findAll();

        return examesVo.stream().map(ExameView::new).collect(Collectors.toList());
    }

    public ExameView findById(Long id) {
        Exame exame = exameRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Exame não encontrado id: %s".formatted(id)));

        return new ExameView(exame);
    }

    public Exame getById(Long id) {
        return exameRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Exame não encontrado id: %s".formatted(id)));
    }

    @Transactional
    public ExameView update(Long id, UpdateExameForm updateExameForm) {
        Exame exame = exameRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Exame não encontrado id: %s".formatted(id)));
        exame.merge(updateExameForm);

        return new ExameView(exame);
    }

    @Transactional
    public void deleteById(Long id) {
//        Exame exame = exameRepository.getReferenceById(id);
        try {
            exameRepository.deleteById(id);
        } catch (Exception ex) {
//            throw new DatabaseException("Não pode ser deletado! Está associado a outros objetos");
            System.out.println("=========> Estou no catch");
            ex.printStackTrace();
        }
    }
}
