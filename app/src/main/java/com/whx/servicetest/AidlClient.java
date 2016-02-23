package com.whx.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by whx on 2016/2/15.
 */
public class AidlClient extends Activity{

    private ICat catService;
    private Button get;
    private EditText color,weight;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            catService = ICat.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            catService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        get = (Button)findViewById(R.id.get);
        color = (EditText)findViewById(R.id.icat_color);
        weight = (EditText)findViewById(R.id.icat_weight);

        Intent intent = new Intent();
        intent.setAction("com.whx.service.AIDL_SERVICE");
        //绑定远程service
        bindService(intent,conn,BIND_AUTO_CREATE);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    color.setText(catService.getColor());
                    weight.setText(catService.getWeight()+"");
                }catch (RemoteException e){
                    Log.d("------------",e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        this.unbindService(conn);
    }
}
