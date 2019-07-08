package com.example.farmer_assist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class WeatherAdapter extends ArrayAdapter<Weather> {
    public WeatherAdapter(@NonNull Context context, ArrayList<Weather> weatherArrayList) {
        super(context, 0, weatherArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String pattern = "###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        Weather weather = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.forecast_temp_list_item_layout, parent, false);
        }
        TextView dateTextView = convertView.findViewById(R.id.tv_time);
        TextView minTextView = convertView.findViewById(R.id.tv_mintemp);
        TextView maxTextView = convertView.findViewById(R.id.tv_maxtemp);
        TextView descTextView = convertView.findViewById(R.id.tv_desc);
        dateTextView.setText(weather.getDate());
        minTextView.setText("Min: "+decimalFormat.format(Double.parseDouble(weather.getMinTemp())-273.15)+" C");
        maxTextView.setText("Max: "+decimalFormat.format(Double.parseDouble(weather.getMaxTemp())-273.15)+" C");
        descTextView.setText(weather.getDesc());
        return convertView;
    }
}
