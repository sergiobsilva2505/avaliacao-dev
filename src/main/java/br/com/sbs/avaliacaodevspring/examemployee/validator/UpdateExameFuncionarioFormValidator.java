package br.com.sbs.avaliacaodevspring.examemployee.validator;

import br.com.sbs.avaliacaodevspring.examemployee.dto.UpdateExameFuncionarioForm;
import br.com.sbs.avaliacaodevspring.examemployee.ExameFuncionarioRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UpdateExameFuncionarioFormValidator implements Validator {

    private final ExameFuncionarioRepository exameFuncionarioRepository;

    public UpdateExameFuncionarioFormValidator(ExameFuncionarioRepository exameFuncionarioRepository) {
        this.exameFuncionarioRepository = exameFuncionarioRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UpdateExameFuncionarioForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateExameFuncionarioForm form = (UpdateExameFuncionarioForm) target;

        if (exameFuncionarioRepository.existsByExameAndFuncionarioAndDateAndIdNot(form.exameId(), form.funcionarioId(), form.rowid())) {
            errors.reject("exame.funcionario.exists.already");
        }
    }
}
