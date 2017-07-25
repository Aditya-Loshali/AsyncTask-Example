package com.example.adilos.asynctaskexample;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    public void downloadImage(View view){
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        DownloadTask task = new DownloadTask();
        Bitmap bmp = null;
        try {
            bmp = task.execute("https://pbs.twimg.com/profile_images/763046576050757632/1KdfLvwt.jpg").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bmp);
    }

    class DownloadTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //String result = "";
            URL url;
            //HttpURLConnection urlConnection = null;
            URLConnection urlConnection = null;
            try{
                url = new URL(params[0]);
                //urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection = url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                return BitmapFactory.decodeStream(in);
                /*InputStreamReader reader = new InputStreamReader(in);
                result = Integer.toString(reader.read()); // track progress of cursor*/
                /*while (data !=-1){                    //don't use while
                    char current = (char)data;
                    result += current;
                    data = reader.read();
                }*/

                //return result;
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
