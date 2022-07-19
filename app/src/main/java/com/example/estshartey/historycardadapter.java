package com.example.estshartey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class historycardadapter extends ArrayAdapter<patientHistory> {
    private List<patientHistory> HcardList = new ArrayList<patientHistory>();
    public historycardadapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class CardViewHolder {
        TextView doctorname;
        TextView date;
        TextView time;
        TextView symptoms;
    }
    @Override
    public void add(patientHistory object) {
        HcardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.HcardList.size();
    }

    @Override
    public patientHistory getItem(int index) {
        return this.HcardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View roww = convertView;
        historycardadapter.CardViewHolder HviewHolder;
        if (roww == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            roww = inflater.inflate(R.layout.historycarditem, parent, false);
            HviewHolder = new historycardadapter.CardViewHolder();
            HviewHolder.doctorname = (TextView) roww.findViewById(R.id.textView_1);
            HviewHolder.date = (TextView) roww.findViewById(R.id.textView_2);
            HviewHolder.time = (TextView) roww.findViewById(R.id.textView_5);
            HviewHolder.symptoms = (TextView) roww.findViewById(R.id.textView33);
            roww.setTag(HviewHolder);
        } else {
            HviewHolder = (historycardadapter.CardViewHolder)roww.getTag();
        }
        patientHistory Hcard = getItem(position);

        HviewHolder.doctorname.setText(Hcard.getDoctorname());
        HviewHolder.date.setText(Hcard.getDate());
        HviewHolder.time.setText(Hcard.getTime());
        HviewHolder.symptoms.setText(Hcard.getSymptoms());

        return roww;
    }

}
