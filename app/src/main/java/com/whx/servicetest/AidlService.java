package com.whx.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by whx on 2016/2/15.
 */
public class AidlService extends Service {

    private CatBinder binder;
    Timer timer = new Timer();
    String[] colors = new String[]{
            "红色",
            "黄色",
            "黑色"
    };
    double[] weights = new double[]{
            2.3,
            3.1,
            1.58
    };
    private String color;
    private double weight;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    private class CatBinder extends ICat.Stub{
        @Override
        public double getWeight() throws RemoteException {
            return weight;
        }

        @Override
        public String getColor() throws RemoteException {
            return color;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new CatBinder();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //随机改变Service组件内color，weight的值
                int rand = (int)(Math.random()*3);
                color = colors[rand];
                weight = weights[rand];
            }
        },0,800);
    }

    @Override
    public void onDestroy() {
        timer.cancel();
    }
}
