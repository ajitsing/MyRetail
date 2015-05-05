package com.example.ajitsingh.navigationdrawer.tables;

public class CartTable {
    public static final String TABLE_NAME = "cart";
    public static final String ID = "_id";
    public static final String ITEM_ID = "item_id";
    public static final String[] ALL_COLUMNS = new String[]{ ID, ITEM_ID };

    public static final String DROP_QUERY = "drop table if exists " + TABLE_NAME;
    public static final String CREATE_QUERY = "create table " +
                TABLE_NAME +"("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ITEM_ID + " INT"
                + ")";

    public static final String SELECT_QUERY = "select * from " + TABLE_NAME;
}
