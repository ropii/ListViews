package com.example.listviews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FlowerAdapter extends ArrayAdapter<Flower> {

    Context context;
    List<Flower> objects;
    public FlowerAdapter(Context context, int resource, int textViewResourceId, List<Flower> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context=context;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.list_view_row_layout,parent,false);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvSubTitle = view.findViewById(R.id.tvSubTitle);
        TextView tvPrice = view.findViewById(R.id.tvPrice);
        ImageView ivProduct=view.findViewById(R.id.ivProduct);
        Flower temp = objects.get(position);

        ivProduct.setImageResource(temp.getImageId());
        tvPrice.setText(String.valueOf(temp.getPrice()));
        tvTitle.setText(temp.getTitle());
        tvSubTitle.setText(temp.getSubTitle());

        return view;
    }
}