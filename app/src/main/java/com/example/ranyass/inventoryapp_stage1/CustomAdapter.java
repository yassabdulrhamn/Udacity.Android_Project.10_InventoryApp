package com.example.ranyass.inventoryapp_stage1;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter  extends BaseAdapter {
    LayoutInflater inflter;
    private DatabaseHandler db;
    private HashMap<Integer, ArrayList<String>> hashMap;

    public CustomAdapter(Context applicationContext) {
        inflter = (LayoutInflater.from(applicationContext));

        db = new DatabaseHandler(applicationContext);
        hashMap =  db.getData();
}

    @Override
    public int getCount() {
        return hashMap.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview_item, null);
        TextView texItemName = (TextView) view.findViewById(R.id.texItemName);
        TextView texCategory = (TextView) view.findViewById(R.id.texCategory);
        TextView texCost = (TextView) view.findViewById(R.id.texCost);
        TextView texPurveyor = (TextView) view.findViewById(R.id.texPurveyor);
        TextView texQuantity = (TextView) view.findViewById(R.id.texQuantity);
        TextView texAddDate = (TextView) view.findViewById(R.id.texAddDate);
        texItemName.setText(hashMap.get(i).get(1).toString());
        texCategory.setText(hashMap.get(i).get(2).toString());
        texCost.setText(hashMap.get(i).get(3).toString());
        texPurveyor.setText(hashMap.get(i).get(4).toString());
        texQuantity.setText(hashMap.get(i).get(5).toString());
        texAddDate.setText(hashMap.get(i).get(6).toString());



        return view;
    }
}
