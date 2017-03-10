package com.example.shankarpentyala.course_buddy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText UserName;
    EditText Password;
    Button Login;
    TextView textView, textView3;
    // TextView Errortext = (TextView) findViewById(R.id.text_Error);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Login");
        UserName = (EditText) findViewById(R.id.input_UserName);
        Password = (EditText) findViewById(R.id.input_password);
        Login = (Button) findViewById(R.id.Login);
        textView = (TextView) findViewById(R.id.textView);
        textView3 = (TextView) findViewById(R.id.textView3);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Avenir Roman.otf");
        Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Heavy.otf");
        UserName.setTypeface(font);
        Password.setTypeface(font);
        Login.setTypeface(font1);
        textView.setTypeface(font1);
        textView3.setTypeface(font1);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String S_username = UserName.getText().toString();
                String S_pwd = Password.getText().toString();

                if (S_username.isEmpty()) {
                    UserName.setError("Enter User Name");
                    //Toast.makeText(getApplicationContext(), "Enter User Name", Toast.LENGTH_SHORT).show();
                } else if (S_pwd.isEmpty()) {
                    Password.setError("Enter Password");
                    //Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                } else if (!S_username.equals("UMKC")) {
                    Toast.makeText(getApplicationContext(), "Invalid UserName", Toast.LENGTH_SHORT).show();
                } else if (!S_pwd.equals("UMKC")) {
                    Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent redirect = new Intent(MainActivity.this, Courses.class);
                    startActivity(redirect);
                }
            }
        });
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }
}





   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/