package com.daeseong.spannable_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class ClickableSpanEx extends ClickableSpan {

    private Context context;
    private String sUrl;

    public ClickableSpanEx(Context context, String sUrl) {
        this.context = context;
        this.sUrl = sUrl;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);

        ds.setColor(Color.RED);
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.sUrl));
        context.startActivity(intent);
    }
}
