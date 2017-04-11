package com.example.usgir.callerbuddy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.usgir.callerbuddy.Sinchservice.Sinchinterface;

public class MainActivity extends AppCompatActivity{
    String user;
    Sinchinterface sinchinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Onlogin(View view)
    {
        EditText edittext = (EditText)findViewById(R.id.editText);
        user = edittext.getText().toString();
        Intent intent = new Intent(this,Sinchservice.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            sinchinterface = (Sinchinterface)service;
            sinchinterface.Startclient(user);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
