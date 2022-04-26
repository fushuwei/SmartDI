package com.mochousoft.smartdi.common.meta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void test1() {
        // 测试异常报错信息
        Configuration config = Configuration.newInstance("{");
    }

    @Test
    void test2() {
        String json = "{\"data\":{\"total\":2,\"records\":[{\"name\":\"张三\",\"age\":18},{\"name\":\"李四\",\"age\":20}]}}";

        Configuration config = Configuration.newInstance(json);

        // 取值，参考文档：https://github.com/alibaba/fastjson/wiki/JSONPath
        System.out.println(config);
        System.out.println(config.get("$"));
        System.out.println(config.get("."));
        System.out.println(config.get("data.total"));
        System.out.println(config.get("data.records"));
        System.out.println(config.get("data.records.name"));
        System.out.println(config.get("data.records.name[0]"));
        System.out.println(config.get("data.records.name[1]"));
        System.out.println(config.get("data.records.age[0]"));

        // 赋值
        // TODO: 2022/4/27
    }
}