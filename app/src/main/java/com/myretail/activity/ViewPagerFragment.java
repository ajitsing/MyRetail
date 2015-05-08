package com.myretail.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.myretail.Models.Category;
import com.myretail.MyRetailSpiceService;
import com.myretail.R;
import com.myretail.adapter.ItemPagerAdapter;
import com.myretail.request.CategoryRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class ViewPagerFragment extends Fragment {
    private long category;
    private SpiceManager spiceManager = new SpiceManager(MyRetailSpiceService.class);
    private ProgressBar spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinner = (ProgressBar)getActivity().findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        CategoryRequest categoryRequest = new CategoryRequest(this.category);
        spiceManager.execute(categoryRequest, categoryRequest.createCacheKey(), DurationInMillis.ONE_HOUR, new RequestListener<Category>(){

            @Override
            public void onRequestFailure(SpiceException spiceException) {

            }

            @Override
            public void onRequestSuccess(Category category) {
                ItemPagerAdapter pagerAdapter = new ItemPagerAdapter(getFragmentManager(), category.getItems());
                ViewPager viewPager = (ViewPager) getView().findViewById(R.id.pager);
                viewPager.setAdapter(pagerAdapter);
                spinner.setVisibility(View.GONE);
            }
        });
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}