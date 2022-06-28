package com.example.ewasterecyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Models.Item;
import com.example.ewasterecyclers.R;

import java.util.ArrayList;

class MyAdapter extends ArrayAdapter {

    ArrayList birdList = new ArrayList<>();

    public MyAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        birdList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.pricelist, null);

        Item item= (Item) getItem(position);

        TextView textView = (TextView) v.findViewById(R.id.ttle);
        TextView tv = (TextView) v.findViewById(R.id.price);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageview1);
        textView.setText(item.getBirdListName());
        tv.setText(item.getBirdPrice());
        imageView.setImageResource(item.getBirdListImage());
        return v;

    }

}