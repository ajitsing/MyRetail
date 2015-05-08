package com.myretail.request;

import com.myretail.Models.Categories;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import static com.myretail.Constants.REMOTE_SERVER_URL;

public class CategoriesRequest extends SpringAndroidSpiceRequest<Categories> {

    public CategoriesRequest() {
        super(Categories.class);
    }

    @Override
    public Categories loadDataFromNetwork() throws Exception{
        String url = REMOTE_SERVER_URL + "/categories";
        return getRestTemplate().getForObject(url, Categories.class);
    }
}
