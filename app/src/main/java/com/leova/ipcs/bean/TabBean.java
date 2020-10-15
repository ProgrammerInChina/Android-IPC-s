package com.leova.ipcs.bean;

public class TabBean extends BaseBean {

    private String tabName;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    @Override
    public String toString() {
        return "TabBean{" +
                "tabName='" + tabName + '\'' +
                '}';
    }
}
