package com.whx.servicetest;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by whx on 2016/2/19.
 */
public class AlarmChangeWallpaper extends Activity{

    AlarmManager alarmManager;
    Button start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);

        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);
        alarmManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
        //制定ChangeService组件
        Intent intent = new Intent(AlarmChangeWallpaper.this,ChangeService.class);

        final PendingIntent pi = PendingIntent.getService(AlarmChangeWallpaper.this,0,intent,0);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,0,5000,pi);
                start.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(AlarmChangeWallpaper.this,"成功",Toast.LENGTH_SHORT).show();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setEnabled(true);
                stop.setEnabled(false);
                //取消对pi的调度
                alarmManager.cancel(pi);
            }
        });
    }
}
