package com.leova.ipcs.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        }

        private void destory() {
            if (messengerService != null) {
                messengerService.clear();
            }
            removeCallbacksAndMessages(null);
        }
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
