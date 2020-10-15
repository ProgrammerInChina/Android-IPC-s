package com.leova.ipcs.adapter;

import androidx.annotation.NonNull;

import com.leova.ipcs.holder.CommonHolder;

public enum HolderFactory {
    TAB;

//    public CommonHolder create() {
//        if (this == HolderFactory.TAB) {
//        }
//    }

    @NonNull
    @Override
    public String toString() {
        return "[name:" + name() + ",id:" + ordinal() + "]";
    }
}
