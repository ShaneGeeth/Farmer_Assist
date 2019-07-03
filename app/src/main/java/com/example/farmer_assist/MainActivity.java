package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    UserHandler userHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userHandler=new UserHandler(this);
        //set input variables
        Button loginButton=(Button) findViewById(R.id.loginBtn);
        final EditText usernameET= (EditText) findViewById(R.id.loginUserNameEditText);
        final EditText passwordET=(EditText) findViewById(R.id.loginPasswordEdittext);

        final Context context=this;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userHandler.authUser(usernameET.getText().toString(),passwordET.getText().toString())!=null){
                    String name=userHandler.authUser(usernameET.getText().toString(),passwordET.getText().toString()).getFirst_name();
                    Toast.makeText(context, "Hi "+name , Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity.this,UserDashboard.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(context, "You insert wrong credential data " , Toast.LENGTH_LONG).show();
                }


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
