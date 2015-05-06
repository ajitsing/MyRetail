package com.myretail.adapter;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class CategoryAdapter extends SimpleCursorAdapter {

    public CategoryAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }
}