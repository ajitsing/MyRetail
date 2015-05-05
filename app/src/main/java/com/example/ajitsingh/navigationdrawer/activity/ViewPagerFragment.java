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
import com.example.ajitsingh.navigationdrawer.tables.ItemTable;

public class ViewPagerFragment extends Fragment {
    private ViewPager mPager;
    private CursorPagerAdapter<ProductFragment> pagerAdapter;
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
        pagerAdapter = new CursorPagerAdapter<ProductFragment>(getFragmentManager(),
                ProductFragment.class, ItemTable.COLUMNS,
                dataBaseHelper.getItemsCursor(this.category));

        mPager = (ViewPager) getView().findViewById(R.id.pager);
        mPager.setAdapter(pagerAdapter);
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public static class CursorPagerAdapter<F extends Fragment> extends FragmentStatePagerAdapter {
        private final Class<F> fragmentClass;
        private final String[] projection;
        private Cursor cursor;

        public CursorPagerAdapter(FragmentManager fm, Class<F> fragmentClass, String[] projection, Cursor cursor) {
            super(fm);
            this.fragmentClass = fragmentClass;
            this.projection = projection;
            this.cursor = cursor;
        }

        @Override
        public F getItem(int position) {
            if (cursor == null)
                return null;

            cursor.moveToPosition(position);
            F frag;
            try {
                frag = fragmentClass.newInstance();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            Bundle args = new Bundle();
            for (int i = 0; i < projection.length; ++i) {
                args.putString(projection[i], cursor.getString(i));
            }
            frag.setArguments(args);
            return frag;
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