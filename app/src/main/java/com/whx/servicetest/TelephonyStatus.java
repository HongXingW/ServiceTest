package com.whx.servicetest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whx on 2016/2/16.
 */
public class TelephonyStatus extends Activity{

    private ListView showList;
    String[] statusNames;
    ArrayList<String> statusValues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_sim);
        //获取系统的TelephonyManager对象
        TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        statusNames = new String[]{"设备编号","软件版本",
                "网络运营商代号","网络运营商名称","手机制式","设备当前位置",
                "sim卡的国别","sim卡序列号","sim卡状态"};

        statusValues.add(tManager.getDeviceId());
        statusValues.add(tManager.getDeviceSoftwareVersion()!=null?tManager.getDeviceSoftwareVersion():"未知");
        statusValues.add(tManager.getNetworkOperator());
        statusValues.add(tManager.getNetworkOperatorName());
        statusValues.add(tManager.getPhoneType()+"");
        statusValues.add(tManager.getCellLocation()!=null?tManager.getCellLocation().toString():"未知位置");
        statusValues.add(tManager.getSimCountryIso());
        statusValues.add(tManager.getSimSerialNumber());
        statusValues.add(tManager.getSimState()+"");

        showList = (ListView)findViewById(R.id.sim_net_list);

        ArrayList<Map<String,String>> status = new ArrayList<>();

        for(int i = 0;i<statusValues.size();i++){

            HashMap<String ,String> map = new HashMap<>();
            map.put("name",statusNames[i]);
            map.put("value",statusValues.get(i));
            status.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this,status,R.layout.sim_net_listitem,
                new String[]{"name","value"},new int[]{R.id.status,R.id.values});
        showList.setAdapter(adapter);
    }
}
