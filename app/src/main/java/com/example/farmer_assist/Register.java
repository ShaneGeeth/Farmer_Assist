package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {
    UserHandler db_user;
    Button reg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db_user=new UserHandler(this);

        reg_btn=findViewById(R.id.reg_btn);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setFirst_name("Harshan");
                user.setLast_name("Mad");
                user.setPassword("123");
                user.setAddress("12/2, hh sd, ssd");
                user.setEmail("lama@mail.com");
                user.setTelephone("23456");

                boolean res=db_user.createNewUser(user);
                Log.d("Save: ", "" + res);

            }
        });
    }
}
