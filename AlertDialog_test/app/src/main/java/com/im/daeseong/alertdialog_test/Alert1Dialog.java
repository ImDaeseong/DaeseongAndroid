package com.im.daeseong.alertdialog_test;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class Alert1Dialog extends AlertDialog.Builder {

    private Context context;

    private TextView tvTitle;
    private TextView tvMessage;

    public Alert1Dialog(Context context) {
        super(context);
        this.context = context;

        View viewtitle = View.inflate(context, R.layout.alert_title, null);
        tvTitle = (TextView) viewtitle.findViewById(R.id.tvTitle);
        setCustomTitle(viewtitle);

        View viewmessage = View.inflate(context, R.layout.alert_message, null);
        tvMessage = (TextView) viewmessage.findViewById(R.id.tvMessage);
        setView(viewmessage);
    }

    @Override
    public Alert1Dialog setTitle(int textResId) {
        tvTitle.setText(textResId);
        return this;
    }

    @Override
    public Alert1Dialog setTitle(CharSequence text) {
        tvTitle.setText(text);
        return this;
    }

    @Override
    public Alert1Dialog setMessage(int textResId) {
        tvMessage.setText(textResId);
        return this;
    }

    @Override
    public Alert1Dialog setMessage(CharSequence text) {
        tvMessage.setText(text);
        return this;
    }
}
