package com.whx.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by whx on 2016/2/13.
 */
public class FirstService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("---------","service is created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        long endTime = System.currentTimeMillis()+20*1000;

        Log.d("---------","耗时任务开始");
        while (System.currentTimeMillis()<endTime){
            synchronized (this){
                try{
                    wait(endTime-System.currentTimeMillis());
                }catch (Exception e){

                }
            }
        }
        Log.d("--------","耗时任务结束");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("-----------","service is destroyed");
    }
}
