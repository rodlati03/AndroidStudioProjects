package com.example.parseaws;

import android.app.Application;
import android.util.Log;

import com.parse.DeleteCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // e6uPFYEglpBE

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("myappID")
                .clientKey("KQi7T0XnZr3x")
                .server("http://ec2-3-86-230-170.compute-1.amazonaws.com/parse/")
                .build()
        );
    /**
        ParseObject object = new ParseObject("Score");
        object.put("username","Rachel");
        object.put("score",35);

        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException ex) {
                if (ex == null) {
                    Log.i("Parse Result", "Successful!");
                } else {
                    Log.i("Parse Result", "Failed" + ex.toString());
                }
            }
        });
    **/

    // get values by id
    /**
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Score");
        query.getInBackground("EABSjCHD5n", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {
                    String username = object.getString("username");
                    int score = object.getInt("score");
                    Log.i("username", username);
                    Log.i("score", Integer.toString(score));
                } else {
                    Log.i("Parse Result", "Failed" + e.toString());
                }
            }
        });
    **/


        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}

