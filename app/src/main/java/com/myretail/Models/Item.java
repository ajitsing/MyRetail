package com.myretail.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.math.BigDecimal;

public class Item {
    private BigDecimal price;
    private Long id;
    private String name;
    private String detail;
    private byte[] image;
    private Long categoryId;

    public Item(Long id, String name, String detail, String price, byte[] image, Long categoryId) {
        this(id, name, categoryId);
        this.detail = detail;
        this.image = image;
        this.price = BigDecimal.valueOf(Double.parseDouble(price));
    }

    public Item(Long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public Bitmap getImage() {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public BigDecimal getPrice() {
        return price;
    }
}
