package com.im.daeseong.newbanner_test.Banner8_style;

import android.graphics.Bitmap;

public class itememainbanner{

    String seq;
    String link;
    Bitmap img_url;

    public String getseq(){
        return seq;
    }

    public String getLink(){
        return link;
    }

    public Bitmap getImg_url(){
        return img_url;
    }

    public void setItem(String seq, String link, Bitmap img_url){
        this.seq = seq;
        this.link = link;
        this.img_url = img_url;
    }
}
