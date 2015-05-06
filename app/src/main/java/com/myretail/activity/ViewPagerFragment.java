package com.myretail.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myretail.R;
import com.myretail.adapter.CursorPagerAdapter;
import com.myretail.db_helper.DataBaseHelper;

public class ViewPagerFragment extends Fragment {
    private long category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(this.getActivity());
        CursorPagerAdapter pagerAdapter = new CursorPagerAdapter(getFragmentManager(), dataBaseHelper.getItemsCursor(this.category));
        ViewPager viewPager = (ViewPager) getView().findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}