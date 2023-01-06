package com.daeseong.bottomsheetdialog_test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

public class BottomFragmentDialog extends BottomSheetDialogFragment {

    private static final String TAG = BottomFragmentDialog.class.getSimpleName();

    private TextInputLayout tvl1, tvl2;
    private Button button1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_bottomlayout, container, false);
        tvl1 = (TextInputLayout)view.findViewById(R.id.tvl1);
        tvl2 = (TextInputLayout)view.findViewById(R.id.tvl2);
        button1 = (Button) view.findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean bCehck = false;
                if(checkID()){
                    bCehck = true;
                }

                if(checkPwd()){
                    bCehck = true;
                }

                if(bCehck){
                    dismiss();
                }

            }
        });

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private boolean checkID(){

        String sID = tvl1.getEditText().getText().toString();
        if ( sID.isEmpty() ){
            tvl1.setError("아이디를 입력하세요");
        }else {
            tvl1.setError(null);
            tvl1.setErrorEnabled(false);
        }
        return true;
    }

    private boolean checkPwd(){

        String sPwd = tvl2.getEditText().getText().toString();
        if ( sPwd.isEmpty() ){
            tvl2.setError("비밀번호를 입력하세요");
        }else {
            tvl2.setError(null);
            tvl2.setErrorEnabled(false);
        }
        return true;
    }
}
