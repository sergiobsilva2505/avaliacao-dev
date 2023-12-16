package br.com.sbs.avaliacaodevspring.employee;

import br.com.sbs.avaliacaodevspring.employee.dto.FuncionarioView;
import br.com.sbs.avaliacaodevspring.employee.dto.NewFuncionarioForm;
import br.com.sbs.avaliacaodevspring.employee.dto.UpdateFuncionarioForm;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
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

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));
    }

    @Transactional
    public Funcionario update(Long id, UpdateFuncionarioForm updateFuncionarioForm) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));
        funcionario.merge(updateFuncionarioForm);

        return funcionario;
    }

    @Transactional
    public void deleteById(Long id, boolean isRequestedByAPI) {
        Funcionario funcionario = funcionarioRepository.getReferenceById(id);

        funcionarioRepository.delete(funcionario);
    }
}
