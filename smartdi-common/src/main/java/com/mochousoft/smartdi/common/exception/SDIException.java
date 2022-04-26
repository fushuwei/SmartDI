package com.mochousoft.smartdi.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统全局异常类
 */
public class SDIException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private SDIException(ErrorCode code) {
        super(code.toString());
    }

    private SDIException(ErrorCode code, String message) {
        super(StringUtils.join(code, "\n\n", message));
    }

    private SDIException(ErrorCode code, String message, Throwable cause) {
        super(StringUtils.join(code, "\n\n", message), cause);
    }

    private SDIException(ErrorCode code, Throwable cause) {
        super(code.toString(), cause);
    }

    public static SDIException newInstance(ErrorCode code) {
        return new SDIException(code);
    }

    public static SDIException newInstance(ErrorCode code, String message) {
        return new SDIException(code, message);
    }

    public static SDIException newInstance(ErrorCode code, String message, Throwable cause) {
        if (cause instanceof SDIException) {
            return (SDIException) cause;
        }
        return new SDIException(code, message, cause);
    }

    public static SDIException newInstance(ErrorCode code, Throwable cause) {
        if (cause instanceof SDIException) {
            return (SDIException) cause;
        }
        return new SDIException(code, cause);
    }
}
