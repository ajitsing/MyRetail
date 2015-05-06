package com.myretail.builder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.myretail.R;
import com.myretail.tables.ItemTable;

import java.io.ByteArrayOutputStream;

public class ItemBuilder {
    private SQLiteDatabase dataBaseHelper;
    private Context context;

    public ItemBuilder(SQLiteDatabase dataBaseHelper, Context context) {
        this.dataBaseHelper = dataBaseHelper;
        this.context = context;
    }

    public void build(){
        insertItem(1l, "T.V", "3000", "Details of LCD TV", imageToByteArray(R.drawable.tv), 10l);
        insertItem(2l, "Microwave", "4000", "microwave", imageToByteArray(R.drawable.microwave), 10l);

        insertItem(3l, "Chair", "1000", "Chair", imageToByteArray(R.drawable.chair), 20l);
        insertItem(4l, "Table", "5000", "Table", imageToByteArray(R.drawable.table), 20l);

        insertItem(5l, "T-Shirt", "800", "t-shirt", imageToByteArray(R.drawable.t_shirt), 30l);
        insertItem(6l, "Jeans", "1500", "jeans", imageToByteArray(R.drawable.jeans), 30l);
    }

    private void insertItem(Long id, String name, String price, String detail, byte[] image, Long categoryId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemTable.NAME, name);
        contentValues.put(ItemTable.ID, id);
        contentValues.put(ItemTable.PRICE, price);
        contentValues.put(ItemTable.DETAIL, detail);
        contentValues.put(ItemTable.IMAGE, image);
        contentValues.put(ItemTable.CATEGORY_ID, categoryId);

        dataBaseHelper.insert(ItemTable.TABLE_NAME, null, contentValues);
    }

    private byte[] imageToByteArray(int imageResource) {
        Bitmap b = BitmapFactory.decodeResource(this.context.getResources(), imageResource);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }
}
