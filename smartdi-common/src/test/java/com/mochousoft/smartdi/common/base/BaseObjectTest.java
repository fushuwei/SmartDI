package com.mochousoft.smartdi.common.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BaseObjectTest {

    @Test
    void testInstanceof() {
        Object obj1 = "abc";
        Object obj2 = 100;

        System.out.println(obj1 instanceof String);

        System.out.println(obj1 instanceof Integer);

        System.out.println(String.class.isInstance(obj1));

        System.out.println(String.class.isInstance(obj2));
    }

    @Test
    void testEquals() {
        // 对象1
        TestRecord r1 = new TestRecord();
        r1.setId(1);
        r1.setFirstName('付');
        r1.setLastName((short) '威');
        r1.setFullName("付威");
        r1.setMobile(13112345678L);
        r1.setPic(new byte[10]);
        r1.setScore(100);
        r1.setIsFinish(true);

        // 对象2
        TestRecord r2 = new TestRecord();
        r2.setId(1);
        r2.setFirstName('付');
        r2.setLastName((short) '威');
        r2.setFullName("付威");
        r2.setMobile(13112345678L);
        r2.setPic(new byte[10]);
        r2.setScore(100);
        r2.setIsFinish(true);

        // 对比
        System.out.println(r1.equals(r2));
        System.out.println(r1.hashCode());
        System.out.println(r2.hashCode());
    }

    @Test
    void TestHashCode() {
        // 对象1
        TestRecord r1 = new TestRecord();
        r1.setId(1);
        r1.setFirstName('付');
        r1.setLastName((short) '威');
        r1.setFullName("付威");
        r1.setMobile(13112345678L);
        r1.setPic(new byte[10]);
        r1.setScore(100);
        r1.setIsFinish(true);

        // 对象2
        TestRecord r2 = new TestRecord();
        r2.setId(1);
        r2.setFirstName('付');
        r2.setLastName((short) '威');
        r2.setFullName("付威");
        r2.setMobile(13112345678L);
        r2.setPic(new byte[10]);
        r2.setScore(100);
        r2.setIsFinish(true);

        // 用于测试 HashMap 中对 hashcode 的使用场景
        Map<Object, Object> map1 = new HashMap<Object, Object>();
        map1.put(r1, "我是r1");
        map1.put(r2, "我是r2");

        Map<Object, Object> map2 = new HashMap<Object, Object>();
        map2.put("我是r1", r1);
        map2.put("我是r2", r2);

        System.out.println("输出1: " + r1.hashCode());
        System.out.println("输出2: " + r2.hashCode());
        System.out.println("输出3: " + map1);
        System.out.println("输出4: " + map1.get(r1));
        System.out.println("输出5: " + map2);

        /*
        以下三条数据是继承了 BaseObject 类，且重写了 hashCode 方法 的结果（重写后，虽然是不同的对象，但 hashcode 却相同）
        由于 r1 和 r2 的 hashcode 和 equals 都相同，因此 “输出3” 只有一个键值对（第二次put把第一次put的值覆盖了）
        HashMap中添加或查询对象的时候直接引用了已存在的 hashcode 对应的对象，从而大大提高效率，并减少内存占用

        输出1: 609978990
        输出2: 609978990
        输出3: {com.mochousoft.smartdi.common.base.BaseObjectTest$TestRecord@245b8a6e=我是r2}
        输出4: 我是r2
        输出5: {我是r1=com.mochousoft.smartdi.common.base.BaseObjectTest$TestRecord@245b8a6e, 我是r2=com.mochousoft.smartdi.common.base.BaseObjectTest$TestRecord@245b8a6e}



        以下三条数据是调用 Objects 类原生 hashCode 方法 的结果（测试时，删除 TestRecord 类后面的 extends BaseObject）
        两个对象的 hashcode 不一样

        输出1: 1011279482
        输出2: 208866101
        输出3: {com.mochousoft.smartdi.common.base.BaseObjectTest$TestRecord@c730b35=我是r2, com.mochousoft.smartdi.common.base.BaseObjectTest$TestRecord@3c46e67a=我是r1}
        输出4: 我是r1
        输出5: {我是r1=com.mochousoft.smartdi.common.base.BaseObjectTest$TestRecord@3c46e67a, 我是r2=com.mochousoft.smartdi.common.base.BaseObjectTest$TestRecord@c730b35}
         */

    }

    @Test
    void testToString() {
        // 对象1
        TestRecord r1 = new TestRecord();
        r1.setId(1);
        r1.setFirstName('付');
        r1.setLastName((short) '威');
        r1.setFullName("付威");
        r1.setMobile(13112345678L);
        r1.setPic(new byte[10]);
        r1.setScore(100);
        r1.setIsFinish(true);

        // 对象2
        TestRecord r2 = new TestRecord();
        r2.setId(1);
        r2.setFirstName('付');
        r2.setLastName((short) '威');
        r2.setFullName("付威");
        r2.setMobile(13112345678L);
        r2.setPic(new byte[10]);
        r2.setScore(100);
        r2.setIsFinish(true);

        System.out.println(r1);
        System.out.println(r2);

        // https://blog.csdn.net/weixin_43989293/article/details/114880572

        System.out.println(ToStringBuilder.reflectionToString(r1, ToStringStyle.JSON_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(r1, ToStringStyle.MULTI_LINE_STYLE));
    }

    static class TestRecord extends BaseObject {
        int id;
        char firstName;
        short lastName;
        String fullName;
        long mobile;
        byte[] pic;
        double score;
        boolean isFinish;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public char getFirstName() {
            return firstName;
        }

        public void setFirstName(char firstName) {
            this.firstName = firstName;
        }

        public short getLastName() {
            return lastName;
        }

        public void setLastName(short lastName) {
            this.lastName = lastName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public long getMobile() {
            return mobile;
        }

        public void setMobile(long mobile) {
            this.mobile = mobile;
        }

        public byte[] getPic() {
            return pic;
        }

        public void setPic(byte[] pic) {
            this.pic = pic;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public boolean getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(boolean isFinish) {
            isFinish = isFinish;
        }
    }
}