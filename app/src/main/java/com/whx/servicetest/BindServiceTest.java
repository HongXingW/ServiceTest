package com.whx.servicetest;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by whx on 2016/2/14.
 */
public class BindServiceTest extends Activity{

    Button bindBtn,unbindBtn,getServiceStatus;
    //保持所启动的Service的IBinder对象
    BindService.MyBinder binder;
    //定义一个ServiceConnection对象
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("---------","service connected");
            binder = (BindService.MyBinder)service;
        }
        //当该Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.d("---------","service disconnected");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);

        bindBtn = (Button)findViewById(R.id.bind);
        unbindBtn = (Button)findViewById(R.id.unbind);
        getServiceStatus = (Button)findViewById(R.id.getservice);

        final Intent intent = new Intent();
        intent.setAction("com.whx.service.BIND_SERVICE");
        //绑定指定的service
        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent,conn, Service.BIND_AUTO_CREATE);
            }
        });
        //解除绑定
        unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });
        getServiceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BindServiceTest.this,"Service的count值为"+binder.getCount(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
