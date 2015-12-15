package com.example.morganmonzingo.googlebooksapiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by morganmonzingo on 11/17/15.
 */
class BookStuff extends AsyncTask<String, Double, Book[]> {
    private Context context;
    private Activity parent;

    protected BookStuff(Context context, Activity parent){
        this.context = context;
        this.parent = parent;

    }

    @Override
    protected Book[] doInBackground(String... params) {
        //this needs to be the background work of getting the words and looking for them in the API
        try {
            Log.d("gavin", params[0]);
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            Log.d("The response code:", String.valueOf(responseCode));
            if(responseCode == HttpURLConnection.HTTP_OK) {

                String response = convertToString(connection.getInputStream());
                try {
                    JSONObject resultObject = new JSONObject(response);
                    JSONArray results = resultObject.getJSONArray("results");
                    Book[] bookItems = new Book[results.length()];

                    for (int i = 0; i < results.length(); i++) {
                        bookItems[i] = new Book(results.getJSONObject(i));
                        String collectionName = bookItems[i].collectionName;
                        Log.d("Jorge", collectionName);
                    }
                    return bookItems;
                }
                catch (Exception e){
                    Log.e("creating JSON","could not make object");
                }


            } else {
                Log.e("things", "Uh oh, URL is wrong!");
            }

        } catch(MalformedURLException e) {
            Log.e("malformed","Your URL is malformed!", e);

        } catch(IOException e) {
            Log.e("io exceptionr","Could not connect to the URL", e);
        }




        return null; //I guess you return the json objects?
    }

    @Override
    protected void onPostExecute(Book[] result){
        //there are UI things in here. Like putting the json things into the homepage
        Log.d("On Post Exe", result.toString());
        BookAdapter adapter = new BookAdapter(this.context, result);
        final ListView lv = (ListView) this.parent.findViewById(R.id.listText);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("do in Background BS", "In On Item Click");
                    Book lilBuk = (Book) lv.getItemAtPosition(position);
                    Intent intent = new Intent(context, singleView.class);
                    Log.d("do in Background BS", "BS. really.");
                    intent.putExtra("SELECTED", lilBuk.jsObj.toString());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("do in Background BS", "BS. really.");
                    context.startActivity(intent);
                }


            });
        lv.setAdapter(adapter);
    }
    public String convertToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(inputStream));

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line+"\n");
        }
        reader.close();
        return builder.toString();
    }

}