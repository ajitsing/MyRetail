package com.myretail.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private BigDecimal price;
    private Long id;
    private String name;
    private String detail;
    private String image;
    private Long categoryId;

    public Item() {
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

    public String getImage() {
        return image;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
