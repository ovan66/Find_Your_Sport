package com.bastian.findyousport.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bastian.findyousport.R;

import java.util.List;

/**
 * Created by cutiko on 21-12-16.
 */

public class CategoriesDdAdapter extends ArrayAdapter<String> {

    public CategoriesDdAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    private class ViewChildHolder {
        TextView name;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewChildHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_lefted, parent, false);
            holder = new ViewChildHolder();
            holder.name = (TextView) convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewChildHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewChildHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ViewChildHolder();
            holder.name = (TextView) convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewChildHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position));

        return convertView;
    }
}
