package com.myretail.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myretail.R;

import static com.myretail.Constants.ITEM_DETAIL;
import static com.myretail.Constants.ITEM_IMAGE;
import static com.myretail.Constants.ITEM_NAME;
import static com.myretail.Constants.ITEM_PRICE;

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

        String itemName = getArguments().getString(ITEM_NAME);
        String detail = getArguments().getString(ITEM_DETAIL);
        String image = getArguments().getString(ITEM_IMAGE);
        String price = getArguments().getString(ITEM_PRICE);

        itemTextView.setText(itemName);
        detailTextView.setText(detail);
        priceTextView.setText("Price - " + price + " ₹");

        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));

        return result;
    }
}
