package com.example.instagramparselogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    EditText username;
    EditText password;
    Button signUpButton;
    TextView loginTextView;
    Boolean is_Login=false;

    public void showUserList(){
        String userM =ParseUser.getCurrentUser().getUsername();
        Intent intent = new Intent(getApplicationContext(), ListUserActivity.class);
        intent.putExtra("user", userM);
        startActivity(intent);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            allButtonAction();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.loginTextView){
            Log.i("Switch", "Switch SignUP button to LogIn");
            if(is_Login){
                is_Login=false;
                loginTextView.setText("or, Login");
                signUpButton.setText("Sign Up");
            }else {
                is_Login=true;
                loginTextView.setText("or, SignUp");
                signUpButton.setText("LogIn");
            }
        }else if(view.getId() == R.id.constrainteLayout || view.getId() == R.id.logoImageView){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

    }

    public void signUpClicked(){

        if(username.getText().toString().matches("") || password.getText().toString().matches("")){
            Toast.makeText(MainActivity.this, "A username or password is required", Toast.LENGTH_SHORT).show();
        }else{
            System.out.println(username.getText().toString());
            System.out.println(password.getText().toString());

            ParseUser user = new ParseUser();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null){
                        Log.i("sign up", "Sign up successfully");
                    }else{
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void logIn(){
        /**
        if(ParseUser.getCurrentUser().getUsername() !=null){
            Toast.makeText(MainActivity.this, "User "+ ParseUser.getCurrentUser().getUsername()+" already signed in", Toast.LENGTH_SHORT).show();
        }else {
            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(user != null){
                        Log.i("Sign Up", "user "+ParseUser.getCurrentUser().getUsername()+"is signed in");
                    }
                }
            });
        }
         **/


        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null){
                    Log.i("Login", "user "+ParseUser.getCurrentUser().getUsername()+" is signed in");
                    showUserList();
                    Toast.makeText(MainActivity.this,"User "+ParseUser.getCurrentUser().getUsername()+" is signed in", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void allButtonAction(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(is_Login){
                    //Toast.makeText(MainActivity.this,"Login action",Toast.LENGTH_SHORT).show();
                    logIn();
                }else {
                    //Toast.makeText(MainActivity.this,"SignUp action",Toast.LENGTH_SHORT).show();
                    signUpClicked();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.usernameEditText);
        password = (EditText) findViewById(R.id.passwordEditTextText);
        signUpButton = (Button) findViewById(R.id.sigUpButton);
        loginTextView = (TextView) findViewById(R.id.loginTextView);
        ImageView logoImageView = findViewById(R.id.logoImageView);
        ConstraintLayout constraintLayout = findViewById(R.id.constrainteLayout);
        //init
        loginTextView.setOnClickListener(this);
        constraintLayout.setOnClickListener(this);
        logoImageView.setOnClickListener(this);

        allButtonAction();
        if(ParseUser.getCurrentUser().getUsername() != null) {
            Toast.makeText(MainActivity.this,"USER1 from intent is "+ParseUser.getCurrentUser().getUsername(),Toast.LENGTH_SHORT).show();
            showUserList();
        }
        //ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}