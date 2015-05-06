package com.myretail.Models;

import java.util.List;

public class Category {
    private Long id;
    private String name;
    private List<Item> items;

    public Category(Long id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }
}
