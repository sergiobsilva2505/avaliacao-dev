package br.com.sbs.avaliacaodevspring.employee_exam.validator;

import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExamRepository;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.UpdateEmployeeExamForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class UpdateEmployeeExamFormValidator implements Validator {

    private final EmployeeExamRepository employeeExamRepository;

    public UpdateEmployeeExamFormValidator(EmployeeExamRepository employeeExamRepository) {
        this.employeeExamRepository = employeeExamRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UpdateEmployeeExamForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateEmployeeExamForm form = (UpdateEmployeeExamForm) target;

        if (employeeExamRepository.existsByExamIdAndEmployeeIdAndAccomplishedAt(form.employeeId(), form.examId(), LocalDateTime.parse(form.accomplishedAt()), form.rowid())) {
            errors.rejectValue("existsAlready", "employee.exam.already.exists");
        }
    }
}
