package com.yhr.course.course.contants;

/**
 * Created by Administrator on 2019/2/20.
 */
public enum RoleEnum {

    TEACHER("teacher"),STUDENT("student");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
