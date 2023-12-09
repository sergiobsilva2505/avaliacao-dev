package br.com.sbs.avaliacaodevspring.exame;

import br.com.sbs.avaliacaodevspring.exame.dto.ExameVoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ExameVoService {

    private final ExameVoRepository exameVoRepository;

    public ExameVoService(ExameVoRepository exameVoRepository) {
        this.exameVoRepository = exameVoRepository;
    }

    @Transactional
    public ExameVoDTO save(ExameVoDTO exameVoDTO) {
        ExameVo exameVo = exameVoDTO.toEntity();
        exameVo = exameVoRepository.save(exameVo);

        return exameVo.toDTO(exameVo);
    }

    public Collection<ExameVoDTO> findall() {
        Collection<ExameVo> examesVo = exameVoRepository.findAll();

        return examesVo.stream().map(ExameVoDTO::toDTO).collect(Collectors.toList());
    }

    public ExameVoDTO findById(Long id) {
        ExameVo exameVo = exameVoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Exame não encontrado"));

        return exameVo.toDTO(exameVo);
    }

    @Transactional
    public ExameVoDTO update(ExameVoDTO exameVoDTO) {
        ExameVo exameVo = exameVoDTO.toEntity();
        exameVo = exameVoRepository.save(exameVo);

        return exameVo.toDTO(exameVo);
    }
}
