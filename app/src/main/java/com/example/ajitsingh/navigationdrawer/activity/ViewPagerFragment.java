package com.example.ajitsingh.navigationdrawer.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ajitsingh.navigationdrawer.R;
import com.example.ajitsingh.navigationdrawer.db_helper.DataBaseHelper;

import static com.example.ajitsingh.navigationdrawer.tables.ItemTable.*;

public class ViewPagerFragment extends Fragment {
    private ViewPager mPager;
    private CursorPagerAdapter pagerAdapter;
    private DataBaseHelper dataBaseHelper;
    private long category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataBaseHelper = DataBaseHelper.getInstance(this.getActivity());
        pagerAdapter = new CursorPagerAdapter(getFragmentManager(), dataBaseHelper.getItemsCursor(this.category));
        mPager = (ViewPager) getView().findViewById(R.id.pager);
        mPager.setAdapter(pagerAdapter);
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public static class CursorPagerAdapter extends FragmentStatePagerAdapter {
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
            if (cursor == null)
                return 0;
            else
                return cursor.getCount();
        }
    }
}