package br.com.sbs.avaliacaodevspring.dominio.funcionario.service;

import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.NewFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.UpdateFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.entity.Funcionario;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.repository.FuncionarioRepository;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.FuncionarioView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Collection<FuncionarioView> findAll() {
        Collection<Funcionario> funcionarios = funcionarioRepository.findAll();

        return funcionarios.stream().map(FuncionarioView::new).toList();
    }

    @Transactional
    public FuncionarioView save(NewFuncionarioForm newFuncionarioForm) {
        Funcionario funcionario = newFuncionarioForm.toEntity();
        funcionario = funcionarioRepository.save(funcionario);

        return new FuncionarioView(funcionario);
    }

    public FuncionarioView findById(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));

        return new FuncionarioView(funcionario);
    }

    public Funcionario getById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));
    }

    @Transactional
    public FuncionarioView update(Long id, UpdateFuncionarioForm updateFuncionarioForm) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));
        funcionario.merge(updateFuncionarioForm);

        return new FuncionarioView(funcionario);
    }

    @Transactional
    public void deleteById(Long id) {
        Funcionario funcionario = funcionarioRepository.getReferenceById(id);

        funcionarioRepository.delete(funcionario);
    }
}