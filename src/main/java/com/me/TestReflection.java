package com.me;

import java.lang.reflect.Field;

/**
 * Created by heifrank on 16/5/18.
 */
public class TestReflection {
    public static void main(String[] args) throws IllegalAccessException {
        UserBean bean = new UserBean();
        bean.setId(100);
        bean.setAddress("北京");
        Class userClass = bean.getClass();

        Field[] fs = userClass.getDeclaredFields();
        for(int i = 0; i < fs.length; i++){
            Field f = fs[i];
            f.setAccessible(true);
            Object val = f.get(bean);
            System.out.println("name: " + f.getName() + ", value: " + val);
        }
    }
}

class UserBean{
    private Integer id;

    private int age;

    private String name;

    private String address;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
