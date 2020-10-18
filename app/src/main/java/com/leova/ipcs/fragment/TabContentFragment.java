package com.leova.ipcs.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.leova.ipcs.R;
import com.leova.ipcs.callbacks.NotifyCallback;
import com.leova.ipcs.messenger.MessengerService;
import com.leova.ipcs.utils.MyUtils;

import java.lang.ref.WeakReference;

public class TabContentFragment extends BaseFragment implements View.OnClickListener {


    private static final String TAG = "tabFrg";
    Button btnMainToOther;
    Button btnBindRemoteService;
    Messenger messenger;
    Messenger myMessenger;

    RemoteHandler remoteHandler;


    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: " + name);
            notifyCallback.onNotifyLogChange("远程服务已连接");
            messenger = new Messenger(service);
            notifyCallback.onNotifyLogChange("已就绪");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            notifyCallback.onNotifyLogChange("远程服务已断开");
        }
    };

    public static TabContentFragment newInstance(NotifyCallback notifyCallback) {
        TabContentFragment fragment;
        fragment = new TabContentFragment();
        fragment.setNotifyCallback(notifyCallback);
        return fragment;
    }

    @Override
    public void onVisible() {
        Log.d(TAG, "onVisible: ------- ");

        btnMainToOther = mRootView.findViewById(R.id.btn_main_to_other);
        btnBindRemoteService = mRootView.findViewById(R.id.btn_bind_remote_service);

        btnMainToOther.setOnClickListener(this);
        btnBindRemoteService.setOnClickListener(this);


        remoteHandler = new RemoteHandler(this);
        myMessenger = new Messenger(remoteHandler);
    }

    @Override
    public void onUnVisible() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab_content;
    }


    public void onBtnMainToOtherClicked() {
        String processName = MyUtils.getProcessName(getActivity(), Process.myPid());
        String give = "礼物001";
        String messageStr = "Hi 我是进程 " + processName + " ，你好吗？这是我送去的礼物 " + give;
        Message message = Message.obtain(null, 0, 0, 0);
        message.replyTo = myMessenger;
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
            notifyCallback.onNotifyLogChange("发送失败！");
        }
        notifyCallback.onNotifyLogChange(messageStr);
    }

    public void onBtnBindRemoteServiceClicked() {
        notifyCallback.onNotifyLogChange("启动远程服务");
        Intent intent = new Intent(getActivity(), MessengerService.class);
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind_remote_service:
                onBtnBindRemoteServiceClicked();
                break;
            case R.id.btn_main_to_other:
                onBtnMainToOtherClicked();
                break;
        }

    }

    static class RemoteHandler extends Handler {
        WeakReference<TabContentFragment> fragmentWeakReference;

        public RemoteHandler(TabContentFragment messengerService) {
            this.fragmentWeakReference = new WeakReference<>(messengerService);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            TabContentFragment fragment = fragmentWeakReference.get();
            fragment.notifyCallback.onNotifyLogChange("服务端已收到消息");
        }

        private void destory() {
            if (fragmentWeakReference != null) {
                fragmentWeakReference.clear();
            }
            removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        remoteHandler.destory();
        getActivity().unbindService(serviceConnection);
    }
}
