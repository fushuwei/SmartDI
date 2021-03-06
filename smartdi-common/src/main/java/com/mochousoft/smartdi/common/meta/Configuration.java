package com.mochousoft.smartdi.common.meta;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mochousoft.smartdi.common.exception.SDIException;
import com.mochousoft.smartdi.common.exception.impl.GlobalErrorCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 配置信息
 */
public class Configuration {

    private JSONObject jConfig = null;

    /**
     * 无参构造，用于生成一个空配置对象
     */
    private Configuration() {
        this.jConfig = new JSONObject();
    }

    /**
     * 基于传入的json字符串构造一个配置对象
     *
     * @param json json字符串
     */
    private Configuration(final String json) {
        try {
            this.jConfig = JSON.parseObject(json);
        } catch (Exception e) {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_FORMAT_ERROR, e);
        }
    }

    /**
     * 生成一个空配置对象
     *
     * @return 配置对象
     */
    public static Configuration newInstance() {
        return new Configuration();
    }

    /**
     * 基于传入的json字符串构造一个配置对象
     *
     * @param json json字符串
     * @return 配置对象
     */
    public static Configuration newInstance(String json) {
        if (StringUtils.isBlank(json)) {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_NOT_FOUND);
        }

        return new Configuration(json);
    }

    /**
     * 取值
     *
     * @param path json寻址路径
     * @return Object
     */
    public Object get(final String path) {
        checkPath(path);

        // 判断json中是否包含该寻址路径
        if (!JSONPath.contains(this.jConfig, path)) {
            return null;
        }

        return JSONPath.eval(this.jConfig, path);
    }

    /**
     * 取值
     *
     * @param path json寻址路径
     * @return 字符串
     */
    public String getString(final String path) {
        Object obj = this.get(path);
        if (obj == null) {
            return null;
        }

        String result = String.valueOf(obj);
        if (StringUtils.isBlank(result)) {
            return null;
        }

        return result;
    }

    /**
     * 取值
     *
     * @param path         json寻址路径
     * @param defaultValue 默认值
     * @return 字符串
     */
    public String getString(final String path, final String defaultValue) {
        String result = this.getString(path);

        if (result == null) {
            result = defaultValue;
        }

        return result;
    }

    /**
     * 取值
     *
     * @param path json寻址路径
     * @return 整数
     */
    public Integer getInt(final String path) {
        String result = this.getString(path);

        if (result == null) {
            return null;
        }

        try {
            return Integer.valueOf(result);
        } catch (Exception e) {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_ERROR,
                    String.format("读取配置出错，路径 [%s] 对应的值 [%s] 无法转成整数类型", path, result), e);
        }
    }

    /**
     * 取值
     *
     * @param path         json寻址路径
     * @param defaultValue 默认值
     * @return 整数
     */
    public Integer getInt(final String path, final int defaultValue) {
        Integer result = this.getInt(path);

        if (result == null) {
            return defaultValue;
        }

        return result;
    }

    /**
     * 取值
     *
     * @param path json寻址路径
     * @return Long
     */
    public Long getLong(final String path) {
        String result = this.getString(path);

        if (result == null) {
            return null;
        }

        try {
            return Long.valueOf(result);
        } catch (Exception e) {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_ERROR,
                    String.format("读取配置出错，路径 [%s] 对应的值 [%s] 无法转成整数类型", path, result), e);
        }
    }

    /**
     * 取值
     *
     * @param path         json寻址路径
     * @param defaultValue 默认值
     * @return Long
     */
    public Long getLong(final String path, final long defaultValue) {
        Long result = this.getLong(path);

        if (result == null) {
            return defaultValue;
        }

        return result;
    }

    /**
     * 取值
     *
     * @param path json寻址路径
     * @return Double
     */
    public Double getDouble(final String path) {
        String result = this.getString(path);

        if (result == null) {
            return null;
        }

        try {
            return Double.valueOf(result);
        } catch (Exception e) {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_ERROR,
                    String.format("读取配置出错，路径 [%s] 对应的值 [%s] 无法转成浮点类型", path, result), e);
        }
    }

    /**
     * 取值
     *
     * @param path         json寻址路径
     * @param defaultValue 默认值
     * @return Double
     */
    public Double getDouble(final String path, final double defaultValue) {
        Double result = this.getDouble(path);

        if (result == null) {
            return defaultValue;
        }

        return result;
    }

    /**
     * 取值
     *
     * @param path json寻址路径
     * @return Boolean
     */
    public Boolean getBoolean(final String path) {
        String result = this.getString(path);

        if (result == null) {
            return null;
        } else if ("true".equalsIgnoreCase(result)) {
            return true;
        } else if ("false".equalsIgnoreCase(result)) {
            return false;
        } else {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_ERROR,
                    String.format("读取配置出错，路径 [%s] 对应的值 [%s] 无法转成布尔类型", path, result));
        }
    }

    /**
     * 取值
     *
     * @param path         json寻址路径
     * @param defaultValue 默认值
     * @return Boolean
     */
    public Boolean getBoolean(final String path, final boolean defaultValue) {
        Boolean result = this.getBoolean(path);

        if (result == null) {
            return defaultValue;
        }

        return result;
    }

    /**
     * 取值
     *
     * @param path json寻址路径
     * @return JSONObject
     */
    public JSONObject getJSONObject(final String path) {
        String result = this.getString(path);

        if (result == null) {
            return null;
        }

        try {
            return JSON.parseObject(result);
        } catch (Exception e) {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_ERROR,
                    String.format("读取配置出错，路径 [%s] 对应的值 [%s] 无法转成JSONObject类型", path, result), e);
        }
    }

    /**
     * 取值
     *
     * @param path         json寻址路径
     * @param defaultValue 默认值
     * @return JSONObject
     */
    public JSONObject getJSONObject(final String path, final JSONObject defaultValue) {
        JSONObject result = this.getJSONObject(path);

        if (result == null) {
            result = defaultValue;
        }

        return result;
    }

    /**
     * 取值
     *
     * @param path json寻址路径
     * @return JSONArray
     */
    public JSONArray getJSONArray(final String path) {
        String result = this.getString(path);

        if (result == null) {
            return null;
        }

        try {
            return JSON.parseArray(result);
        } catch (Exception e) {
            throw SDIException.newInstance(GlobalErrorCode.CONFIG_ERROR,
                    String.format("读取配置出错，路径 [%s] 对应的值 [%s] 无法转成JSONArray类型", path, result), e);
        }
    }

    /**
     * 取值
     *
     * @param path         json寻址路径
     * @param defaultValue 默认值
     * @return JSONArray
     */
    public JSONArray getJSONArray(final String path, final JSONArray defaultValue) {
        JSONArray result = this.getJSONArray(path);

        if (result == null) {
            result = defaultValue;
        }

        return result;
    }

    /**
     * 赋值
     *
     * @param path json寻址路径
     * @param obj  值
     */
    public void set(final String path, final Object obj) {
        checkPath(path);

        boolean isSuccess;

        try {
            isSuccess = JSONPath.set(this.jConfig, path, obj);
        } catch (Exception e) {
            throw SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR,
                    String.format("系统编程错误, 在配置信息中添加配置项 [%s: %s] 失败", path, obj), e);
        }

        if (!isSuccess) {
            throw SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR,
                    String.format("系统编程错误, 在配置信息中添加配置项 [%s: %s] 失败", path, obj));
        }
    }

    /**
     * 删除
     *
     * @param path json寻址路径
     */
    public void remove(final String path) {
        checkPath(path);

        // 判断json中是否包含该寻址路径
        if (!JSONPath.contains(this.jConfig, path)) {
            return;
        }

        boolean isSuccess;

        try {
            isSuccess = JSONPath.remove(this.jConfig, path);
        } catch (Exception e) {
            throw SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR,
                    String.format("系统编程错误, 在配置信息中删除配置项 [%s] 失败", path), e);
        }

        if (!isSuccess) {
            throw SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR,
                    String.format("系统编程错误, 在配置信息中删除配置项 [%s] 失败", path));
        }
    }

    /**
     * 校验json寻址路径
     *
     * @param path 路径地址
     */
    private void checkPath(final String path) {
        if (StringUtils.isBlank(path)) {
            throw SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR, "系统编程错误，json的寻址路径不能为空");
        }

        for (final String item : !".".equals(path) ? path.split("\\.", -1) : new String[]{}) {
            if (StringUtils.isBlank(item)) {
                throw SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR,
                        String.format("系统编程错误, json的寻址路径 [%s] 不合法, 路径层级之间不能是空白符", path));
            }
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this.jConfig, SerializerFeature.PrettyFormat);
    }
}
