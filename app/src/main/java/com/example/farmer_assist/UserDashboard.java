package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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
