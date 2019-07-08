package com.example.farmer_assist;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
///data/2.5/forecast?q=London,us&appid=b6907d289e10d714a6e88b30761fae22
public class NetworkUtill{
    private static final String TAG = "NetworkUtils";
    private final static String WEATHERDB_BASE_URL=
            "http://api.openweathermap.org/data/2.5/forecast?";
    private final static String API_KEY="f49014010c0e881f28022c52917838d9";
    private final static String METRIC_VALUE="true";
    private final static String PARAM_API_KEY="apikey";
    private final static String PARAM_METRIC= "q";

    public static URL buildUrlForWeather(String city){
        Uri buildUri = Uri.parse(WEATHERDB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY,API_KEY)
                .appendQueryParameter(PARAM_METRIC,city)
                .build();
        URL url=null;
        try {
            url=new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "buildUrlForWeather: url: "+url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
