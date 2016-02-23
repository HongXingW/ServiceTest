package com.whx.servicetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by whx on 2016/2/14.
 */
public class IntentServiceTest extends Activity{

    Button comServiceBtn,intentServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentservice);

        comServiceBtn = (Button)findViewById(R.id.start_service);
        intentServiceBtn = (Button)findViewById(R.id.start_intentservice);

        comServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentServiceTest.this,FirstService.class);
                startService(intent);
            }
        });
        intentServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentServiceTest.this,MyIntentService.class);
                startService(intent);
            }
        });
    }
}
