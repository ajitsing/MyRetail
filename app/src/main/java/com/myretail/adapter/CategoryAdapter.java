package com.myretail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.myretail.Models.Category;
import com.myretail.R;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter {
    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, R.layout.navigation_list_row);
        this.addAll(categories);
    }

    @Override
    public long getItemId(int position) {
        Category category = (Category) getItem(position);
        return category.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = (Category) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.navigation_list_row, parent, false);
        }

        TextView categoryNameView = (TextView) convertView.findViewById(R.id.navigation_list_row);
        categoryNameView.setText(category.getName());

        return convertView;
    }
}