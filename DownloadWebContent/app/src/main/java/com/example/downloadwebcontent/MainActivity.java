package com.example.downloadwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button, button1, button2, button0;
    HashMap<String, String> map;
    int celebrityNumber =0;
    List<String> listName ;
    List<String> listImage;
    List<Button> listButton;
    String message ="";
    TextView textView;

    public void setCelebrityButton(){
        Random whichBut = new Random();
        int whichButton = whichBut.nextInt(2);

        for (int x=0; x<=2; x++) {
            button = listButton.get(x);
            if (Integer.parseInt(button.getTag().toString()) == whichButton) {
                Random celeb = new Random();
                celebrityNumber = celeb.nextInt(listImage.size());
                button.setText(listName.get(celebrityNumber));
            }else {
                Random fake = new Random();
                int fakeNumber = fake.nextInt(listImage.size());
                button.setText(listName.get(fakeNumber));
            }
        }
    // cas ou il y a un ou plusieurs Boutons ont le nom de célébrités
        for (int x=0; x<2; x++) {
            for(int y=1; y<=2; y++) {
                if (listButton.get(x).getText().toString() == listButton.get(y).getText().toString()) {
                    Random fake = new Random();
                    int fakeNumber = fake.nextInt(listImage.size());
                    int dupNumber = 0;
                    // get index of the duplicate button
                    int indexMe = listName.indexOf(listButton.get(y).getText().toString());
                    if (indexMe == fakeNumber && indexMe < listName.size()){
                        dupNumber = fakeNumber +1;
                    } else if (indexMe == fakeNumber && indexMe == listName.size()){
                        dupNumber = fakeNumber -5;
                    } else{
                        dupNumber = fakeNumber;
                    }
                    listButton.get(y).setText(listName.get(dupNumber));
                }
            }
        }
    }

    public void guessCelebrity(View view){
        button = (Button) view;
        textView.setTextSize(20);

        if(button.getText().toString() == listName.get(celebrityNumber).toString() ){
            message = "You win it !" ;
            textView.setTextColor(getResources().getColor(R.color.green));
        } else {
            textView.setTextColor(getResources().getColor(R.color.red));
            message = "Wrong, it was: "+listName.get(celebrityNumber).toString();
        }
        System.out.println(message);
        textView.setText(message);
        //textView.animate().alpha(0);
        //wrongOrGoodAnswer.animate().alpha(1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Quel est le nom de cette célébrité ci-dessus ?");
                textView.setTextSize(16);
                textView.setTextColor(getResources().getColor(R.color.black));
                if(message != "You win it !") {
                    setCelebrityButton();
                    getCelebImage(celebrityNumber);
                }
        }    }, 3000);

        if(message == "You win it !") {
            setCelebrityButton();
            getCelebImage(celebrityNumber);
        }

    }

    public void getCelebImage(int i){
        // Image Task
        ImageDownloader imageTask = new ImageDownloader();
        Bitmap myImage;
        try {
            //myImage = imageTask.execute("https://analyzingmarket"+listImage.get(i)+"jpg").get();
            myImage = imageTask.execute(listImage.get(i)).get();
            imageView.setImageBitmap(myImage);
            System.out.println(listImage.get(i));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    // init
        map = new HashMap<String, String>();
        listName = new ArrayList<String>();
        listImage = new ArrayList<String>();
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.buttonA);
        button1 = (Button) findViewById(R.id.buttonB);
        button2 = (Button) findViewById(R.id.buttonC);

        listButton = new ArrayList<Button>();

        listButton.add(button0);
        listButton.add(button1);
        listButton.add(button2);

    // Web Task
        WebContentDownloader webTask = new WebContentDownloader();
           String[] result = new String[100];
        try {
            result = webTask.execute("https://thepressfree.com/the-50-richest-celebrities-in-the-world-their-net-worth-2021/").get();
        }catch (Exception e){
            e.printStackTrace();
        }

        Pattern p = Pattern.compile("<h4>(.*?)</h4>");
        Pattern p2 = Pattern.compile("data-lazy-src=\"https://analyzingmarket(.*?)jpg\"");

        for (int i=0; i<result.length; i++) {
            Matcher m = p.matcher(result[i]);
            Matcher m2 = p2.matcher(result[i]);
            while(m.find()){
                listName.add(m.group(1));
            }
            while(m2.find()){
                listImage.add(m2.group(1));
            }
        }

        for (int i=0; i<listImage.size(); i++) {
            listImage.set(i,"https://analyzingmarket"+listImage.get(i)+"jpg");
        }

        setCelebrityButton();
        getCelebImage(celebrityNumber);


    }



    public class WebContentDownloader extends AsyncTask<String, Void, String []> {

        @Override
        protected String[] doInBackground(String... urls) {
            String[] getmeString;
            getmeString = new String[100];
            for(int i=0; i<getmeString.length; i++) {
                getmeString[i] = "";
            }

            int data = 0;
            URL url;
            HttpURLConnection connection;
            InputStreamReader reader = null;
            try {
                // put strings into URL
                url = new URL(urls[0]);
                // Create HTTP Connection with url
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                // Create instances InputStream and InputStreamReader
                InputStream in = connection.getInputStream();
                reader = new InputStreamReader(in);

            } catch (Exception e) {
                e.printStackTrace();
               // return "Failed";
            }

            //read HTML char by char
            try {
                data = reader.read();
                int x = 0;

                while(data != -1 ){
                    char theChar = (char)data;
                    if(getmeString[x].length() < 4900){
                        getmeString[x] += theChar;
                    }else {
                        x+=1;
                    }
                    data = reader.read();

                }

                return getmeString;
            }catch (Exception e){
                e.printStackTrace();
            }
            return getmeString;

        }
    }

    // Get image by its link

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }

}