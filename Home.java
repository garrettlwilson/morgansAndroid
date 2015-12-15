package com.example.morganmonzingo.googlebooksapiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.View;
import android.widget.*;
import android.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.*;
import java.io.*;



public class Home extends AppCompatActivity {
    Button sButton;
    Button lButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void search(View target) {
        Log.d("Gavin1", "in the search");
        final EditText sText = (EditText) findViewById(R.id.search_text);
        String desiredBook = sText.getText().toString();
        desiredBook = desiredBook.trim();
        desiredBook = desiredBook.replaceAll(" ", "+");

        if (desiredBook.isEmpty()) {
            sText.setError(getText(R.string.error));
        } else {
            new BookStuff(this.getApplicationContext(), this).execute("https://itunes.apple.com/search?term=" +desiredBook+ "&entity=audiobook&limit=5");
        }
    }

   public void iKnowWhatIWant(View target) {

       Log.d("Gavin2", "I know what I want");
       final EditText sText = (EditText) findViewById(R.id.search_text);
       String desiredBook = sText.getText().toString();
       desiredBook = desiredBook.trim();
       desiredBook = desiredBook.replaceAll(" ", "+");
       if (desiredBook.isEmpty()) {
           sText.setError(getText(R.string.error));
       } else {

           boolean checked = ((RadioButton) target).isChecked();
       // Check which radio button was clicked
           /* switch(target.getId()) {
                case R.id.radio_title:
                    if (checked){
                        desiredBook = "collectionName=" +desiredBook;
                        break;}
                case R.id.radio_author:
                    if (checked){
                        desiredBook = "collectionAuthor=" +desiredBook;
                        break;}
                case R.id.radio_genre:
                    if (checked){
                        desiredBook = "primaryGenreName=" +desiredBook;
                        break;}
            }*/
            new theLuckyButton(this.getApplicationContext(), this).execute("https://itunes.apple.com/search?" + desiredBook + "&entity=audiobook");

       }
   }

    }

