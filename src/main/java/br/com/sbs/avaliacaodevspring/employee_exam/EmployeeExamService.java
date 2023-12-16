package br.com.sbs.avaliacaodevspring.employee_exam;

import br.com.sbs.avaliacaodevspring.employee.Employee;
import br.com.sbs.avaliacaodevspring.exam.Exam;
import br.com.sbs.avaliacaodevspring.exam.ExamService;
import br.com.sbs.avaliacaodevspring.employee.EmployeeService;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.EmployeeExamView;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.NewEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.UpdateEmployeeExamForm;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import br.com.sbs.avaliacaodevspring.relatorio.ReportByPeriod;
import br.com.sbs.avaliacaodevspring.relatorio.ReportByPeriodForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class EmployeeExamService {

    private final EmployeeExamRepository employeeExamRepository;
    private final ExamService examService;
    private final EmployeeService employeeService;

    public EmployeeExamService(EmployeeExamRepository employeeExamRepository, ExamService examService, EmployeeService employeeService) {
        this.employeeExamRepository = employeeExamRepository;
        this.examService = examService;
        this.employeeService = employeeService;
    }

    @Transactional
    public EmployeeExam save(NewEmployeeExamForm newEmployeeExamForm) {
        Exam exam = examService.findById(newEmployeeExamForm.examId());
        Employee employee = employeeService.findById(newEmployeeExamForm.employeeId());

        EmployeeExam employeeExam = newEmployeeExamForm.toEntity(employee, exam);
        employeeExamRepository.save(employeeExam);

        return employeeExam;
    }

    public Collection<EmployeeExamView> findAll() {
        Collection<EmployeeExam> employeeExamList = employeeExamRepository.findAll();

        return employeeExamList.stream().map(EmployeeExamView::new).toList();
    }

    public EmployeeExam findById(Long id) {
        return employeeExamRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Exam de funcionario não encontrado, id: %s".formatted(id)));
    }

    @Transactional
    public EmployeeExamView update(Long id, UpdateEmployeeExamForm updateEmployeeExamForm) {
        Exam exam = examService.findById(updateEmployeeExamForm.examId());
        Employee employee = employeeService.findById(updateEmployeeExamForm.employeeId());
        EmployeeExam employeeExam = employeeExamRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Exam de funcionario não encontrado, id: %s".formatted(id)));
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
