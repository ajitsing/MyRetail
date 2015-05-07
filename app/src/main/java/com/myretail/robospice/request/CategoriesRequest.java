package com.myretail.robospice.request;

import com.myretail.Models.Categories;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class CategoriesRequest extends SpringAndroidSpiceRequest<Categories> {

    public CategoriesRequest() {
        super(Categories.class);
    }

    @Override
    public Categories loadDataFromNetwork() throws Exception{
        String url = "https://enigmatic-coast-6264.herokuapp.com/categories";
        return getRestTemplate().getForObject(url, Categories.class);
    }
}