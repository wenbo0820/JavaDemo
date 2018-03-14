package introspector;

import javax.script.ScriptEngine;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * Created by GongWenBo on 2018/3/2.
 */
public class IntrospectorDemo {

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("name", "wenbo");
        map.put("age", "19");
        map.put("school", "aaaaaa");

        /**
         * 通过内省机制为User赋值
         */
        TestUser user = new IntrospectorDemo().new TestUser();

        BeanInfo beanInfo = Introspector.getBeanInfo(TestUser.class);
        PropertyDescriptor[] pros = beanInfo.getPropertyDescriptors(); //不会包含school
        for (PropertyDescriptor p : pros) {
            if (map.containsKey(p.getName())) {
                p.getWriteMethod().invoke(user, map.get(p.getName()));
            }
        }
        System.out.println(user);

    }


    class TestUser {
        private String name;
        private String age;

        private String school;

        public void ss(String s) {
            school = s;
        }

        public void show() {
            System.out.println(school);
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "TestUser{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }
}
