package com.android.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.UserViewHolder> {

    Context mContext;
    List<Item> items;

    public ItemsAdapter(Context mContext, List<Item> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Item item = items.get(position);
        holder.title.setText(item.getName());
        holder.period.setText(item.getPeriod());
        holder.views.setText(item.getViews());
        holder.details.setText(removeTags(forceRTL(item.getDetails())).trim());
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("item_object", items.get(position));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        });


        Glide.with(mContext).load(item.getImage()).into(holder.image);

    }

    private String forceRTL(String bodyHTML) {
        return "<html dir='RTL' lang='ar'>" + "<body>" + bodyHTML + "</body></html>";
    }

    private String removeTags(String bodyHTML) {
        return android.text.Html.fromHtml(bodyHTML).toString().replaceAll("\\<[^>]*>","");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {

        CardView mainLayout;
        ImageView image;
        TextView title, period, details, views;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            mainLayout = itemView.findViewById(R.id.product_main);
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_name);
            period = itemView.findViewById(R.id.product_status);
            details = itemView.findViewById(R.id.product_location);
            views = itemView.findViewById(R.id.view_count);
        }
    }
}
