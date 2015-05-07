package com.myretail.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myretail.Models.Item;
import com.myretail.activity.ProductFragment;

import java.util.List;

import static com.myretail.tables.ItemTable.CATEGORY_ID;
import static com.myretail.tables.ItemTable.DETAIL;
import static com.myretail.tables.ItemTable.IMAGE;
import static com.myretail.tables.ItemTable.NAME;
import static com.myretail.tables.ItemTable.PRICE;

public class CursorPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Item> items;

    public CursorPagerAdapter(FragmentManager fm, List<Item> items) {
        super(fm);
        this.items = items;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (items.size() < 0)
            return null;

        return items.get(position).getName();
    }

    @Override
    public ProductFragment getItem(int position) {
        if (items.size() < 0)
            return null;

        Item item = items.get(position);
        ProductFragment productFragment = new ProductFragment();
        Bundle args = new Bundle();

        args.putString(NAME, item.getName());
        args.putString(DETAIL, item.getDetail());
        args.putString(PRICE, String.valueOf(item.getPrice().intValue()));
        args.putLong(CATEGORY_ID, 0);
        args.putString(IMAGE, item.getImage());

        productFragment.setArguments(args);
        return productFragment;
    }

    @Override
    public int getCount() {
        return items.size();
    }
}