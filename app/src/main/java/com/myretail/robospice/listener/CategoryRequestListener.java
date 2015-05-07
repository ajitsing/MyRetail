package com.myretail.robospice.listener;

import android.util.Log;

import com.myretail.Models.Category;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class CategoryRequestListener implements RequestListener<Category> {

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Log.e("result","failed");
    }

    @Override
    public void onRequestSuccess(Category category) {

        Log.e("result",category.toString());
    }
}
