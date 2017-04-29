package com.example.usgir.callerbuddy;

import android.media.AudioManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallListener;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by usgir on 3/26/2017.
 */

public class SinchCallListener implements CallListener {
    @Override
    public void onCallProgressing(Call call) {
        Log.d(TAG, "onCallProgressing: ");
    }

    @Override
    public void onCallEstablished(Call call) {
        Log.d(TAG, "onCallEstablished: ");
    }

    @Override
    public void onCallEnded(Call call) {
        Log.d(TAG, "onCallEnded: ");
    }

    @Override
    public void onShouldSendPushNotification(Call call, List<PushPair> list) {

    }
}
