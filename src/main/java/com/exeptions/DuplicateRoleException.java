package com.exeptions;

public class DuplicateRoleException extends Exception {

    public DuplicateRoleException() {
    }

    public DuplicateRoleException(String arg0) {
        super(arg0);
    }

    public DuplicateRoleException(Throwable arg0) {
        super(arg0);
    }

    public DuplicateRoleException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DuplicateRoleException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}