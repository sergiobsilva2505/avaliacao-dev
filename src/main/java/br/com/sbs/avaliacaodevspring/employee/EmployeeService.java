package br.com.sbs.avaliacaodevspring.employee;

import br.com.sbs.avaliacaodevspring.employee.dto.EmployeeView;
import br.com.sbs.avaliacaodevspring.employee.dto.NewEmployeeForm;
import br.com.sbs.avaliacaodevspring.employee.dto.UpdateEmployeeForm;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Collection<EmployeeView> findAll() {
        Collection<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(EmployeeView::new).toList();
    }

    @Transactional
    public EmployeeView save(NewEmployeeForm newEmployeeForm) {
        Employee employee = newEmployeeForm.toEntity();
        employee = employeeRepository.save(employee);

        return new EmployeeView(employee);
    }

    public EmployeeView findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));

        return new EmployeeView(employee);
    }

    @Transactional
    public EmployeeView update(Long id, UpdateEmployeeForm updateEmployeeForm) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));
        employee.merge(updateEmployeeForm);

        return new EmployeeView(employee);
    }

    @Transactional
    public void deleteById(Long id) {
        Employee employee = employeeRepository.getReferenceById(id);

        employeeRepository.delete(employee);
    }
}
