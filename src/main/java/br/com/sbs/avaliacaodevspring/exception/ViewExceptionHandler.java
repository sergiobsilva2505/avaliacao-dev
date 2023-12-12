package br.com.sbs.avaliacaodevspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ViewExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFoundExaception(ObjectNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("exception/page404");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }
}
