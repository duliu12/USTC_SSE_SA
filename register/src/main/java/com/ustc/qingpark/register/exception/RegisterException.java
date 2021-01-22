package com.ustc.qingpark.register.exception;

import com.ustc.qingpark.register.global.ErrorCode;
import org.slf4j.helpers.MessageFormatter;

public class RegisterException extends RuntimeException{
    private static final long serialVersionUID = 9215327422367136022L;
    private int code;
    private String snapshot;

    public RegisterException(ErrorCode errorCode, String snapshotFormat, Object... argArray) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public RegisterException(int code, String message, String snapshotFormat, Object... argArray) {
        super(message);
        this.code = code;
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getSnapshot() {
        return snapshot;
    }
}
