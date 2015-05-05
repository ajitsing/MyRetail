package com.example.ajitsingh.navigationdrawer.builder;

import android.database.sqlite.SQLiteDatabase;
import com.example.ajitsingh.navigationdrawer.tables.CategoryTable;
import java.util.ArrayList;

public class CategoryBuilder {
    private SQLiteDatabase dataBaseHelper;
    private ArrayList<String> data;

    public CategoryBuilder(SQLiteDatabase dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
        data = new ArrayList<>();
    }

    public CategoryBuilder seed(){
        data.add("10, 'Electronics'");
        data.add("20, 'Furniture'");
        data.add("30, 'Clothes'");

        return this;
    }

    public void build(){
        for (String values : data) {
            dataBaseHelper.execSQL("insert into " + CategoryTable.TABLE_NAME + " values(" + values + ")");
        }
    }
}
