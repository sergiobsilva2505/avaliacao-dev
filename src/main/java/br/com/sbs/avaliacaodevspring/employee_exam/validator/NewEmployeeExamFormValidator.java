package br.com.sbs.avaliacaodevspring.employee_exam.validator;

import br.com.sbs.avaliacaodevspring.employee_exam.EmployeeExamRepository;
import br.com.sbs.avaliacaodevspring.employee_exam.dto.NewEmployeeExamForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

        if (employeeExamRepository.newQuery(form.employeeId(), form.examId(), form.accomplishedAt())) {
            errors.rejectValue("accomplishedAt", "employee.exam.already.exists");
        }
    }
}
