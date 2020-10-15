package com.leova.ipcs.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leova.ipcs.bean.BaseBean;

public abstract class CommonHolder<T extends BaseBean> extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected T mBean;

    public CommonHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void bind(T bean) {
        this.mBean = bean;
    }

    protected abstract void onDataSetChange();

    @Override
    public void onClick(View v) {

    }
}
