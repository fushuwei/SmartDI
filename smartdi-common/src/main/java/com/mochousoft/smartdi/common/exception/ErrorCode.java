package com.mochousoft.smartdi.common.exception;

/**
 * 错误代码接口
 */
public interface ErrorCode {

    /**
     * 获取错误代码
     *
     * @return
     */
    String getCode();

    /**
     * 获取错误描述
     *
     * @return
     */
    String getDescription();

    /**
     * 重新toString方法
     *
     * @return
     */
    String toString();
}
