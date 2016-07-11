package com.me;

import avro.shaded.com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by heifrank on 16/6/12.
 */
public class TestJavaBean {
    public class User{
        private String name;
        private int age;

        public User(String name, int age){
            this.name = name;
            this.age = age;
        }

        public int getAge(){
            return age;
        }

        public String getName(){
            return name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name){
            this.name = name;
        }
    }

    public class UUser{
        private int age;
        private String school;

        public UUser(int age, String school){
            this.age = age;
            this.school = school;
        }

        public int getAge(){
            return age;
        }


        public void setAge(int age) {
            this.age = age;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }
    }


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        TestJavaBean testJavaBean = new TestJavaBean();
        User user1 = testJavaBean.new User("songyang", 26);
        User user2 = testJavaBean.new User("liuzi", 25);

        UUser user3 = testJavaBean.new UUser(33, "home");

        BeanUtils.copyProperty(user1, "name", user2.getName());
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", "child");
        map.put("age", 1);
        map.put("what", 223);


        System.out.println("User1 name " + user1.getName());

        BeanUtils.populate(user2, map);
        System.out.println(user2.getName() + user2.getAge());

        BeanUtils.copyProperties(user2, user3);
        System.out.println(user2.getName() + user2.getAge());
    }
}
