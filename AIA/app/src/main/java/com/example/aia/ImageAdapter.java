package com.example.aia;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.gallery_image_01,R.drawable.gallery_image_02,R.drawable.gallery_image_03,
            R.drawable.gallery_image_04,R.drawable.gallery_image_05,R.drawable.gallery_image_06,
            R.drawable.gallery_image_07,R.drawable.gallery_image_08,R.drawable.gallery_image_09,
            R.drawable.gallery_image_10, R.drawable.gallery_image_11, R.drawable.gallery_image_12,
            R.drawable.gallery_image_13, R.drawable.gallery_image_14, R.drawable.gallery_image_15,
            R.drawable.gallery_image_16, R.drawable.gallery_image_17, R.drawable.gallery_image_18,
            R.drawable.gallery_image_19, R.drawable.gallery_image_20
    };

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
        return imageView;
    }
}
