package com.whx.servicetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startServiceBtn,bindServiceBtn,intentServiceBtn,yuanchengServiceBtn;
    Button simNetInfoBtn,phoneCallBtn,blackListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceBtn = (Button)findViewById(R.id.start_service_test);
        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StartServiceTest.class);
                startActivity(intent);
            }
        });

        bindServiceBtn = (Button)findViewById(R.id.bind_service_test);
        bindServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BindServiceTest.class);
                startActivity(intent);
            }
        });

        intentServiceBtn = (Button)findViewById(R.id.intent_service);
        intentServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IntentServiceTest.class);
                startActivity(intent);
            }
        });

        yuanchengServiceBtn = (Button)findViewById(R.id.start_aidl_service);
        yuanchengServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AidlClient.class);
                startActivity(intent);
            }
        });

        simNetInfoBtn = (Button)findViewById(R.id.sim_net_info);
        simNetInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TelephonyStatus.class);
                startActivity(intent);
            }
        });

        phoneCallBtn = (Button)findViewById(R.id.phone_call);
        phoneCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MonitorPhone.class);
                startActivity(intent);
            }
        });

        blackListBtn = (Button)findViewById(R.id.black_list);
        blackListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BlockMain.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.send_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendSms.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AlarmTest.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.change_wallpaper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AlarmChangeWallpaper.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
