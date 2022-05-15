package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double my_input, total;
    public void convert(View view){
        EditText convert = (EditText) findViewById(R.id.convertEditText);
        TextView textAffiche = (TextView) findViewById(R.id.afficheTextView);
        my_input = Double.parseDouble(convert.getText().toString());
        total = my_input * 0.83;
        String affiche_info = "La conversion en livre sterling est : "+ total+ " pounds";
        Log.i("Info", affiche_info);
        //Toast.makeText( this, affiche_info, Toast.LENGTH_SHORT).show();
        textAffiche.setText(String.valueOf(total)+" pounds");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}