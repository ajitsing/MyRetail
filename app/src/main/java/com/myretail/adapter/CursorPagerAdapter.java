package com.myretail.adapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myretail.activity.ProductFragment;

import static com.myretail.tables.ItemTable.*;

public class CursorPagerAdapter extends FragmentStatePagerAdapter {
    private Cursor cursor;

    public CursorPagerAdapter(FragmentManager fm, Cursor cursor) {
        super(fm);
        this.cursor = cursor;
    }

    @Override
    public ProductFragment getItem(int position) {
        if (cursor == null)
            return null;

        cursor.moveToPosition(position);
        ProductFragment productFragment = new ProductFragment();

        String name = cursor.getString(cursor.getColumnIndex(NAME));
        byte[] image = cursor.getBlob(cursor.getColumnIndex(IMAGE));
        Long categoryId = cursor.getLong(cursor.getColumnIndex(CATEGORY_ID));
        String detail = cursor.getString(cursor.getColumnIndex(DETAIL));
        String price = cursor.getString(cursor.getColumnIndex(PRICE));

        Bundle args = new Bundle();

        args.putString(NAME, name);
        args.putString(DETAIL, detail);
        args.putString(PRICE, price);
        args.putLong(CATEGORY_ID, categoryId);
        args.putByteArray(IMAGE, image);

        productFragment.setArguments(args);
        return productFragment;
    }

    @Override
    public int getCount() {
        return cursor == null ? 0 : cursor.getCount();
    }
}