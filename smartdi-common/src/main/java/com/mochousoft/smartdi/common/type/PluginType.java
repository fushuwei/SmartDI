package com.mochousoft.smartdi.common.type;

/**
 * 插件类型
 */
public enum PluginType {
    // 源读取插件
    SOURCE_READER,

    // 目标读取插件
    TARGET_READER,

    // 转换插件
    CONVERTER,

    // 对比插件
    COMPARER,

    // 目标新增数据插件
    TARGET_INSERT_WRITER,

    // 目标修改数据插件
    TARGET_UPDATE_WRITER,

    // 目标删除数据插件
    TARGET_DELETE_WRITER,

    // 脏数据记录插件
    DIRTY_WRITER,

    // 非标准数据输出插件
    // todo

    // 拉链表新增数据插件
    ZIPPER_INSERT_WRITER,

    // 拉链表修改数据插件
    ZIPPER_UPDATE_WRITER,

    // 拉链表删除数据插件
    ZIPPER_DELETE_WRITER,

    // 对比结果输出插件
    COMPARE_WRITER;
}