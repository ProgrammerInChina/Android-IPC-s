package com.leova.ipcs.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leova.ipcs.adapter.CommonAdapter;
import com.leova.ipcs.bean.BaseBean;

public abstract class CommonHolder<T extends BaseBean> extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    protected T mBean;
    protected int mCurrentPosition;
    private CommonAdapter.OnItemClicklistener onItemClicklistener;

    public CommonHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void bind(T bean, int position) {
        this.mBean = bean;
        this.mCurrentPosition = position;
        onDataSetChange();
    }

    public void setOnItemClicklistener(CommonAdapter.OnItemClicklistener onItemClicklistener) {
        this.onItemClicklistener = onItemClicklistener;
    }

    protected abstract void onDataSetChange();

    @Override
    public void onClick(View v) {
        if (onItemClicklistener != null) {
            onItemClicklistener.onItemClick(v, mCurrentPosition);
        }
    }

}
