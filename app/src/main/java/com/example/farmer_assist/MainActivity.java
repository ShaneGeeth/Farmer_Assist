package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set input variables
        Button loginButton=(Button) findViewById(R.id.loginBtn);
        EditText usernameET= (EditText) findViewById(R.id.loginUserNameEditText);
        EditText passwordET=(EditText) findViewById(R.id.loginPasswordEdittext);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,UserDashboard.class);
                startActivity(intent);

            }
        });

        Button regbtn=(Button) findViewById(R.id.regactivity);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);

            }
        });
    }
}
