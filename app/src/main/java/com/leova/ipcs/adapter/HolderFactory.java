package com.leova.ipcs.adapter;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import com.leova.ipcs.R;
import com.leova.ipcs.bean.TabBean;
import com.leova.ipcs.holder.CommonHolder;
import com.leova.ipcs.holder.TabHolder;

public final class HolderFactory {

    public static final int ITEM_TYPE_TAB_LIST = 1001;

    public static CommonHolder createHolder(LayoutInflater layoutInflater, int itemType,
                                            CommonAdapter.OnItemClicklistener onItemClicklistener) {
        if (itemType == HolderFactory.ITEM_TYPE_TAB_LIST) {
            View inflate = layoutInflater.inflate(R.layout.tab_item_view, null, false);
            TabHolder<TabBean> tabBeanTabHolder = new TabHolder<>(inflate);
            tabBeanTabHolder.setOnItemClicklistener(onItemClicklistener);
            return tabBeanTabHolder;
        }
        return null;
    }

}
