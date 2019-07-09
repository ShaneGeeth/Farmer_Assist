package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.shamanland.fab.FloatingActionButton;

public class UserDashboard extends AppCompatActivity {
    ListView lv_languages;
    MenuListAdapter menuListAdapter;
    String[] menuItem = new String[] { "Weather",
            "Products",
            "Logout",
            "About",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        init();
        lv_languages.setAdapter(menuListAdapter);

        FloatingActionButton callBtn=findViewById(R.id.fabcall);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1920"));
                startActivity(intent);
            }
        });
    }

    private void init() {
        menuListAdapter = new MenuListAdapter(this,menuItem);
        lv_languages = (ListView) findViewById(R.id.lv_languages);

    }

    @Override
    public void onBackPressed()
    {

    }
}
