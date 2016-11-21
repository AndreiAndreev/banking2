package com.exeptions;

public class DuplicatePermissionException extends Exception {

    public DuplicatePermissionException() {
    }

    public DuplicatePermissionException(String arg0) {
        super(arg0);
    }

    public DuplicatePermissionException(Throwable arg0) {
        super(arg0);
    }

    public DuplicatePermissionException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DuplicatePermissionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}