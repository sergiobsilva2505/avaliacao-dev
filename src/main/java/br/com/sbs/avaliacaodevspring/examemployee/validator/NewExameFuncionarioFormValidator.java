package br.com.sbs.avaliacaodevspring.examemployee.validator;

import br.com.sbs.avaliacaodevspring.examemployee.dto.NewExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.examemployee.ExameFuncionarioRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewExameFuncionarioFormValidator implements Validator {

    private final ExameFuncionarioRepository exameFuncionarioRepository;

    public NewExameFuncionarioFormValidator(ExameFuncionarioRepository exameFuncionarioRepository) {
        this.exameFuncionarioRepository = exameFuncionarioRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewExameFuncionarioForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewExameFuncionarioForm form = (NewExameFuncionarioForm) target;

        if (exameFuncionarioRepository.existsByExameAndFuncionarioAndDate(form.exameId(), form.funcionarioId())) {
            errors.reject("exame.funcionario.exists.already");
        }
    }
}
