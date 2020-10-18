package com.leova.ipcs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leova.ipcs.bean.BaseBean;
import com.leova.ipcs.holder.CommonHolder;

import java.util.ArrayList;
import java.util.List;

public class CommonAdapter<T extends BaseBean> extends RecyclerView.Adapter<CommonHolder> {


    private List<T> mDatas;
    private OnItemClicklistener onItemClicklistener;

    public CommonAdapter() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (mDatas == null || mDatas.isEmpty()) {
            return 0;
        }
        T t = mDatas.get(position);
        return Math.max(t.getType(), 0);
    }

    public List<T> getDatas() {
        return mDatas;
    }


    public void addDatas(List<T> datas) {
        if (datas != null && !datas.isEmpty()) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void addDataBean(T bean) {
        if (bean != null) {
            mDatas.add(bean);
            notifyItemChanged(mDatas.size() - 1);
        }
    }

    public void setOnItemClicklistener(OnItemClicklistener onItemClicklistener) {
        this.onItemClicklistener = onItemClicklistener;
    }

    @NonNull
    @Override
    public CommonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CommonHolder holder = HolderFactory.createHolder(layoutInflater,
                viewType, onItemClicklistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonHolder holder, int position) {
        holder.bind(mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return (mDatas != null && !mDatas.isEmpty()) ? mDatas.size() : 0;
    }

    public interface OnItemClicklistener {
        void onItemClick(View view, int position);
    }

}
