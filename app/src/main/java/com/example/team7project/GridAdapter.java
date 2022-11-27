package com.example.team7project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {
    Context context;
//    String[] mediaName;
    int[] mediaImage;

    LayoutInflater inflater;

    public GridAdapter(Context context, int[] mediaImage) {
        this.context = context;
//        this.mediaName = mediaName;
        this.mediaImage = mediaImage;

    }

    @Override
    public int getCount() {
        return mediaImage.length;
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
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null){
            view = inflater.inflate(R.layout.grid_item, null);
        }

        ImageView imageView = view.findViewById(R.id.grid_image);
//        TextView textView = view.findViewById(R.id.media_name);

        imageView.setImageResource(mediaImage[position]);
//        textView.setText(mediaName[position]);

        return view;
    }
}
