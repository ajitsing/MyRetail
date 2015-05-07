package com.myretail.resource;

import android.net.Uri;
import android.util.Log;

import com.myretail.Models.Category;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CategoryResource {
    public static Category getCategory(Long categoryId){
        Uri.Builder uriBuilder = Uri.parse("http://localhost:3000/categories/" + categoryId + ".json").buildUpon();
        String url = uriBuilder.toString();
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            String stringResult = IOUtils.toString(urlConnection.getInputStream());
            Log.e("Categories From Server", stringResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
