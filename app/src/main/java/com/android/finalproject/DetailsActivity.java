package com.android.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private TextView productUsagePeriod, productDescription;
    private ImageView productImage;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
    }

    private void init() {

        item = getIntent().getExtras().getParcelable("item_object");
        toolbar = findViewById(R.id.toolbar);
        toolbar = getToolbar(item.getName());

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        appBarLayout = findViewById(R.id.appbar);

        productUsagePeriod = findViewById(R.id.product_usage_period);
        productUsagePeriod.setText(item.getPeriod());
        productDescription = findViewById(R.id.product_description);
        productDescription.setText(item.getDetails());
        productImage = findViewById(R.id.product_image);
        Glide.with(getApplicationContext()).load(item.getImage()).into(productImage);
    }

    protected Toolbar getToolbar(final String resId) {
        if (toolbar == null) {
            toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                toolbar.post(() -> toolbar.setTitle(resId));
                toolbar.setTitle(resId);
            }
        }
        return toolbar;
    }
}