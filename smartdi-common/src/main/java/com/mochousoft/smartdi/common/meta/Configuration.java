package com.mochousoft.smartdi.common.meta;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.mochousoft.smartdi.common.exception.SDIException;
import com.mochousoft.smartdi.common.exception.impl.GlobalErrorCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 配置信息
 */
public class Configuration {

    private JSONObject jRoot = null;

    private Configuration() {
        jRoot = new JSONObject();
    }

    private Configuration(final String json) {
        try {
            jRoot = JSON.parseObject(json);
        } catch (Exception e) {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_FORMAT_ERROR, e);
        }
    }

    public static Configuration newInstance() {
        return new Configuration();
    }

    public static Configuration newInstance(String json) {

        if (StringUtils.isBlank(json)) {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_NOT_FOUND);
        }

        return new Configuration(json);
    }

    /**
     * 查询
     *
     * @param path
     * @return
     */
    public Object get(final String path) {
        checkPath(path);
        return JSONPath.eval(jRoot, path);
    }

    /**
     * 添加或修改
     *
     * @param path
     * @param obj
     */
    public void set(final String path, final Object obj) {

    }

    /**
     * 校验json的寻址路径
     *
     * @param path
     */
    private void checkPath(final String path) {
        if (StringUtils.isBlank(path)) {
            throw SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR, "系统编程错误，Json的寻址路径不能为空");
        }

        for (final String item : path.split("\\.")) {
            if (StringUtils.isBlank(item)) {
                throw SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR,
                        String.format("系统编程错误, Json的寻址路径 %s 不合法, 路径层次之间不能出现空白字符", path));
            }
        }

        if (!JSONPath.contains(this.jRoot, path)) {
            throw SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR,
                    String.format("系统编程错误, Json中不存在路径为 %s 的寻址路径", path));
        }
    }

    @Override
    public String toString() {
        return this.jRoot.toJSONString();
    }
}
