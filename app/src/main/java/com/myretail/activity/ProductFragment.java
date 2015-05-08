package com.myretail.activity;

import android.content.Intent;
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

        final String itemName = getArguments().getString(ITEM_NAME);
        final String detail = getArguments().getString(ITEM_DETAIL);
        final String image = getArguments().getString(ITEM_IMAGE);
        final String price = getArguments().getString(ITEM_PRICE);

        itemTextView.setText(itemName);
        detailTextView.setText(getString(R.string.details) + detail);
        priceTextView.setText(getString(R.string.price) + price + getString(R.string.currency));

        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));

        itemTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra(ITEM_NAME, itemName);
                intent.putExtra(ITEM_DETAIL, detail);
                intent.putExtra(ITEM_PRICE, price);
                intent.putExtra(ITEM_IMAGE, image);
                startActivity(intent);
            }
        });

        return result;
    }
}
