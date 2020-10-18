package com.leova.ipcs.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.leova.ipcs.R;
import com.leova.ipcs.adapter.CommonAdapter;
import com.leova.ipcs.bean.TabBean;

import java.util.List;

public class TabHolder<T extends TabBean> extends CommonHolder {
    private TextView tabNameTextView;
    private View selectView;

    public TabHolder(@NonNull View itemView) {
        super(itemView);
        tabNameTextView = itemView.findViewById(R.id.tv_tab_name);
        selectView = itemView.findViewById(R.id.v_tab_select_line);
    }

    @Override
    protected void onDataSetChange() {
        T tabBean = (T) mBean;
        tabNameTextView.setText(tabBean.getTabName());
        tabNameTextView.setTextColor(tabBean.isSelectStatue() ? 0xff000000 : 0xffc8c8c8);
//        selectView.setVisibility(tabBean.isSelectStatue() ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        CommonAdapter<TabBean> adapter = (CommonAdapter) getBindingAdapter();
        List<TabBean> datas = adapter.getDatas();
        for (TabBean data : datas) {
            data.setSelectStatue(false);
        }
        datas.get(mCurrentPosition).setSelectStatue(true);
        adapter.notifyDataSetChanged();
    }

}
