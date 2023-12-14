package br.com.sbs.avaliacaodevspring.dominio.funcionario.service;

import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.NewFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.UpdateFuncionarioForm;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.entity.Funcionario;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.repository.FuncionarioRepository;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.dto.FuncionarioView;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

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

    public Funcionario findById(Long id, boolean isRequestedByAPI) {
        Optional<Funcionario> possibleFuncionario = funcionarioRepository.findById(id);
        if (possibleFuncionario.isEmpty()) {
            if (isRequestedByAPI) {
                throw new ResourceNotFoundException("Funcionario não encontrado, id: %s".formatted(id));
            }
            throw new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(id));
        }

        return possibleFuncionario.get();
    }

    public Funcionario getById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));
    }

    @Transactional
    public Funcionario update(Long id, UpdateFuncionarioForm updateFuncionarioForm, boolean isRequestedByApi) {
        Optional<Funcionario> possibleFuncionario = funcionarioRepository.findById(id);

        if (possibleFuncionario.isEmpty()) {
            if (isRequestedByApi) {
                throw new ResourceNotFoundException("Funcionario não encontrado, id: %s".formatted(id));
            }
            throw new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(id));
        }
        Funcionario funcionario = possibleFuncionario.get();
        funcionario.merge(updateFuncionarioForm);

        return funcionario;
    }

    @Transactional
    public void deleteById(Long id, boolean isRequestedByAPI) {
        Funcionario funcionario = funcionarioRepository.getReferenceById(id);

        funcionarioRepository.delete(funcionario);
    }
}
