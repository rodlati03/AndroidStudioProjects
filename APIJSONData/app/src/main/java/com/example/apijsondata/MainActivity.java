package com.example.apijsondata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadJSONContent task = new DownloadJSONContent();

        task.execute("https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=b3535e58ec1db4b024bc41c59d5b4914");

    }

// Download API JSON content
    public class DownloadJSONContent extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
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
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "KO";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // get a part of json with JSONObject and JSONArray methods
            try {
                // JSONObject return a list
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");
                Log.i("weather info",weatherInfo);

                // adding the list into JSONArray
                JSONArray getJsonList = new JSONArray(weatherInfo);

                for(int i=0;i<getJsonList.length();i++){
                    JSONObject jsonPart = getJsonList.getJSONObject(i);

                    Log.i("main",jsonPart.getString("main"));
                    Log.i("description",jsonPart.getString("description"));
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}