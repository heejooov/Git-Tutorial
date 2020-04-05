package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecAdapter extends BaseAdapter {

    private ArrayList<RecItem> list = new ArrayList<RecItem>();

    public RecAdapter(){
    }
    //아이템 추가
    public void addItem(Drawable image, String title, String duration, String dday){
        RecItem item = new RecItem();

        item.setImage(image);
        item.setTitle(title);
        item.setDuration(duration);
        item.setDday(dday);

        list.add(item);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    //리스트뷰에 데이터 삽입
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview,parent,false);
        }

        ImageView image = convertView.findViewById(R.id.image);
        TextView title = convertView.findViewById(R.id.title);
        TextView duration = convertView.findViewById(R.id.duration);
        TextView dday = convertView.findViewById(R.id.dday);
        dday.setTextColor(Color.RED);

        RecItem listItem = list.get(position);

        //아이템 내 위젯에 데이터 반영
        image.setImageDrawable(listItem.getImage());
        title.setText(listItem.getTitle());
        duration.setText(listItem.getDuration());
        dday.setText(listItem.getDday());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

}

