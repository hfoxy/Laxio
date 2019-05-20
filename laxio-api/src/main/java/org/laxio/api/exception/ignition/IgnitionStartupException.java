package org.laxio.api.exception.ignition;

public class IgnitionStartupException extends RuntimeException {

    public IgnitionStartupException(String message) {
        super(message);
    }

    public IgnitionStartupException(String message, Throwable cause) {
        super(message, cause);
    }

}
