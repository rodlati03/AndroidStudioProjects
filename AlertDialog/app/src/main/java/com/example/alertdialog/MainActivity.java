package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String language = "";
    SharedPreferences sharedPreferences = null;
    TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myText = (TextView) findViewById(R.id.textView);
        sharedPreferences = MainActivity.this.getSharedPreferences("com.example.alertdialog", Context.MODE_PRIVATE);
   /*

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you Sure !?")
                .setMessage("Do you definitely want to do this !?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "It's Done !", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    */
        // Alert Dialog declaration
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // set title
        builder.setTitle("Languages");

        // create languages list
        String[] languages = {"Créole", "Anglais","Francais","Espagnol"};

        // adding languages list into alertDialog
        builder.setItems(languages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                language = languages[i];
                Toast.makeText(MainActivity.this, language, Toast.LENGTH_SHORT).show();
                setLanguage(language);
            }
        });
        builder.setNegativeButton("Annuler",null);
        String lang = sharedPreferences.getString("Language","Error");
        if(lang == "Error"){
            builder.show();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu, menu);
        String lang = sharedPreferences.getString("Language","Error");
        System.out.println(lang);
        if(lang != "Error"){
            menu.add(lang);
        }
        return super.onCreateOptionsMenu(menu);
    }

    void setLanguage(String whichLanguage){
        sharedPreferences.edit().putString("Language", whichLanguage).apply();
        myText.setText(whichLanguage);
    }
    
}