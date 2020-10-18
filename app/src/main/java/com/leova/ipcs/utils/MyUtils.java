package com.leova.ipcs.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class MyUtils {


    public static final String getProcessName(Context context, int pid) {
        ActivityManager systemService = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = systemService.getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.isEmpty()) {
            return "进程";
        }

        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            if (runningAppProcess.pid == pid) {
                return runningAppProcess.processName;
            }
        }
        return "进程";
    }
}
