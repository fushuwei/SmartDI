package com.mochousoft.smartdi.common.base;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 基础类，重写了 java.lang.Object 类的三个方法，原则上建议所有java类都集成该类
 */
public class BaseObject {

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }
}
