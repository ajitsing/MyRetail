package com.myretail.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myretail.Models.Item;
import com.myretail.activity.ProductFragment;

import java.util.List;

import static com.myretail.Constants.ITEM_DETAIL;
import static com.myretail.Constants.ITEM_IMAGE;
import static com.myretail.Constants.ITEM_NAME;
import static com.myretail.Constants.ITEM_PRICE;

public class ItemPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Item> items;

    public ItemPagerAdapter(FragmentManager fm, List<Item> items) {
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

        args.putString(ITEM_NAME, item.getName());
        args.putString(ITEM_DETAIL, item.getDetail());
        args.putString(ITEM_PRICE, String.valueOf(item.getPrice().intValue()));
        args.putString(ITEM_IMAGE, item.getImage());

        productFragment.setArguments(args);
        return productFragment;
    }

    @Override
    public int getCount() {
        return items.size();
    }
}