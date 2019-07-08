package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    UserHandler db_user;
    Button reg_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Context context=this;

        //
        final EditText firstNameET=findViewById(R.id.first_name_et);
        final EditText lastNameET=findViewById(R.id.last_name_et);
        final EditText emailET=findViewById(R.id.email_et);
        final EditText passwordET=findViewById(R.id.password_et);
        final EditText addressET=findViewById(R.id.address_et);
        final EditText telephoneET=findViewById(R.id.telephone_et);

        db_user=new UserHandler(this);

        reg_btn=findViewById(R.id.reg_btn);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setFirst_name(firstNameET.getText().toString());
                user.setLast_name(lastNameET.getText().toString());
                user.setPassword(passwordET.getText().toString());
                user.setAddress(addressET.getText().toString());
                user.setEmail(emailET.getText().toString());
                user.setTelephone(telephoneET.getText().toString());

                boolean res=db_user.createNewUser(user);
                if(res)
                    Toast.makeText(context, "Registered Successfully!" , Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, "Failed!" , Toast.LENGTH_LONG).show();
                Log.d("Save: ", "" + res);

            }
        });
    }
}
