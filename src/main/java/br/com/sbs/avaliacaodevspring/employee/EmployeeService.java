package br.com.sbs.avaliacaodevspring.employee;

import br.com.sbs.avaliacaodevspring.employee.dto.EmployeeView;
import br.com.sbs.avaliacaodevspring.employee.dto.NewEmployeeForm;
import br.com.sbs.avaliacaodevspring.employee.dto.UpdateEmployeeForm;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Page<EmployeeView> findAll(PageRequest pageRequest) {
        Page<Employee> pagedEmployees = employeeRepository.findAll(pageRequest);

        return pagedEmployees.map(EmployeeView::new);
    }

    @Transactional
    public EmployeeView save(NewEmployeeForm newEmployeeForm) {
        Employee employee = newEmployeeForm.toEntity();
        employee = employeeRepository.save(employee);

        return new EmployeeView(employee);
    }

    public EmployeeView findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));

        return new EmployeeView(employee);
    }

    @Transactional
    public EmployeeView update(Long id, UpdateEmployeeForm updateEmployeeForm) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(id)));
        employee.merge(updateEmployeeForm);

        return new EmployeeView(employee);
    }

    @Transactional
    public void deleteById(Long id) {
        Employee employee = employeeRepository.getReferenceById(id);

        employeeRepository.delete(employee);
    }
}
