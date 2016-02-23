package com.whx.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by whx on 2016/2/14.
 */
public class MyIntentService extends IntentService{

    public MyIntentService(){
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        long endTime = System.currentTimeMillis()+20*1000;

        Log.d("---------", "耗时任务开始");
        while (System.currentTimeMillis()<endTime){
            synchronized (this){
                try{
                    wait(endTime-System.currentTimeMillis());
                }catch (Exception e){

                }
            }
        }
        Log.d("--------","耗时任务结束");
    }
}
