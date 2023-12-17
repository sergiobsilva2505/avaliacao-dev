package br.com.sbs.avaliacaodevspring.employee_exam.validator;

import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExamRepository;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.NewEmployeeExamForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class NewEmployeeExamFormValidator implements Validator {

    private final EmployeeExamRepository employeeExamRepository;

    public NewEmployeeExamFormValidator(EmployeeExamRepository employeeExamRepository) {
        this.employeeExamRepository = employeeExamRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewEmployeeExamForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewEmployeeExamForm form = (NewEmployeeExamForm) target;

        if (employeeExamRepository.existsByExamIdAndEmployeeIdAndAccomplishedAt(form.employeeId(), form.examId(), LocalDateTime.parse(form.accomplishedAt()))) {
            errors.rejectValue("accomplishedAt", "", "O funcionário já realizou este exame hoje");
        }
    }
}
