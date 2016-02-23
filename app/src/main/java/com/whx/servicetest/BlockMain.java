package com.whx.servicetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by whx on 2016/2/17.
 */
public class BlockMain extends Activity{

    ArrayList<String> blockList = new ArrayList<>();
    TelephonyManager telephonyManager;

    CustomPhoneCallListener cpListener;
    Button heimingdanBtn;

    public class CustomPhoneCallListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    if(isBlock(incomingNumber)){
                        try{
                            Method method = Class.forName("android.os.ServiceManager").
                                    getMethod("getService", String.class);
                            //获取远程TELEPHONY_SERVICE的IBinder对象的代理
                            IBinder binder = (IBinder)method.invoke(null,new Object[]{TELEPHONY_SERVICE});
                            //将IBinder对象的代理转换为ITelephony对象
                            ITelephony telephony = ITelephony.Stub.asInterface(binder);
                            //挂断电话
                            telephony.endCall();

                        }catch (Exception e){
                            Log.d("----------",e.getMessage());
                        }
                    }
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common);

        telephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        cpListener = new CustomPhoneCallListener();

        telephonyManager.listen(cpListener,PhoneStateListener.LISTEN_CALL_STATE);

        heimingdanBtn = (Button)findViewById(R.id.heimingdan);

        heimingdanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.
                        Phone.CONTENT_URI, null, null, null, null);
                BaseAdapter adapter = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return cursor.getCount();
                    }

                    @Override
                    public Object getItem(int position) {
                        return position;
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        cursor.moveToPosition(position);
                        CheckBox rb = new CheckBox(BlockMain.this);
                        //获取联系人的电话号码，并去掉中间的横线
                        String number = cursor.getString(cursor.getColumnIndex(ContactsContract
                                .CommonDataKinds.Phone.NUMBER)).replace("-", "");
                        rb.setText(number);
                        //如果该号码已加入到黑名单，默认勾选号码
                        if (isBlock(number)) {
                            rb.setChecked(true);
                        }
                        return rb;
                    }
                };
                View selectView = getLayoutInflater().inflate(R.layout.heimingdan_list, null);
                final ListView listView = (ListView) selectView.findViewById(R.id.heimingdan_list);
                listView.setAdapter(adapter);

                new AlertDialog.Builder(BlockMain.this).setView(selectView).setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                blockList.clear();
                                //遍历listview组件的每个列表项
                                for (int i = 0; i < listView.getCount(); i++) {
                                    CheckBox checkBox = (CheckBox) listView.getChildAt(i);
                                    //如果该列表项被勾选
                                    if (checkBox.isChecked()) {
                                        //添加该列表项的电话号码
                                        blockList.add(checkBox.getText().toString());
                                    }
                                }
                            }
                        }).show();
            }
        });
    }
    public boolean isBlock(String phone){
        for (String s : blockList){
            if(s.equals(phone)){
                return true;
            }
        }
        return false;
    }
}
