package com.demorest.tools;

/**
 * Created by marcus on 14/12/2016.
 */
public class SqlSessionException extends Exception {
    public SqlSessionException(String message) {
        super(message);
    }

    public SqlSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlSessionException(Throwable cause) {
        super(cause);
    }
}
