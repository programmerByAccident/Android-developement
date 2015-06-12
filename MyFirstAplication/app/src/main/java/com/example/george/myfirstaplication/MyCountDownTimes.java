package com.example.george.myfirstaplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;

/**
 * Created by George on 6/9/2015.
 */
public class MyCountDownTimes extends CountDownTimer {
    Context context;

    public MyCountDownTimes(long start, long interval){
        super(start, interval);
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        MediaPlayer mp = MediaPlayer.create(context, alarm);
        mp.start();
    }
}
