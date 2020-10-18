package com.leova.ipcs.bean;

public class TabBean extends BaseBean {

    private String tabName;
    private boolean isSelectStatue;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public boolean isSelectStatue() {
        return isSelectStatue;
    }

    public void setSelectStatue(boolean selectStatue) {
        isSelectStatue = selectStatue;
    }


    @Override
    public String toString() {
        return "TabBean{" +
                "tabName='" + tabName + '\'' +
                ", isSelectStatue=" + isSelectStatue +
                '}';
    }
}
