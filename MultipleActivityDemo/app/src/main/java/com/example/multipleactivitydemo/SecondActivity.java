package com.example.multipleactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    public void goToBack(View view){
        //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        //startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        //int age= intent.getIntExtra("age",0);
        String name = intent.getStringExtra("Name");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}