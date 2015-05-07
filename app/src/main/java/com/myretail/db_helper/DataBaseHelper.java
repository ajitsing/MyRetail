package com.myretail.db_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.myretail.Models.Item;
import com.myretail.builder.CategoryBuilder;
import com.myretail.builder.ItemBuilder;
import com.myretail.tables.CartTable;
import com.myretail.tables.CategoryTable;
import com.myretail.tables.ItemTable;

import java.util.ArrayList;
import java.util.List;

import static com.myretail.db_helper.DatabaseManager.backupDB;
import static com.myretail.db_helper.DatabaseManager.isBackupAvailable;
import static com.myretail.db_helper.DatabaseManager.restoreDB;


public class DataBaseHelper {

    private final String DATA_BASE = "retail.db";
    private final Integer DB_VERSION = 1;
    private Context context;
    private SQLiteDatabase database;
    private static DataBaseHelper dataBaseHelper;

    public DataBaseHelper(Context baseContext) {
        this.context = baseContext;
    }

    public static DataBaseHelper getInstance(Context context) {
        if (dataBaseHelper == null) {
            dataBaseHelper = new DataBaseHelper(context);
        }

        return dataBaseHelper;
    }

    public void setUpDB() {
        database = new DataBaseOpenHelper(this.context).getWritableDatabase();
//        if (isBackupAvailable()) {
//            restoreDB();
//        } else {
            createAndSeedCategoriesTable();
            createAndSeedItemsTable();
            createCartTable();
            backupDB();
//        }
    }

    public Cursor getCategoriesCursor() {
        return database.rawQuery(CategoryTable.SELECT_QUERY, null);
    }

//    public Item getItem(Long id) {
//        Cursor item = database.query(ItemTable.TABLE_NAME, ItemTable.ALL_COLUMNS, ItemTable.ID + "=" + id.intValue(), null, null, null, null, null);
//        if (item == null) return null;
//
//        item.moveToNext();
//        String name = item.getString(item.getColumnIndex(ItemTable.NAME));
//        byte[] image = item.getBlob(item.getColumnIndex(ItemTable.IMAGE));
//        Long categoryId = item.getLong(item.getColumnIndex(ItemTable.CATEGORY_ID));
//        String detail = item.getString(item.getColumnIndex(ItemTable.DETAIL));
//        String price = item.getString(item.getColumnIndex(ItemTable.PRICE));
//
//        item.close();
//
//        return new Item(id, name, detail, price, image, categoryId);
//    }

    public void addItemToCart(Long id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CartTable.ITEM_ID, id);
        database.insert(CartTable.TABLE_NAME, null, contentValues);
    }

    private void createCartTable() {
        database.execSQL(CartTable.DROP_QUERY);
        database.execSQL(CartTable.CREATE_QUERY);
    }

    private void createAndSeedItemsTable() {
        database.execSQL(ItemTable.DROP_QUERY);
        database.execSQL(ItemTable.CREATE_QUERY);

        new ItemBuilder(database, this.context).build();
    }

    private void createAndSeedCategoriesTable() {
        database.execSQL(CategoryTable.DROP_QUERY);
        database.execSQL(CategoryTable.CREATE_QUERY);

        new CategoryBuilder(database).seed().build();
    }

    public Cursor getCartCursor() {
        return database.rawQuery(CartTable.SELECT_QUERY, null);
    }

    public void deleteCartItem(Long cartItemId) {
        Integer delete = database.delete(CartTable.TABLE_NAME, CartTable.ID + "=" + cartItemId.intValue(), null);
        Log.e("rows affected:", delete.toString());
        backupDB();
    }

    public String getCategoryName(Long id) {
        Cursor category = database.query(CategoryTable.TABLE_NAME, new String[]{CategoryTable.NAME}, CategoryTable.ID + "=" + id, null, null, null, null, null);
        if(category == null || category.getCount() == 0) return "";

        category.moveToFirst();
        String categoryName = category.getString(category.getColumnIndex(CategoryTable.NAME));
        category.close();
        return categoryName;
    }

//    public List<Item> getItems(Long categoryId) {
//        Cursor itemsCursor = database.query(ItemTable.TABLE_NAME, ItemTable.ALL_COLUMNS, ItemTable.CATEGORY_ID + "=" + categoryId.intValue(), null, null, null, null, null);
//        ArrayList<Item> items = new ArrayList<>();
//        if(itemsCursor == null || itemsCursor.getCount() == 0) return items;
//
//        itemsCursor.moveToFirst();
//        do {
//            Long itemId = itemsCursor.getLong(itemsCursor.getColumnIndex(ItemTable.ID));
//            items.add(getItem(itemId));
//
//        } while(itemsCursor.moveToNext());
//
//
//        itemsCursor.close();
//        return items;
//    }

    public Cursor getItemsCursor(Long categoryId) {
        return database.query(ItemTable.TABLE_NAME, ItemTable.ALL_COLUMNS, ItemTable.CATEGORY_ID + "=" + categoryId.intValue(), null, null, null, null, null);
    }

    private class DataBaseOpenHelper extends SQLiteOpenHelper {

        DataBaseOpenHelper(Context context) {
            super(context, DATA_BASE, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }
}
