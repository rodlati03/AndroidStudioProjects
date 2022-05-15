package com.example.notessaved;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;
import java.util.HashSet;

public class SecondActivity extends AppCompatActivity {
    int noteId = -1;
    static SharedPreferences sharedPreferences;
    EditText multiAutoCompleteTextView;

    public String buttonSetText(int i){
        String afficheSharedPref = MainActivity.listNotes2.get(i);
        int it = 0;
        String textButton = "";
        if (afficheSharedPref.length() < 25) {
            textButton = afficheSharedPref;
        } else {
            for (it = 0; it < 25; it++) {
                textButton += afficheSharedPref.charAt(it);
            }
            textButton+=" ...";
        }
        // get rid of carriage return
        String newline = System.getProperty("line.separator");
        boolean hasNewline = textButton.contains(newline);
        if(hasNewline == true){
            textButton= textButton.replaceAll("\\n|\\r"," ");
        }
        return textButton;
    }

    void majEditText(){
        if (noteId != -1) {
            multiAutoCompleteTextView.setText(MainActivity.listNotes2.get(noteId));
        } else {
            multiAutoCompleteTextView.setText("Adding a note !");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    // initialize
        multiAutoCompleteTextView = (EditText) findViewById(R.id.editTextTextMultiLine);
        multiAutoCompleteTextView.setBackgroundColor(getResources().getColor(R.color.light_yellow));
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);
        sharedPreferences = getSharedPreferences("com.example.notessaved", Context.MODE_PRIVATE);

    // add change text listener
        multiAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.listNotes2.set(noteId,String.valueOf(charSequence));
                MainActivity.listNotes.set(noteId,buttonSetText(noteId));
                /*
                HashSet<String> set = new HashSet<String>(MainActivity.listNotes2);
                sharedPreferences.edit().putStringSet("NoteList", set).apply();
                 */
                MainActivity.arrayAdapter.notifyDataSetChanged();
                try{
                    SecondActivity.sharedPreferences.edit().putString("NoteList",ObjectSerializer.serialize(MainActivity.listNotes2)).apply();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        majEditText();
    }
}