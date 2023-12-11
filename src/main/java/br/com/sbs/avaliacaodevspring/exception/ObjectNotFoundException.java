package br.com.sbs.avaliacaodevspring.exception;

public class ObjectNotFoundException  extends RuntimeException {

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
