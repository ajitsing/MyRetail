package com.example.ajitsingh.navigationdrawer.tables;

public class ItemTable {
    public static final String TABLE_NAME = "items";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DETAIL = "detail";
    public static final String CATEGORY_ID = "category_id";
    public static final String PRICE = "price";
    public static final String IMAGE = "image_url";

    public static final String DROP_QUERY = "drop table if exists " + TABLE_NAME;
    public static final String CREATE_QUERY = "create table " + TABLE_NAME +"(" +
                ID + "," +
                NAME + " TEXT," +
                PRICE + " REAL," +
                DETAIL + " TEXT," +
                IMAGE + " TEXT," +
                CATEGORY_ID + " INT" +
            ")";

    public static final String[] ALL_COLUMNS = {ID, NAME, IMAGE, DETAIL, CATEGORY_ID, PRICE};
}
