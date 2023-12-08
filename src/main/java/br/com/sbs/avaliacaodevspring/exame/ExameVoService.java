package br.com.sbs.avaliacaodevspring.exame;

import br.com.sbs.avaliacaodevspring.exame.dto.ExameVoView;
import br.com.sbs.avaliacaodevspring.exame.dto.NewExameVoForm;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExameVoService {

    private final ExameVoRepository exameVoRepository;

    public ExameVoService(ExameVoRepository exameVoRepository) {
        this.exameVoRepository = exameVoRepository;
    }

    public ExameVoView save(NewExameVoForm newExameVoForm) {
        ExameVo exameVo = newExameVoForm.toEntity();
        exameVo = exameVoRepository.save(exameVo);

        return new ExameVoView(exameVo);
    }

    public Collection<ExameVoView> findall() {
        List<ExameVo> examesVo = exameVoRepository.findAll();

        return examesVo.stream().map(ExameVoView::new).collect(Collectors.toList());
    }
}
