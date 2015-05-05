package com.example.ajitsingh.navigationdrawer.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ajitsingh.navigationdrawer.R;

import static com.example.ajitsingh.navigationdrawer.tables.ItemTable.*;

public class ProductFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.product_fragment, container, false);
        TextView itemTextView = (TextView) result.findViewById(R.id.item_name);
        TextView detailTextView = (TextView) result.findViewById(R.id.detail);
        TextView priceTextView = (TextView) result.findViewById(R.id.price);
        ImageView imageView = (ImageView) result.findViewById(R.id.image);

        String itemName = getArguments().getString(NAME);
        String detail = getArguments().getString(DETAIL);
        byte[] image = getArguments().getByteArray(IMAGE);
        String price = getArguments().getString(PRICE);

        itemTextView.setText(itemName);
        detailTextView.setText(detail);
        priceTextView.setText(price);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));

        return result;
    }
}
