package com.mochousoft.smartdi.common.type;

/**
 * 插件类型
 */
public enum PluginType {
    /**
     * 源读取插件
     */
    SOURCE_READER,

    /**
     * 目标读取插件
     */
    TARGET_READER,

    /**
     * 转换插件
     *
     * <p>用于对源表读出来的数据进行加工转换，例如：类型转换、格式转换、数据填充、代码表码值转换、数据脱敏等</p>
     */
    CONVERTER,

    /**
     * 对比插件
     *
     * <p>用于源表和目标表数据对比，识别数据变化状态，从而精准更新目标表数据</p>
     */
    COMPARER,

    /**
     * 目标新增数据插件
     */
    TARGET_INSERT_WRITER,

    /**
     * 目标修改数据插件
     */
    TARGET_UPDATE_WRITER,

    /**
     * 目标删除数据插件
     */
    TARGET_DELETE_WRITER,

    /**
     * 脏数据输出插件
     *
     * <p>用于记录操作目标表失败的数据，例如：违反目标表字段约束等</p>
     */
    DIRTY_WRITER,

    /**
     * 非规范数据输出插件
     *
     * <p>应用于数据质量检测场景，需配合质量检测规则一起使用，其目的是将不规范的数据写入第三方表</p>
     */
    NONSTANDARD_WRITER,

    /**
     * 拉链表新增数据插件
     */
    ZIPPER_INSERT_WRITER,

    /**
     * 拉链表修改数据插件
     */
    ZIPPER_UPDATE_WRITER,

    /**
     * 拉链表删除数据插件
     */
    ZIPPER_DELETE_WRITER,

    /**
     * 对比结果输出插件
     *
     * <p>该插件不会改变源表和目标表数据，而是将源与目标的对比结果写入第三方表</p>
     */
    COMPARE_WRITER;
}