package br.com.sbs.avaliacaodevspring.dominio.exame.service.exception;

public class ResourceDatabaseException extends RuntimeException {

    public ResourceDatabaseException(String message) {
        super(message);
    }

    public ResourceDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
