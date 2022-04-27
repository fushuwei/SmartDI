package com.mochousoft.smartdi.common.meta;

import com.alibaba.fastjson.JSONPath;
import com.mochousoft.smartdi.common.exception.SDIException;
import com.mochousoft.smartdi.common.exception.impl.GlobalErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void test1() {
        // 测试异常报错信息

        Configuration config = Configuration.newInstance(" ");

        System.out.println(config);
    }

    @Test
    void test2() {
        // 取值，参考文档：https://github.com/alibaba/fastjson/wiki/JSONPath

        String json = "{\"data\":{\"total\":2,\"records\":[{\"name\":\"张三\",\"age\":18},{\"name\":\"李四\",\"age\":20}]}}";

        Configuration config = Configuration.newInstance(json);

        System.out.println(config);
        System.out.println(config.get("$"));
        System.out.println(config.get("."));
        System.out.println(config.get("data.total"));
        System.out.println(config.get("data.records"));
        System.out.println(config.get("data.records.name"));
        System.out.println(config.get("data.records.name[0]"));
        System.out.println(config.get("data.records.name[1]"));
        System.out.println(config.get("data.records.age[0]"));
        System.out.println(config.get("data.records.age[1]"));
    }

    @Test
    void test3() {
        // 取值，精确返回类型

        String json = "{'score':99.99,'name':'','detail':{'yy':100,'sx':99.5},'age':18,'email':'[\"a\", \"b\"]', 'success': true, 'phone': 12345678910}";

        Configuration config = Configuration.newInstance(json);

        System.out.println(config.get("."));
        System.out.println(config.getString("name", "张三"));
        System.out.println(config.getInt("age"));
        System.out.println(config.getLong("phone"));
        System.out.println(config.getDouble("score"));
        System.out.println(config.getBoolean("success"));
        System.out.println(config.getJSONObject("detail"));
        System.out.println(config.getJSONObject("detail").getDouble("sx"));
        System.out.println(config.getJSONArray("email"));
    }
}