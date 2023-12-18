package br.com.sbs.avaliacaodevspring.employee_exam;

import br.com.sbs.avaliacaodevspring.employee.Employee;
import br.com.sbs.avaliacaodevspring.employee.EmployeeRepository;
import br.com.sbs.avaliacaodevspring.employee.dto.EmployeeView;
import br.com.sbs.avaliacaodevspring.exam.Exam;
import br.com.sbs.avaliacaodevspring.exam.ExamRepository;
import br.com.sbs.avaliacaodevspring.exam.ExamService;
import br.com.sbs.avaliacaodevspring.employee.EmployeeService;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.EmployeeExamView;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.NewEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.UpdateEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import br.com.sbs.avaliacaodevspring.report.ReportByPeriod;
import br.com.sbs.avaliacaodevspring.report.ReportByPeriodForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class EmployeeExamService {

    private final EmployeeExamRepository employeeExamRepository;
    private final ExamRepository examRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeExamService(EmployeeExamRepository employeeExamRepository, ExamRepository examRepository, EmployeeRepository employeeRepository) {
        this.employeeExamRepository = employeeExamRepository;
        this.examRepository = examRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeExam save(NewEmployeeExamForm newEmployeeExamForm) {
        Exam exam = examRepository.findById(newEmployeeExamForm.examId())
                .orElseThrow(()-> new ObjectNotFoundException("Exame não encontrado, id: %s".formatted(newEmployeeExamForm.examId())));
        Employee employee = employeeRepository.findById(newEmployeeExamForm.employeeId())
                .orElseThrow(()-> new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(newEmployeeExamForm.examId())));

        EmployeeExam employeeExam = newEmployeeExamForm.toEntity(employee, exam);
        employeeExamRepository.save(employeeExam);

        return employeeExam;
    }

    public Collection<EmployeeExamView> findAll() {
        Collection<EmployeeExam> employeeExamList = employeeExamRepository.findAll();

        return employeeExamList.stream().map(EmployeeExamView::new).toList();
    }

    public EmployeeExamView findById(Long id) {
        EmployeeExam employeeExam = employeeExamRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id)));

        return new EmployeeExamView(employeeExam);
    }

    @Transactional
    public EmployeeExamView update(Long id, UpdateEmployeeExamForm updateEmployeeExamForm) {
        Exam exam = examRepository.findById(updateEmployeeExamForm.examId())
                .orElseThrow(()-> new ObjectNotFoundException("Exame não encontrado, id: %s".formatted(updateEmployeeExamForm.examId())));
        Employee employee = employeeRepository.findById(updateEmployeeExamForm.employeeId())
                .orElseThrow(()-> new ObjectNotFoundException("Funcionario não encontrado, id: %s".formatted(updateEmployeeExamForm.examId())));
        EmployeeExam employeeExam = employeeExamRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Exame de funcionario não encontrado, id: %s".formatted(id)));

        employeeExam.merge(employee, exam);

        return new EmployeeExamView(employeeExam);
    }

    @Transactional
    public void deleteById(Long id) {
        EmployeeExam employeeExam = employeeExamRepository.getReferenceById(id);
        employeeExamRepository.delete(employeeExam);
    }

    public List<ReportByPeriod> getReportByPeriod(ReportByPeriodForm reportByPeriodForm) {
        return employeeExamRepository.getReportByPeriod(reportByPeriodForm.initialDate(), reportByPeriodForm.finishDate());
    }
}
