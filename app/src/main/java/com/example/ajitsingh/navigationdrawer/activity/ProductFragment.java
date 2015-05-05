package com.example.ajitsingh.navigationdrawer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ajitsingh.navigationdrawer.R;
import com.example.ajitsingh.navigationdrawer.tables.ItemTable;

public class ProductFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                         ViewGroup container,
                         Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.product_fragment, container, false);
        TextView itemTextView = (TextView)result.findViewById(R.id.item_name);
        String itemName = getArguments().getString(ItemTable.NAME);

        itemTextView.setText(itemName);

        return result;
    }
}
