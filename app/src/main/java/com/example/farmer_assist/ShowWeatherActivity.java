package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import static java.nio.file.Paths.get;

public class ShowWeatherActivity extends AppCompatActivity {
Spinner districtSpinner;
    String city="Colombo, LK";
    private static final String TAG = ShowWeatherActivity.class.getSimpleName() ;
    private ArrayList<Weather> weatherArrayList = new ArrayList<>();
    TextView tempretureTV;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);

        listView=findViewById(R.id.idListView2);
        tempretureTV=findViewById(R.id.tempretureTV);

        districtSpinner=findViewById(R.id.spinnerDistrict);


        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int pos, long id) {
                Toast.makeText(parent.getContext(),
                        "You Selected : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_SHORT).show();
                city= parent.getItemAtPosition(pos).toString();
                //url for get current weather by refering city
                URL weatherUrl = NetworkUtil2.buildUrlForWeather(city);
                //url for get current weather by refering city
                URL weatherUrl2 = NetworkUtill.buildUrlForWeather(city);
                new FetchWeatherDetails2().execute(weatherUrl);
                new FetchWeatherForecastDetails2().execute(weatherUrl2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //url for get current weather by refering city
        URL weatherUrl = NetworkUtil2.buildUrlForWeather(city);
        //url for get current weather by refering city
        URL weatherUrl2 = NetworkUtill.buildUrlForWeather(city);
        new FetchWeatherDetails2().execute(weatherUrl);
        new FetchWeatherForecastDetails2().execute(weatherUrl2);
        Log.i(TAG, "onCreate: weatherUrl: " + weatherUrl);
//        parseJSON();

    }

    private class FetchWeatherDetails2 extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL weatherUrl = urls[0];
            String weatherSearchResults2 = null;
            try{
                weatherSearchResults2 = NetworkUtil2.getResponseFromHttpUrl(weatherUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "doInBackground: weatherSearchResults: " + weatherSearchResults2);
            return weatherSearchResults2;
        }

        @Override
        protected void onPostExecute(String weatherSearchResults2) {
            String pattern = "###.##";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            Log.i(TAG, weatherSearchResults2);
            tempretureTV.setText(""+decimalFormat.format(parseJSON(weatherSearchResults2))+" C");
            super.onPostExecute(weatherSearchResults2);
        }
    }

    private double parseJSON(String weatherSearchResults2) {


        if(weatherSearchResults2 != null){
            try{
                JSONObject rootObject = new JSONObject(weatherSearchResults2);
                JSONObject main= (JSONObject) rootObject.get("main");
                String value=""+(main.get("temp"));

                return (Double.valueOf(value)-273.15);//Tempreture convert into the celcious
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private class FetchWeatherForecastDetails2 extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL weatherUrl = urls[0];
            String weatherSearchResults2 = null;
            try{
                weatherSearchResults2 = NetworkUtil2.getResponseFromHttpUrl(weatherUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "doInBackground: weatherSearchResults: " + weatherSearchResults2);
            return weatherSearchResults2;
        }

        @Override
        protected void onPostExecute(String weatherSearchResults2) {
            Log.i(TAG, weatherSearchResults2);
            super.onPostExecute(weatherSearchResults2);
            parseJSON2(weatherSearchResults2);
        }
    }

    private  ArrayList<Weather> parseJSON2(String weatherSearchResults){
        if(weatherArrayList != null){
            weatherArrayList.clear();
        }
        if(weatherSearchResults != null){
            try{

                JSONObject rootObject = new JSONObject(weatherSearchResults);
                JSONArray results = rootObject.getJSONArray("list");

                for (int i=0;i<results.length(); i++){
                    Weather weather = new Weather();
                    JSONObject resultsObj = results.getJSONObject(i);
                    String date = resultsObj.getString("dt_txt");
                    weather.setDate(date);
                    JSONObject temperatureObj = resultsObj.getJSONObject("main");
                    String minTemperature = temperatureObj.getString("temp_min");
                    weather.setMinTemp(minTemperature);
                    String maxTemperature = temperatureObj.getString("temp_max");
                    weather.setMaxTemp(maxTemperature);
                    JSONArray descArray = resultsObj.getJSONArray("weather");
                    JSONObject descObj=descArray.getJSONObject(0);
                    String description = descObj.getString("description");
                    weather.setDesc(description);

                    weatherArrayList.add(weather);
                }
                if(weatherArrayList != null){
                    WeatherAdapter weatherAdapter = new WeatherAdapter(this,weatherArrayList);
                    listView.setAdapter(weatherAdapter);
                }
                return weatherArrayList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}