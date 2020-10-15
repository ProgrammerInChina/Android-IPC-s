package com.leova.ipcs.bean;

public class BaseBean {

    public int type;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "type=" + type +
                '}';
    }
}
