package com.leova.ipcs.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.leova.ipcs.callbacks.NotifyCallback;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private static final String TAG = "basefrg";
    protected View mRootView;
    protected boolean isInitDataed;
    protected NotifyCallback notifyCallback;
    Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null, false);
        }
        Log.d(TAG, "onCreateView: ------- ");
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        onVisible();
    }

    @Override
    public void onPause() {
        super.onPause();
        onUnVisible();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    public abstract void onVisible();

    public abstract void onUnVisible();

    public abstract @LayoutRes
    int getLayoutId();

    public void setNotifyCallback(NotifyCallback notifyCallback) {
        this.notifyCallback = notifyCallback;
    }

}
