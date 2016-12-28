package com.bastian.findyousport.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bastian.findyousport.R;

/**
 * Created by cutiko on 02-12-16.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private CategoriesClickListener listener;
    private String[] categories;

    public CategoriesAdapter(CategoriesClickListener listener, String[] categories) {
        this.listener = listener;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String category = categories[position];
        holder.name.setText(category);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(category.toLowerCase());
            }
        });


        ImageView photo = holder.photo;
        switch (position) {
            case 0:
                photo.setImageResource(R.mipmap.crossfit_wallpaper);
                break;
            case 1:
                photo.setImageResource(R.mipmap.gym_wallpaper);
                break;
            case 2:
                photo.setImageResource(R.mipmap.yoga_wallpaper);
                break;
            case 3:
                photo.setImageResource(R.mipmap.dance_wallpaper);
                break;
            case 4:
                photo.setImageResource(R.mipmap.acuatico_wallpaper);
                break;
            case 5:
                photo.setImageResource(R.mipmap.login_background);
                break;
        }

        ImageView icon = holder.icon;
        switch (position) {
            case 0:
                icon.setImageResource(R.mipmap.corssfit_icon_1);
                break;

            case 1:
                icon.setImageResource(R.mipmap.gym_icon);
                break;

            case 2:
                icon.setImageResource(R.mipmap.yoga_icon);
                break;

            case 3:
                break;

            case 4:
                icon.setImageResource(R.mipmap.swim_icon);
                break;

            case 5:
                icon.setImageResource(R.mipmap.ic_add_white_24dp);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView name;
        ImageView photo, icon;

        ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.categoryTv);
            photo = (ImageView) view.findViewById(R.id.categoryIv);
            icon = (ImageView) view.findViewById(R.id.iconIv);
            cardView = (CardView) view.findViewById(R.id.rootCategory);
        }

    }

}
