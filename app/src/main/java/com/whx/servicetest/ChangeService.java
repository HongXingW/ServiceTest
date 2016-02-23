package com.whx.servicetest;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by whx on 2016/2/19.
 */
public class ChangeService extends Service{

    int[] wallpapers = new int[]{
        R.mipmap.page1,R.mipmap.page2,R.mipmap.page3
    };

    WallpaperManager wallpaperManager;
    int current = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(current >= 3){
            current = 0;
        }
        try{
            wallpaperManager.setResource(wallpapers[current]);
        }catch (Exception e){
            Log.d("-------------",e.getMessage());
        }
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        wallpaperManager = WallpaperManager.getInstance(this);
    }
}
