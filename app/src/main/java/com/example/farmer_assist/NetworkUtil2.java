package com.example.farmer_assist;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil2 {
    private static final String TAG = "NetworkUtils2";
    private final static String WEATHERDB_BASE_URL=
            "http://api.openweathermap.org/data/2.5/weather?";

    private final static String API_KEY="cbc4915049f71da44764d66f2d4b3768";

    private final static String METRIC_VALUE="true";

    private final static String PARAM_API_KEY="apikey";

    private final static String PARAM_METRIC= "q";

    public static URL buildUrlForWeather(String place){
        Uri buildUri = Uri.parse(WEATHERDB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY,(API_KEY))
                .appendQueryParameter(PARAM_METRIC,place)
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
