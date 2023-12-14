package br.com.sbs.avaliacaodevspring.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView notFoundException(ObjectNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("exception/page404");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView dataIntegrityViolationException(DataIntegrityViolationException exception) {
        ModelAndView modelAndView = new ModelAndView("exception/page404");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(BusinessException.class)
    public ModelAndView businessException(BusinessException exception) {
        ModelAndView modelAndView = new ModelAndView("exception/page400");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }


}
