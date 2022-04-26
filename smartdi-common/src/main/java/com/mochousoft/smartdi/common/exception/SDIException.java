package com.mochousoft.smartdi.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 系统全局异常类
 */
public class SDIException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;

    public SDIException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.toString() + " - " + errorMessage);
        this.errorCode = errorCode;
    }

    private SDIException(ErrorCode errorCode, String errorMessage, Throwable cause) {
        super(errorCode.toString() + " - " + getMessage(errorMessage) + " - " + getMessage(cause), cause);

        this.errorCode = errorCode;
    }

    public static SDIException asException(ErrorCode errorCode, String message) {
        return new SDIException(errorCode, message);
    }

    public static SDIException asException(ErrorCode errorCode, String message, Throwable cause) {
        if (cause instanceof SDIException) {
            return (SDIException) cause;
        }
        return new SDIException(errorCode, message, cause);
    }

    public static SDIException asException(ErrorCode errorCode, Throwable cause) {
        if (cause instanceof SDIException) {
            return (SDIException) cause;
        }
        return new SDIException(errorCode, getMessage(cause), cause);
    }

    private static String getMessage(Object obj) {
        if (obj == null) {
            return "";
        }

        if (obj instanceof Throwable) {
            StringWriter str = new StringWriter();
            PrintWriter pw = new PrintWriter(str);
            ((Throwable) obj).printStackTrace(pw);
            return str.toString();
            // return ((Throwable) obj).getMessage();
        } else {
            return obj.toString();
        }
    }
}
