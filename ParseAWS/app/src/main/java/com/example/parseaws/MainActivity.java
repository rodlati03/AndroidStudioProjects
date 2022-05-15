package com.example.parseaws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /**
        // Get all values
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Score");
        //query.whereEqualTo("score",35);
        query.whereGreaterThan("score",50);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            Log.i("Username", object.getString("username"));
                            Log.i("Score", Integer.toString(object.getInt("score")));
                        }
                    }
                }
            }
        });

     **/

    /**
    // create User and password

        ParseUser user = new ParseUser();
        user.setUsername("rodlati");
        user.setPassword("rodlati123");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.i("Saved User", "User saved successfully");
                }
            }
        });


    // login user
        ParseUser.logInInBackground("rodlati", "rodlati123", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null ){
                    Log.i("Login", "Login Successfully");
                }else {
                    e.printStackTrace();
                }
            }
        });

     **/
     //ParseUser.logOut();

        if(ParseUser.getCurrentUser().getUsername() != null){
            System.out.println(ParseUser.getCurrentUser());
            Log.i("Already Signed In", ParseUser.getCurrentUser().getUsername());
        }else {
            Log.i("Please Login", "User not sign in");
        }


    }
}