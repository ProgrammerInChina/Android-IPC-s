package com.leova.ipcs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leova.ipcs.bean.BaseBean;
import com.leova.ipcs.holder.CommonHolder;

import java.util.List;

public class CommonAdapter<T extends BaseBean> extends RecyclerView.Adapter<CommonHolder> {

    @LayoutRes
    private int mLayoutId;
    private List<T> mDatas;
    private int mItemType;

    public CommonAdapter() {
        this(0);
    }

    public CommonAdapter(@LayoutRes int mLayoutId) {
        this(mLayoutId, null);
    }

    public CommonAdapter(@LayoutRes int mLayoutId, List<T> mDatas) {
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas == null || mDatas.isEmpty()) {
            return 0;
        }
        T t = mDatas.get(position);
        return Math.max(t.getType(), 0);
    }

    @NonNull
    @Override
    public CommonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(mLayoutId, null, false);
//        HolderFactory
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (mDatas != null && !mDatas.isEmpty()) ? mDatas.size() : 0;
    }

    class Builder {
        @LayoutRes
        private int layoutId;
        private List<T> datas;
        private int itemType;

        public CommonAdapter<T> build() {
            mLayoutId = layoutId;
            mDatas = datas;
            mItemType = itemType;
            return CommonAdapter.this;
        }

        public Builder setLayoutId(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder setDatas(List<T> datas) {
            this.datas = datas;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }
    }
}
