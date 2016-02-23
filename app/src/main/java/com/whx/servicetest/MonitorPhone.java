package com.whx.servicetest;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by whx on 2016/2/16.
 */
public class MonitorPhone extends Activity{

    TelephonyManager telephonyManager;
    TextView record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common);

        record = (TextView)findViewById(R.id.record);

        telephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);

        PhoneStateListener listener = new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state){
                    //无任何状态
                    case TelephonyManager.CALL_STATE_IDLE:
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        break;
                    //来电铃响
                    case TelephonyManager.CALL_STATE_RINGING:
                        record.setText(new Date() + "来电："+incomingNumber);
                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };

        telephonyManager.listen(listener,PhoneStateListener.LISTEN_CALL_STATE);
    }
}
