package com.example.notessaved;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> listNotes;
    public static ArrayList<String> listNotes2;
    ListView listView;
    //int listNoteSize = 0;
    //SharedPreferences sharedPreferences;
    static ArrayAdapter arrayAdapter;

    // ADD Button
    public void addNotesButton(){
            listNotes.add("Note Exemple");
            listNotes2.add("Note Exemple");
    }

    public void setUpdateList(){
        try{
            SecondActivity.sharedPreferences.edit().putString("NoteList",ObjectSerializer.serialize(MainActivity.listNotes2)).apply();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getUpdateList(){
        listNotes2.clear();
        try {
            listNotes2 = (ArrayList<String>) ObjectSerializer.deserialize(SecondActivity.sharedPreferences.getString("NoteList",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(listNotes2.size() > 0) {
            listNotes.clear();
            for (int i = 0; i < listNotes2.size(); i++) {
                listNotes.add(buttonSetText(i));
            }
            arrayAdapter.notifyDataSetChanged();
        }
        else {
            addNotesButton();
        }
    }

    public String buttonSetText(int i){
        String afficheSharedPref = listNotes2.get(i);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        SecondActivity.sharedPreferences = this.getSharedPreferences("com.example.notessaved", Context.MODE_APPEND);
        listNotes = new ArrayList<String>();
        listNotes2 = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listNotes);
        listView.setAdapter(arrayAdapter);
        buttonOnclickAction();
        getUpdateList();
        buttonLongOnClickAction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

 // ACTIONS SECTIONS METHODS ------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //is_Need_SetID = true;
        if (item.getItemId() == R.id.addingNotes){
            addNotesButton();
            arrayAdapter.notifyDataSetChanged();
            buttonOnclickAction();
            System.out.println("Size de listNotes "+ listNotes.size() );
            setUpdateList();
            return true;
        }else {
            return false;
        }
    }

    // button click action
    void buttonOnclickAction(){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                    intent.putExtra("noteId",i);
                    startActivity(intent);
                }
            });
    }

    // button long click action ==> Remove Button
    void buttonLongOnClickAction(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Removing note")
                        .setMessage("Are you that you want to remove this note " + i +" !?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int y) {
                                listNotes2.remove(i);
                                listNotes.remove(i);
                                arrayAdapter.notifyDataSetChanged();
                                setUpdateList();
                                Toast.makeText(MainActivity.this, "Note "+ i +" has been removed !", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return false;
            }
        });
    }
}