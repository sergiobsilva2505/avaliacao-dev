package br.com.sbs.avaliacaodevspring.exam;

import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExamRepository;
import br.com.sbs.avaliacaodevspring.exam.dto.ExamView;
import br.com.sbs.avaliacaodevspring.exam.dto.NewExamForm;
import br.com.sbs.avaliacaodevspring.exam.dto.UpdateExamForm;
import br.com.sbs.avaliacaodevspring.exception.BusinessException;
import br.com.sbs.avaliacaodevspring.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<ExamView> findAll(PageRequest pageRequest) {
        Page<Exam> pagedExams = examRepository.findAll(pageRequest);

        return pagedExams.map(ExamView::new);
    }

    public ExamView findById(Long id) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Exame não encontrado id: %s".formatted(id)));

        return new ExamView(exam);
    }

    @Transactional
    public ExamView update(Long id, UpdateExamForm updateExamForm) {
        Exam exam = examRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Exame não encontrado id: %s".formatted(id)));
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
