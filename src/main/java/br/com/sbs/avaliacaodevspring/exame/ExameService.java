package br.com.sbs.avaliacaodevspring.exame;

import br.com.sbs.avaliacaodevspring.exame.dto.ExameView;
import br.com.sbs.avaliacaodevspring.exame.dto.NewExameForm;
import br.com.sbs.avaliacaodevspring.exame.dto.UpdateExameForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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

    public ExameView findById(Long id) {
        Exame exame = exameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Exame não encontrado"));

        return new ExameView(exame);
    }

    @Transactional
    public ExameView update(UpdateExameForm updateExameForm) {
        Exame exame = exameRepository.findById(updateExameForm.rowid()).orElseThrow(() -> new IllegalArgumentException("Exame não encontrado"));
//        exame = exameRepository.save(exame);
        exame.merge(updateExameForm);

        return new ExameView(exame);
    }

    @Transactional
    public void deleteById(Long id) {
        Exame exame = exameRepository.getReferenceById(id);

        exameRepository.delete(exame);
    }
}
