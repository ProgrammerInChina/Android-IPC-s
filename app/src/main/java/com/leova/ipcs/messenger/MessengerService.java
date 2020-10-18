package com.leova.ipcs.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leova.ipcs.utils.MyUtils;

import java.lang.ref.WeakReference;

public class MessengerService extends Service {

    private Messenger mMessenger;
    private MessengerHandler mMessengerHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mMessengerHandler = new MessengerHandler(this);
        mMessenger = new Messenger(mMessengerHandler);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }


    static class MessengerHandler extends Handler {
        WeakReference<MessengerService> messengerService;

        public MessengerHandler(MessengerService messengerService) {
            this.messengerService = new WeakReference<>(messengerService);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            Messenger clentMessenger = msg.replyTo;
            MessengerService messengerService = this.messengerService.get();
            messengerService.sayHi("msgStr");

            Message serviceMsg = Message.obtain();
            try {
                clentMessenger.send(serviceMsg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private void destory() {
            if (messengerService != null) {
                messengerService.clear();
            }
            removeCallbacksAndMessages(null);
        }
    }

    public String sayHi(String str) {
        String processName = MyUtils.getProcessName(this, Process.myPid());
        return "收到客户端的" + str + ",你好我是进程 ： " + processName;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //清除handler中父类引用防止内存泄漏
        if (mMessengerHandler != null) {
            mMessengerHandler.destory();
        }
    }
}
