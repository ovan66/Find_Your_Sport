package com.bastian.findyousport.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bastian.findyousport.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.R.attr.path;

/**
 * Created by cutiko on 21-12-16.
 */

public class ProfilePhotoAdapter extends RecyclerView.Adapter<ProfilePhotoAdapter.ViewHolder> {

    private List<String> paths;

    public ProfilePhotoAdapter(List<String> paths) {
        this.paths = paths;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Context context = holder.photo.getContext();
        Picasso.with(context).load(path(paths.get(position))).fit().centerCrop().noFade().into(holder.photo);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paths.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
        System.gc();
    }

    @Override
    public int getItemCount() {
        return paths.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        ImageButton deleteBtn;


        ViewHolder(View view) {
            super(view);
            photo = (ImageView) view.findViewById(R.id.profileIv);
            deleteBtn = (ImageButton) view.findViewById(R.id.deleteBtn);
        }

    }

    public void add(String path) {
        paths.add(path);
        notifyDataSetChanged();
    }

    private String path(String path) {
        if (path.contains("http://")) {
            return path;
        } else {
            return "file://"+path;
        }
    }


}
