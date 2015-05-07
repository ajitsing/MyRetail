package com.myretail.Models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Categories extends ArrayList<Category> {
    public Categories() {
    }
}
