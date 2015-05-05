package com.example.ajitsingh.navigationdrawer.adapter;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class CategoryAdapter extends SimpleCursorAdapter {
    private Context context;

    public CategoryAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.context = context;
    }
}