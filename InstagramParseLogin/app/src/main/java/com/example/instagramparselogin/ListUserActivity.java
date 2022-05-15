package com.example.instagramparselogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListUserActivity extends AppCompatActivity {
ListView listView;
ArrayList<String> usernames;
String getUserFromIntent = null;

    public void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getPhoto();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode == RESULT_OK && data != null){
            Uri selectImage = data.getData();
            //Parse.initialize(ListUserActivity.this, "applicationId", "clientKey");
            try {
                //ParseUser.logIn("Sophtan", "Davely123");
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                ParseFile file = new ParseFile("jonathan.png", stream.toByteArray());
                //file.save();
                ParseObject imageObject = new ParseObject("Image");
                imageObject.put("image",file);
                Toast.makeText(ListUserActivity.this,"user is "+getUserFromIntent,Toast.LENGTH_SHORT).show();
                imageObject.put("username", getUserFromIntent);
                //imageObject.saveInBackground();

                imageObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Log.i("message OK", "image has been shared");
                           Toast.makeText(ListUserActivity.this,"image has been shared",Toast.LENGTH_SHORT).show();
                        }else {
                            Log.i("message KO", "There has been issue in Uploading");
                            Toast.makeText(ListUserActivity.this,"There has been issue in Uploading",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.share){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                getPhoto();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void getList(){
        usernames = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,usernames);

        final ParseQuery <ParseUser> query = ParseUser.getQuery();
        query.addAscendingOrder("username");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null && objects.size() > 0) {
                    for (ParseUser user : objects) {
                        usernames.add(user.getUsername());
                    }
                    listView.setAdapter(adapter);
                }
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        Intent intent = getIntent();
        getUserFromIntent = intent.getStringExtra("user");
        Toast.makeText(ListUserActivity.this,"USER from intent is "+getUserFromIntent,Toast.LENGTH_SHORT).show();

        listView = (ListView) findViewById(R.id.listUser);
        getList();

    }
}