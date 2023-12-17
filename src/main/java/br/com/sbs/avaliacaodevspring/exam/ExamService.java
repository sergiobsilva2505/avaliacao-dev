package br.com.sbs.avaliacaodevspring.exam;

import br.com.sbs.avaliacaodevspring.exam.dto.ExamView;
import br.com.sbs.avaliacaodevspring.exam.dto.NewExamForm;
import br.com.sbs.avaliacaodevspring.exam.dto.UpdateExamForm;
import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExamRepository;
import br.com.sbs.avaliacaodevspring.exception.BusinessException;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final EmployeeExamRepository employeeExamRepository;

    public ExamService(ExamRepository examRepository, EmployeeExamRepository employeeExamRepository) {
        this.examRepository = examRepository;
        this.employeeExamRepository = employeeExamRepository;
    }

    @Transactional
    public ExamView save(NewExamForm newExamForm) {
        Exam exam = newExamForm.toEntity();

        return new ExamView(examRepository.save(exam));
    }

    public Collection<ExamView> findAll() {
        Collection<Exam> exams = examRepository.findAll();

        return exams.stream().map(ExamView::new).collect(Collectors.toList());
    }

    public Exam findById(Long id) {
        return examRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Exame não encontrado id: %s".formatted(id)));
    }

    @Transactional
    public ExamView update(Long id, UpdateExamForm updateExamForm) {
        Exam exam = examRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Exame não encontrado id: %s".formatted(id)));
        exam.merge(updateExamForm);

        return new ExamView(exam);
    }

    @Transactional
    public void deleteById(Long id) {
        if (employeeExamRepository.existsByExam_Rowid(id)) {
            throw new BusinessException("Violação da integridade da base dados.");
        }
        examRepository.deleteById(id);
    }

}
