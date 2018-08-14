package com.dena.lcm.photopicker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends BaseAdapter {

    private List<ImageItem> mLists;
    private Activity activity;
    private LayoutInflater mInflater;
    private LCMResource R;

    public PhotoAdapter(List<ImageItem> mLists, Activity activity) {
        this.mLists = mLists;
        this.activity = activity;
        R = LCMResource.getInstance(activity);
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhotoViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.getLayoutForId("cs_image_list_item"), parent, false);
            holder = new PhotoViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PhotoViewHolder) convertView.getTag();
        }
        Glide.with(activity)
                .load(new File(mLists.get(position).path))
                .centerCrop()
                .into(holder.iv);
        return convertView;
    }

    public void refreshData(List<ImageItem> imgList) {
        if (imgList == null || imgList.size() == 0) {
            imgList = new ArrayList<>();
        }
        mLists = imgList;
        notifyDataSetChanged();
    }

    class PhotoViewHolder {
        private ImageView iv;

        public PhotoViewHolder(View itemView) {
            iv = (ImageView) itemView.findViewById(R.getId("iv_cs_list"));
        }
    }
}
