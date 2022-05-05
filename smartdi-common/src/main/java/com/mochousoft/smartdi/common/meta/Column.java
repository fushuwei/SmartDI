package com.mochousoft.smartdi.common.meta;

import com.mochousoft.smartdi.common.base.BaseObject;
import com.mochousoft.smartdi.common.type.ColumnType;

/**
 * 列信息
 */
public class Column extends BaseObject {

    // 列类型，对应程序中定义的类型
    private ColumnType type;

    // 列类型，对应数据库中读取出来的未经加工的类型
    private String rawType;

    // 列值，对应数据库中读取出来的未经加工的数据
    private Object rawData;

    // 字节大小，对应数据库中读取出来的数据大小，单位：byte
    private int byteSize;

    public Column(ColumnType type, String rawType, Object rawData, int byteSize) {
        this.type = type;
        this.rawType = rawType;
        this.rawData = rawData;
        this.byteSize = byteSize;
    }

    public ColumnType getType() {
        return type;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }

    public String getRawType() {
        return rawType;
    }

    public void setRawType(String rawType) {
        this.rawType = rawType;
    }

    public Object getRawData() {
        return rawData;
    }

    public void setRawData(Object rawData) {
        this.rawData = rawData;
    }

    public int getByteSize() {
        return byteSize;
    }

    public void setByteSize(int byteSize) {
        this.byteSize = byteSize;
    }
}
