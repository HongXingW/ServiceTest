package com.whx.servicetest;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by whx on 2016/2/18.
 */
public class AlarmTest extends Activity{

    Button setTime;
    AlarmManager alarmManager;
    Calendar currentTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.common);

        setTime = (Button)findViewById(R.id.heimingdan);
        setTime.setText("设置时间");

        alarmManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cTime = Calendar.getInstance();
                new TimePickerDialog(AlarmTest.this, 0, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Intent intent = new Intent(AlarmTest.this,AlarmActivity.class);
                        //
                        PendingIntent pi = PendingIntent.getActivity(AlarmTest.this,0,intent,0);
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        //根据用户选择时间来设置Calendar对象
                        c.set(Calendar.HOUR, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        //设置AlarmManager将在Calendar对应的时间启动指定组件
                        alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);

                        Toast.makeText(AlarmTest.this,"闹铃设置成功",Toast.LENGTH_SHORT).show();
                    }
                },cTime.get(Calendar.HOUR_OF_DAY),cTime.get(Calendar.MINUTE),false).show();
            }
        });
    }
}
