package com.mochousoft.smartdi.common.exception.impl;

import com.mochousoft.smartdi.common.exception.ErrorCode;
import org.apache.commons.lang3.StringUtils;

public enum GlobalErrorCode implements ErrorCode {

    // 全局默认错误代码，当出现位置错误时可以返回默认错误代码
    DEFAULT_ERROR("SDI-99999", "SDI引擎运行出错，请根据报错信息排查原因"),

    // 任务配置信息错误代码
    CONFIG_DEFAULT_ERROR("SDI-00100", "配置信息错误，请检查您在调用SDI引擎时提供的配置是否正确"),
    CONFIG_NOT_FOUND("SDI-00101", "配置信息不存在，请检查您在调用SDI引擎时是否提供了配置信息"),
    CONFIG_FORMAT_ERROR("SDI-00102", "配置信息错误，您在调用SDI引擎时提供的配置信息不是合法的JSON格式"),

    //
    ;

    private final String code;
    private final String description;

    GlobalErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return StringUtils.join(this.code, ": ", this.description);
    }
}
