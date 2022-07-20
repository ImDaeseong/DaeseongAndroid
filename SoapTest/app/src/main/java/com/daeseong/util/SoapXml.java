package com.daeseong.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SoapXml extends AsyncTask<String, Void, String> {

    private static final String TAG = SoapXml.class.getSimpleName();

    private final String NAME_SPACE = "http://www.daeseong.com/";
    private final String METHOD_NAME = "findUserList";
    private final String SOAP_URL = "http://www.daeseong.com/MyWebService.asmx";
    private final String SOAP_ACTION = "http://www.daeseong.com/findUserList";

    private String sResult;

    private TextView textView;

    public SoapXml(TextView textView){
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {

        SoapObject soapObject = new SoapObject(NAME_SPACE, METHOD_NAME);
        soapObject.addProperty("keyword", params[0]);//파라미터 추가

        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet = true; //닷넷 사용 유무
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(SOAP_URL);

        try {

            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope);

            //Object oResult = soapSerializationEnvelope.getResponse();
            //Log.e(TAG, "getResponse:" + oResult.toString());

            SoapObject soapbody = (SoapObject) soapSerializationEnvelope.bodyIn;
            sResult = soapbody.toString();
            //Log.e(TAG, "bodyIn:" + sResult);

        } catch (Exception ex) {
            Log.e(TAG, "Exception:" + ex.getMessage().toString());
        }
        return sResult;
    }

    @Override
    protected void onPostExecute(String sResult) {
        super.onPostExecute(sResult);

        try{

            if(sResult != null){
                this.textView.setText(sResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
