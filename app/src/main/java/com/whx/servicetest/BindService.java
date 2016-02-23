package com.whx.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by whx on 2016/2/14.
 */
public class BindService extends Service{

    private int count;
    private boolean quit;
    private MyBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.d("----------","service is binded");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("----------", "service is created");
        new Thread(){
            @Override
            public void run() {
                while (!quit){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                    }
                    count ++;
                }
            }
        }.start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("-----------","service is unbinded");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        Log.d("-----------","service is destroyed");
    }

    public class MyBinder extends Binder{
        public int getCount(){
            return count;
        }
    }
}
