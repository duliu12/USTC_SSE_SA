package com.ustc.qinpark.client.exception;

import com.ustc.qinpark.client.global.ErrorCode;
import org.slf4j.helpers.MessageFormatter;

public class ClientException extends RuntimeException {
    private static final long serialVersionUID = 9215327422367136022L;
    private int code;
    private String snapshot;

    public ClientException(ErrorCode errorCode, String snapshotFormat, Object... argArray) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public ClientException(int code, String message, String snapshotFormat, Object... argArray) {
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
