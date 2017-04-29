package com.example.usgir.callerbuddy;

import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;
import java.util.List;
public class Sinchuser extends AppCompatActivity{
        SinchClient sinchClient;
        Call call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinchuser);
        Intent intent = getIntent();
        String UserID = intent.getStringExtra("Userid");
        TextView textView = (TextView)findViewById(R.id.textView2);
        textView.setText("Welcome "+UserID);
        android.content.Context context = getApplicationContext();
        sinchClient = Sinch.getSinchClientBuilder().context(context)
                .applicationKey("605fac01-e9f2-4dc1-8287-87088e854c32")
                .applicationSecret("vpxSxIbhNUiQLszJcdh/Zg==")
                .environmentHost("sandbox.sinch.com")
                .userId(UserID)
                .build();
          sinchClient.setSupportCalling(true);
        //sinchClient.setSupportManagedPush(true);
        sinchClient.startListeningOnActiveConnection();
        sinchClient.addSinchClientListener(new SinchClientListener() {
            @Override
            public void onClientStarted(SinchClient sinchClient) {
                Toast.makeText(getApplicationContext(),"Started the client",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClientStopped(SinchClient sinchClient) {
                Toast.makeText(getApplicationContext(),"Stopped the client",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClientFailed(SinchClient sinchClient, SinchError sinchError) {
                Toast.makeText(getApplicationContext(),"Failed to log the client",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRegistrationCredentialsRequired(SinchClient sinchClient, ClientRegistration clientRegistration) {
                Toast.makeText(getApplicationContext(),"Required to log the client",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLogMessage(int i, String s, String s1) {

            }
        });
        sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());
        sinchClient.start();
    }
    public void oncall(View view)
    {
        Button b = (Button)findViewById(R.id.button2);
        TextView textView = (TextView)findViewById(R.id.textView2);
        EditText text = (EditText)findViewById(R.id.editText2);
        if(call==null)
        {
        CallClient callClient = sinchClient.getCallClient();
            String friend = text.getText().toString();
        call = callClient.callUser(friend);
            b.setText("Hangup");
            textView.setText("Ringing");
            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        call.addCallListener(new SinchCallListener());
        }
        else
        {
            call.hangup();
            setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
            textView.setText("Disconnected the call");
            b.setText("Call");
        }
    }
}
class SinchCallClientListener extends Sinchuser implements CallClientListener {
    @Override
    public void onIncomingCall(CallClient callClient, Call incomingCall) {
        //Pick up the call!
        call = incomingCall;
        call.addCallListener(new SinchCallListener());
        setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        Button button = (Button)findViewById(R.id.button2);
        button.setText("Answer");
        call.answer();

    }
}
