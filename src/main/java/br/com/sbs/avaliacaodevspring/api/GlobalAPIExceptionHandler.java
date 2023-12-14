package br.com.sbs.avaliacaodevspring.api;

import br.com.sbs.avaliacaodevspring.exception.BusinessException;
import br.com.sbs.avaliacaodevspring.exception.FieldMessage;
import br.com.sbs.avaliacaodevspring.exception.ResourceNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice("br.com.sbs.avaliacaodevspring.api")
public class GlobalAPIExceptionHandler {

    private final MessageSource messageSource;

    public GlobalAPIExceptionHandler(MessageSource messageSource) {
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ProblemDetail> dataIntegrityViolationException(DataIntegrityViolationException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getLocalizedMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ProblemDetail> businessException(BusinessException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ProblemDetail> exception(Exception exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage()));
    }
}
