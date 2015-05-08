package com.myretail.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.myretail.R;

import static com.myretail.Constants.ITEM_DETAIL;
import static com.myretail.Constants.ITEM_IMAGE;
import static com.myretail.Constants.ITEM_NAME;
import static com.myretail.Constants.ITEM_PRICE;


public class ProductDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        Intent intent = getIntent();

        TextView itemTextView = (TextView) findViewById(R.id.item_name);
        TextView detailTextView = (TextView) findViewById(R.id.detail);
        TextView priceTextView = (TextView) findViewById(R.id.price);
        ImageView imageView = (ImageView) findViewById(R.id.image);

        final String itemName = intent.getStringExtra(ITEM_NAME);
        final String detail = intent.getStringExtra(ITEM_DETAIL);
        String image = intent.getStringExtra(ITEM_IMAGE);
        final String price = intent.getStringExtra(ITEM_PRICE);


        itemTextView.setText(itemName);
        detailTextView.setText(getString(R.string.details) + detail);
        priceTextView.setText(getString(R.string.price) + price + getString(R.string.currency));

        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
    }
}
