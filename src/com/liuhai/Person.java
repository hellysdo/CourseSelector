package com.liuhai;

import java.io.Serializable;

/**
 * <p>ClassName: Person<p>
 * <p>Description:���Զ������л��ͷ����л�<p>
 * @author xudp
 * @version 1.0 V
 * @createTime 2014-6-9 ����02:33:25
 */
public class Person implements Serializable {

    /**
     * ���л�ID
     */
    private static final long serialVersionUID = -5809782578272943999L;
    private int age;
    private String name;
    private String sex;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}