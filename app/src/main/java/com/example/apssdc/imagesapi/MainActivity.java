package com.example.apssdc.imagesapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb;
    RecyclerView rv;
    ArrayList<ImageModel> imageModels;
    String imageurl="https://pixabay.com/api/?key=10850299-d0a7ce1a26a73b619a6ee831b&q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=findViewById(R.id.progress);
        rv=findViewById(R.id.recycler);
        imageModels=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        //new Imagetask().execute();
        rv.setAdapter(new ImageAdapter(this,imageModels));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            switch (item.getItemId()) {
                case R.id.flower:
                    Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    //String a="flowers";
                    new Imagetask().execute(imageurl+"flowers");
                    break;
                case R.id.colour:
                    Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    //String b="colours";
                    new Imagetask().execute(imageurl+"colours");
                    break;
                case R.id.animals:
                    Toast.makeText(this, item.getTitle(),Toast.LENGTH_SHORT).show();
                    //String c="animals";
                    new Imagetask().execute(imageurl+"animals");
                    break;
                case R.id.place:
                    Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    //String d="places";
                    new Imagetask().execute(imageurl+"place");
                    break;
                case R.id.choco:
                    Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    new Imagetask().execute(imageurl+"choco");
                    break;
                case R.id.doll:
                    Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    new Imagetask().execute(imageurl+"doll");
                    break;
                case R.id.food:
                    Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    new Imagetask().execute(imageurl+"food");
                    break;
                case R.id.paintings:
                    Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    new Imagetask().execute(imageurl+"paintings");
                    break;
            }
        }
        else{
            connected = false;
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    class Imagetask extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                Log.i("imageurl",url.toString());
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream =httpURLConnection.getInputStream();
                Scanner sn = new Scanner(inputStream);
                sn.useDelimiter("\\A");
                if (sn.hasNext())
                    return sn.next();
                else
                    return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pb.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("hits");
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject hits = jsonArray.getJSONObject(i);
                        String images = hits.getString("largeImageURL");
                        Log.i("imageurl", images);
                        String likes = hits.getString("likes");
                        Log.i("likes", likes);
                        String views = hits.getString("views");
                        Log.i("views", views);
                        String tags = hits.getString("tags");
                        Log.i("tags", tags);
                        imageModels.add(new ImageModel(images, likes, views, tags));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
            }

        }
    }
}
