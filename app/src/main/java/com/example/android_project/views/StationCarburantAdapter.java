package com.example.android_project.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.android_project.R;
import com.example.android_project.modle.StationCarburant;

import java.util.List;

public class StationCarburantAdapter extends BaseAdapter {

    private Context context;
    private List<StationCarburant> list;

    public StationCarburantAdapter(Context context, List<StationCarburant> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size(); //Attention!!!!!!!
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;
        LayoutInflater mInflater = LayoutInflater.from(context);
        //(1) : Réutilisation du layout
        if (convertView == null) {
            layoutItem = (ConstraintLayout)
                    mInflater.inflate(R.layout.item_layout, parent, false);
        } else { layoutItem = (ConstraintLayout) convertView; }

        ViewHolder viewHolder = (ViewHolder) layoutItem.getTag();
        if(viewHolder == null) {
            viewHolder = new ViewHolder();
        //(2) : Récupération des TextView de notre layout
            viewHolder.tv1 = (TextView) layoutItem.findViewById(R.id.textView_station);
            viewHolder.tv2 = (TextView) layoutItem.findViewById(R.id.textView_gazole);
            viewHolder.img = layoutItem.findViewById(R.id.imageView);
            layoutItem.setTag(viewHolder);
        }

        //(3) : Mise à jour des valeurs

        StationCarburant station = list.get(position);
        viewHolder.tv1.setText(station.getNom());
        viewHolder.tv2.setText(String.valueOf(station.getPriceGazole()));
        // Define your price threshold
        double priceThreshold = 1.33; // This is just an example value

        // Set the image based on the price of gazole
        if (station.getPriceGazole() <= priceThreshold) {
            viewHolder.img.setImageResource(R.mipmap.ic_icone_yellow);
        } else {
            viewHolder.img.setImageResource(R.mipmap.ic_icone_red);
        }
        //On retourne l'item créé.
        return layoutItem;
    }
    private class ViewHolder {
        public TextView tv1;
        public TextView tv2;
        public ImageView img;
    }
}