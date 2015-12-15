package com.example.morganmonzingo.googlebooksapiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.util.*;
import android.widget.TextView;
import com.bumptech.glide.Glide;


import org.w3c.dom.Text;

import java.awt.font.TextAttribute;
import java.util.ArrayList;

/**
 * Created by morganmonzingo on 11/16/15.
 */
 public class BookAdapter extends ArrayAdapter<Book> {
        public Context ctx;
        public BookAdapter(Context context, Book[] books) {

            super(context, 0, books);
            this.ctx = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Book book = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }
            // Lookup view for data population
            TextView bookTitle = (TextView)convertView.findViewById(R.id.homeTitle);
            TextView bookAuthor = (TextView)convertView.findViewById(R.id.homeAuthor);
           // TextView bookPrice = (TextView)convertView.findViewById(R.id.homePrice);

            TextView bookGenre =(TextView)convertView.findViewById(R.id.homeGenre);
            ImageView bookPicture = (ImageView) convertView.findViewById(R.id.homePicture);

            // Populate the data into the template view using the data object
            String temp = book.collectionName.replace("(unabridged)","");
            bookTitle.setText(temp);
            bookAuthor.setText(book.artistName);
         //   bookPrice.setText(book.collectionPrice);

            String imageURL =book.artworkUrl60;
            Glide.with(this.ctx).load(imageURL).into(bookPicture);

            bookGenre.setText(book.primaryGenreName);

            // Return the completed view to render on screen
            return convertView;
        }
    }
