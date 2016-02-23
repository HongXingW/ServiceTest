package com.whx.servicetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;

/**
 * Created by whx on 2016/2/18.
 */
public class AlarmActivity extends Activity{
    MediaPlayer alarmMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.none);

        alarmMusic = MediaPlayer.create(this, RingtoneManager.
                getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_RINGTONE));
        alarmMusic.setLooping(false);
        alarmMusic.start();

        new AlertDialog.Builder(AlarmActivity.this).setTitle("闹钟")
                .setMessage("闹钟响了")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alarmMusic.stop();
                        AlarmActivity.this.finish();
                    }
                }).show();
    }
}
