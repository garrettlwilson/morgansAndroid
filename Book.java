package com.example.morganmonzingo.googlebooksapiapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by morganmonzingo on 11/17/15. Jorge helped. Android sucks. I hate everything. Also, iten = item. I'm stupid.
 */
public class Book {
    String collectionName;
    String artistName;
    String description;
    String collectionPrice;
    String artworkUrl60;
    String primaryGenreName;
    String dirtay;
    Boolean dirtay_catch;

    JSONObject jsObj;

    public Book(JSONObject obj) {
        this.jsObj = obj;
        try {
            try {
                this.collectionName = obj.getString("collectionName");
            } catch (Exception e) {
                this.collectionName = "No Title";
            }
            try {
                    this.artistName = obj.getString("artistName");
            } catch (Exception e) {
                artistName = "NOBODY WROTE THIS!";
            }
            try {
                this.description = obj.getString("description");
            } catch (Exception e) {
                this.description = "Lack of description.";
            }
            try {
                this.artworkUrl60 = obj.getString("artworkUrl60");
            } catch (Exception e) {
                this.artworkUrl60 = "http://us.123rf.com/450wm/openlens/openlens1301/openlens130100092/17284842-three-funny-alpacas-in-different-colors.jpg?ver=6";
            }
            try {
                this.primaryGenreName = obj.getString("primaryGenreName");
            } catch (Exception e) {
                this.primaryGenreName = "Genre - Dank";
            }
            try {
                this.collectionPrice = obj.getString("collectionPrice");
            } catch (Exception e) {
                this.collectionPrice  = "NO MONIES.";
            }
            try {
                this.dirtay = obj.getString("collectionExplicitness");
                if(dirtay.equalsIgnoreCase("notExplicit")){
                    dirtay_catch=false;

                }
                else
                    dirtay_catch=true;
            } catch (Exception e){
                this.dirtay_catch = false;
            }

        } catch (Exception e) {

        }
    }
}
