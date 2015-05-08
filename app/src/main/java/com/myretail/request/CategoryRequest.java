package com.myretail.request;

import com.myretail.Models.Category;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import static com.myretail.Constants.REMOTE_SERVER_URL;

public class CategoryRequest extends SpringAndroidSpiceRequest<Category> {

    private Long categoryId;

    public CategoryRequest(Long categoryId) {
        super(Category.class);
        this.categoryId = categoryId;
    }

    @Override
    public Category loadDataFromNetwork() throws Exception{
        return getRestTemplate().getForObject(REMOTE_SERVER_URL + "/categories/" + this.categoryId + ".json", Category.class);
    }

    public String createCacheKey() {
        return "category." + this.categoryId;
    }
}