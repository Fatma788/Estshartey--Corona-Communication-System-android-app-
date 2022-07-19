package com.example.estshartey;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class menu_item extends ArrayAdapter<String> {
    Context context;
    String[] name;
    int []image;

    public menu_item( Context context, String[] name, int[] image) {
        super(context, R.layout.menu_item);
        this.context = context;
        this.name = name;
        this.image = image;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inter=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View row =inter.inflate(R.layout.menu_item,null);
        TextView t1=(TextView)row.findViewById(R.id.t1);
        ImageView menu1=(ImageView)row.findViewById(R.id.menu1);
       t1.setText(name[position]);
       menu1.setImageResource(image[position]);
       return  row;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inter=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View row =inter.inflate(R.layout.menu_item,null);
        TextView t1=(TextView)row.findViewById(R.id.t1);
        ImageView menu1=(ImageView)row.findViewById(R.id.menu1);
        t1.setText(name[position]);
        menu1.setImageResource(image[position]);
        return  row;
    }
}
