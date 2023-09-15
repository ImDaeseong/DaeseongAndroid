package com.daeseong.changedeprecated.Common;

public class GetStringTask extends ThreadTask<String, String> {

    private static final String TAG = GetStringTask.class.getSimpleName();

    private String sResult = "";

    @Override
    protected String doInBackground(String sUrl) {

        try {
            sResult = HttpUtil.GetDataString(sUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sResult;
    }
}
