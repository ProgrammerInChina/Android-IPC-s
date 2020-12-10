package com.leova.ipcs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leova.ipcs.R;
import com.leova.ipcs.adapter.CommonAdapter;
import com.leova.ipcs.adapter.HolderFactory;
import com.leova.ipcs.bean.TabBean;
import com.leova.ipcs.callbacks.NotifyCallback;
import com.leova.ipcs.fragment.BaseFragment;
import com.leova.ipcs.fragment.TabContentFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements NotifyCallback {

    private static final String TAG = "MainActivity";

    @BindView(R.id.rlv_tab_list)
    RecyclerView mTabRecyclerView;
    Unbinder bind;
    CommonAdapter<TabBean> adapter;
    TabContentFragment tabContentFragment;
    BaseFragment mCurrentFragment;
    @BindView(R.id.tv_log)
    TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        initData();
        mTabRecyclerView.setAdapter(adapter);
        mTabRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        adapter.setOnItemClicklistener((view, position) -> {
            changePage(position);
        });
        initFragment();
    }

    private void initData() {
        adapter = new CommonAdapter<>();
        String[] stringArray = getResources().getStringArray(R.array.tabs);
        for (int i = 0; i < stringArray.length; i++) {
            String tab = stringArray[i];
            TabBean tabBean = new TabBean();
            tabBean.setTabName(tab);
            tabBean.setSelectStatue(i == 0);
            tabBean.setType(HolderFactory.ITEM_TYPE_TAB_LIST);
            adapter.addDataBean(tabBean);
        }
    }

    private void initFragment() {

        mCurrentFragment = tabContentFragment = TabContentFragment.newInstance(this);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, tabContentFragment);
        transaction.show(tabContentFragment);
        transaction.commitNowAllowingStateLoss();

        onNotifyLogChange("请开始你的表演");
    }

    private void changePage(int position) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if (0 == position) {
            transaction.hide(mCurrentFragment);
            transaction.show(tabContentFragment);
            transaction.commit();
            mCurrentFragment = tabContentFragment;
        }
        Log.e(TAG, "call changePage method" );
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    @Override
    public void onNotifyLogChange(String logStr) {
        tvLog.post(() -> {
            String content = tvLog.getText().toString();
            tvLog.setText(content + "->" + logStr + "\n");
        });

    }
}