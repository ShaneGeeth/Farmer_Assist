package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private UserHandler userHandler;
    private SharedPreferences loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggedUser = getSharedPreferences("logged_user_pref", Context.MODE_PRIVATE);
        if(loggedUser.contains("logged_user_id")){
            Intent intent=new Intent(MainActivity.this,UserDashboard.class);
            startActivity(intent);
        }

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
                    String user_id=userHandler.authUser(usernameET.getText().toString(),passwordET.getText().toString()).getId();

                    SharedPreferences.Editor editor = loggedUser.edit();
                    editor.putString("logged_user_id", user_id);
                    editor.commit();

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Farmer Assist");

        // set dialog message
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
