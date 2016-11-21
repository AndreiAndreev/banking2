package com.exeptions;

/**
 * @author Alexander Eveler
 */
public class RecordNotFoundException extends Exception {

    public RecordNotFoundException() {
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public RecordNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RecordNotFoundException(String message, Throwable throwable, boolean arg2, boolean arg3) {
        super(message, throwable, arg2, arg3);
    }
}
