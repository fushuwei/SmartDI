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

        String json = "{'score':99.99,'name':'','detail':{'yy':100,'sx':99.5},'age':18,'email':['a', 'b'], 'success': true, 'phone': 12345678910}";

        Configuration config = Configuration.newInstance(json);

        System.out.println(config);
        System.out.println(config.get("."));
        System.out.println(config.getString("."));
        System.out.println(config.getString("name", "张三"));
        System.out.println(config.getInt("age"));
        System.out.println(config.getLong("phone"));
        System.out.println(config.getDouble("score"));
        System.out.println(config.getBoolean("success"));
        System.out.println(config.getJSONObject("detail"));
        System.out.println(config.getInt("detail.yy"));
        System.out.println(config.getJSONObject("detail").getDouble("sx"));
        System.out.println(config.getJSONArray("email"));
    }

    @Test
    void test4() {
        // 赋值、删除

        String json = "{'name':'张三','detail':{'yy':100,'sx':99.5},'email':['a', 'b']}";

        Configuration config = Configuration.newInstance(json);

        // 输出原始json
        System.out.println(config.get("."));

        // 添加元素
        config.set("age", "18");
        config.set("detail.english", "A+");
        config.set("email[2]", "ccc");
        config.set("aa.bb.cc", 100);
        System.out.println(config.get("."));

        // 修改元素
        config.set("name", "张三丰");
        config.set("detail.yy", "99.99");
        config.set("email[1]", "bbb");
        System.out.println(config.get("."));

        // 删除元素 - 方式1
        config.set("age", null);
        System.out.println(config.get("."));

        // 删除元素 - 方式2
        config.remove("detail.yy");
        config.remove("abc");
        System.out.println(config.get("."));

        // 删除元素 - 报错场景
        // config.remove("email.aa");
        // System.out.println(config.get("."));

        System.out.println("\n" + config + "\n");
    }
}