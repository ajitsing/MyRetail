package com.myretail.robospice.request;

import com.myretail.Models.Category;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class CategoryRequest extends SpringAndroidSpiceRequest<Category> {

    private Long categoryId;

    public CategoryRequest(Long categoryId) {
        super(Category.class);
        this.categoryId = categoryId;
    }

    @Override
    public Category loadDataFromNetwork() throws Exception{
        return getRestTemplate().getForObject("https://enigmatic-coast-6264.herokuapp.com/categories/" + this.categoryId + ".json", Category.class);
    }
}