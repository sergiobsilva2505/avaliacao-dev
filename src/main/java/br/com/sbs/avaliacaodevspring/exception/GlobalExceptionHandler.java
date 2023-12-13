package br.com.sbs.avaliacaodevspring.exception;

import br.com.sbs.avaliacaodevspring.dominio.exame.service.exception.DatabaseException;
import br.com.sbs.avaliacaodevspring.dominio.exame.service.exception.ResourceDatabaseException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private List<FieldMessage> getFieldMessages(MethodArgumentNotValidException exception) {
        List<FieldMessage> invalidParams = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach((error) -> {
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            invalidParams.add(new FieldMessage(error.getField(), errorMessage));
        });

        return invalidParams;
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFoundExaception(ObjectNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("exception/page404");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView dataIntegrityExaception(DatabaseException exception) {
        ModelAndView modelAndView = new ModelAndView("exception/page404");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ProblemDetail> validationError(MethodArgumentNotValidException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<FieldMessage> invalidParams = getFieldMessages(exception);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getLocalizedMessage());
        problemDetail.setTitle("Erro de validação");
        problemDetail.setDetail("Um ou mais campos estão com dados incorretos ou o dado já existe");
        problemDetail.setProperty("invalidParams", invalidParams);

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ProblemDetail> controllerNotfound(ResourceNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getLocalizedMessage()));
    }

//    @ExceptionHandler(ServiceNotFoundException.class)
//    ResponseEntity<ProblemDetail> serviceNotfound(ServiceNotFoundException exception) {
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getLocalizedMessage()));
//    }

    @ExceptionHandler(ResourceDatabaseException.class)
    ResponseEntity<ProblemDetail> databaseViolation(DatabaseException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getLocalizedMessage()));
    }
}
