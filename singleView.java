package com.example.morganmonzingo.googlebooksapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



import org.json.JSONObject;

/**
 * Created by morganmonzingo on 11/17/15.
 */
public class singleView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Book lilBuk = null;

        try {
            lilBuk = new Book( new JSONObject(intent.getExtras().getString("SELECTED")));
        }
        catch (Exception e){
            Log.e("new book creation",e.getMessage());
        }

        Log.d("more logging", intent.getExtras().getString("SELECTED"));
        setContentView(R.layout.iten_book1);

        TextView bookTitle = (TextView)findViewById(R.id.bookTitle);
        TextView bookGenre = (TextView)findViewById(R.id.bookGenre);
        TextView bookAuthor = (TextView)findViewById(R.id.bookAuthor);
        TextView bookDescription = (TextView)findViewById(R.id.bookDescription);
        ImageView bookPicture = (ImageView)findViewById(R.id.bookPicture);
        TextView bookPrice = (TextView)findViewById(R.id.bookPrice);

        if (lilBuk != null) {
            bookTitle.setText(lilBuk.collectionName);
            bookGenre.setText(lilBuk.primaryGenreName);
            bookAuthor.setText(lilBuk.artistName);
            bookDescription.setText(lilBuk.description);
            bookPrice.setText(lilBuk.collectionPrice);

            String imageURL =lilBuk.artworkUrl60;
            Glide.with(this).load(imageURL).into(bookPicture);


        }

    }
}
