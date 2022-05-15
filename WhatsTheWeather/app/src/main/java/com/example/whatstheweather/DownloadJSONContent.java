package com.example.whatstheweather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

// Download API JSON content
public class DownloadJSONContent extends AsyncTask<String, Void, HashMap> {
    public HashMap<String, String> hashMap = null;

    @Override
    protected HashMap doInBackground(String... urls) {
        String result = "";
        int data = 0;
        URL url;
        HttpURLConnection connection;
        InputStreamReader reader = null;
        try {
            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            reader = new InputStreamReader(in);
            data = reader.read();

            while (data != -1){
                char current = (char) data;
                result+= current;
                data = reader.read();
            }
        // get a part of json with JSONObject and JSONArray methods
            try {
                // JSONObject return a list
                JSONObject jsonObject = new JSONObject(result);
                String weatherInfo = jsonObject.getString("weather");
                Log.i("weather info",weatherInfo);

                // adding the list into JSONArray
                JSONArray getJsonList = new JSONArray(weatherInfo);
                hashMap = new HashMap<String, String>();
                for(int i=0;i<getJsonList.length();i++){
                    JSONObject jsonPart = getJsonList.getJSONObject(i);
                    hashMap.put(jsonPart.getString("main"),jsonPart.getString("description"));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return hashMap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}