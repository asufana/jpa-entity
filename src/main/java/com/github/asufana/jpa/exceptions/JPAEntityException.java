package com.github.asufana.jpa.exceptions;

import lombok.*;

import org.slf4j.*;

@Getter
public class JPAEntityException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(JPAEntityException.class);
    
    private final Exception exception;
    private final String message;
    
    public JPAEntityException(final String message) {
        this(null, message);
    }
    
    public JPAEntityException(final Exception exception) {
        this(exception, String.format("%s: %s",
                                      exception.getClass().getSimpleName(),
                                      exception.getMessage()));
    }
    
    public JPAEntityException(final Exception exception, final String message) {
        super();
        this.exception = exception;
        this.message = message;
        
        logger.error("JPAEntityException: {}", message);
    }
}
