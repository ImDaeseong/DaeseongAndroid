package com.im.daeseong.newbanner_test.Banner8_style;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagePagerAdapter extends RecyclingPagerAdapter {

    private Context       context;
    //private List<Integer> imageIdList;
    private List<itememainbanner> imageIdList;

    private int           size;
    private boolean       isInfiniteLoop;

    public ImagePagerAdapter(Context context, List<itememainbanner> imageIdList) {//public ImagePagerAdapter(Context context, List<Integer> imageIdList) {
        this.context = context;
        this.imageIdList = imageIdList;
        this.size = getSize(imageIdList);
        isInfiniteLoop = false;
    }

    private int getSize(List<itememainbanner> imageIdList){//private int getSize(List<Integer> imageIdList){
        int nSize;

        if(imageIdList == null){
            nSize = 0;
        }else {
            nSize = imageIdList.size();
        }
        return nSize;
    }

    @Override
    public int getCount() {
        return isInfiniteLoop ? Integer.MAX_VALUE : getSize(imageIdList);
    }

    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new ImageView(context);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        //bitmap 으로 해야 함으로 이걸로 변경해야 한다.
        holder.imageView.setImageBitmap(imageIdList.get(getPosition(position)).getImg_url());

        //원본
        //holder.imageView.setImageResource(imageIdList.get(getPosition(position)));
        return view;
    }

    private static class ViewHolder {
        ImageView imageView;
    }

    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }
}