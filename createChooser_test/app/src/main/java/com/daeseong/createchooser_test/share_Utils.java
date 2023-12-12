package com.daeseong.createchooser_test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;

public class share_Utils {

    private static final String TAG = share_Utils.class.getSimpleName();

    public static void shareLink(Context context, String sTitle, String slink) {

        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, slink);

            Intent chooserIntent = Intent.createChooser(intent, sTitle);
            context.startActivity(chooserIntent);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    public static void shareText(Context context, String sTitle, String sText) {

        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, sTitle);
            intent.putExtra(Intent.EXTRA_TEXT, sText);

            Intent chooserIntent = Intent.createChooser(intent, sTitle);
            context.startActivity(chooserIntent);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    public static void shareImage(Context context, String sTitle, Uri uri) {

        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, sTitle);
            context.startActivity(chooserIntent);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    public static void shareMultiText(Context context, String sTitle, String sText, Uri uri) {

        try {
            ArrayList<Uri> imageUris = new ArrayList<>();
            imageUris.add(uri);

            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, sTitle);
            intent.putExtra(Intent.EXTRA_TEXT, sText);
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            Intent chooserIntent = Intent.createChooser(intent, sTitle);
            context.startActivity(chooserIntent);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }

}
