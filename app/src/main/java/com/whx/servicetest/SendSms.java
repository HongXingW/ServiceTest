package com.whx.servicetest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by whx on 2016/2/18.
 */
public class SendSms extends Activity{
    EditText number,content;
    Button sendBtn;
    SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmsg);

        smsManager = SmsManager.getDefault();
        number = (EditText)findViewById(R.id.contacts_number);
        content = (EditText)findViewById(R.id.message);
        sendBtn = (Button)findViewById(R.id.send);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个PendingIntent对象
                PendingIntent pi = PendingIntent.getActivity(SendSms.this,0,new Intent(),0);
                //发送短信
                smsManager.sendTextMessage(number.getText().toString(),null,content.getText()
                        .toString(),pi,null);
                //提示短信发送完成
                Toast.makeText(SendSms.this,"短信发送完成",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
