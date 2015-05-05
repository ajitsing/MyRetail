package com.example.ajitsingh.navigationdrawer.tables;

public class CategoryTable {
    public static final String TABLE_NAME = "categories";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String[] ALL_COLUMNS = new String[]{ID, NAME};
    public static final String SELECT_QUERY = "SELECT * FROM " + CategoryTable.TABLE_NAME;

    public static final String DROP_QUERY = "drop table if exists " + TABLE_NAME;
    public static final String CREATE_QUERY = "create table " + TABLE_NAME +"(" +
                ID + "," +
                NAME + " TEXT" +
            ")";
}
