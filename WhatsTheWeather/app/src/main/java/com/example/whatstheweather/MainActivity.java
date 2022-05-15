package com.example.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    DownloadJSONContent task = null;
    HashMap<String, String> listWeather;
    EditText cityNameEditText;
    TextView afficheWeather;

 // generate weather from editText value
    public void getWeather(){
        listWeather = new HashMap<String,String>();
        task = new DownloadJSONContent();
        if(cityNameEditText.getText().toString() == ""){
            afficheWeather.setText("Veuillez entrer un nom d'une ville!");
        }else {

            try {
                listWeather = task.execute("https://api.openweathermap.org/data/2.5/weather?q=" + cityNameEditText.getText().toString() + "&lang=fr&appid=b3535e58ec1db4b024bc41c59d5b4914").get();
            } catch (Exception e) {
                //e.printStackTrace();
                afficheWeather.setText("Cet endroit n'existe pas !");
            }

        }
    }

 // Method button
    public void setListWeather(View view){
        afficheWeather.setVisibility(View.VISIBLE);
        getWeather();
        if(listWeather == null || listWeather.size()<=0){
            afficheWeather.setText("Ooops ! Entrez un nom d'une ville Correctement.");
            afficheWeather.setTextColor(getResources().getColor(R.color.red));
        }else {
            afficheWeather.setTextColor(getResources().getColor(R.color.white));
            for (Map.Entry<String, String> weather : listWeather.entrySet()) {
                System.out.println("KEY : " + weather.getKey() + " and value : " + weather.getValue());
                afficheWeather.setText(weather.getKey() + ": " + weather.getValue());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityNameEditText = (EditText) findViewById(R.id.editTextTextPersonName);
        afficheWeather = (TextView) findViewById(R.id.textView2);
        afficheWeather.setVisibility(View.INVISIBLE);


    }
}