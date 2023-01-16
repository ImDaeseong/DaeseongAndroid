package com.im.daeseong.edittext_test;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ExTextInputLayout extends BackBorderLayout {

    private EditText editText = null;
    private ConstraintLayout.LayoutParams params = null;

    public ExTextInputLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ExTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

        params = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        editText = new EditText(context);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            editText.setTextCursorDrawable(R.drawable.edittext_cursor);
        }

        editText.setSingleLine(true);
        editText.setTextColor(Color.parseColor("#000000"));
        editText.setHintTextColor(Color.parseColor("#AFAFAF"));
        editText.setGravity(Gravity.CENTER_VERTICAL);
        editText.setTextSize(14);
        editText.setPadding(42, 0, 42, 0);
        editText.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        editText.setHint("E-mail 주소 입력5");
        editText.setText("");
        editText.setBackgroundResource(R.drawable.edittext_select_normal);
        editText.setCursorVisible(true);
        params.setMargins(0,0,0,0);
        editText.setLayoutParams(params);
        addView(editText);

        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(b){

                    editText.setBackgroundResource(R.drawable.edittext_select);

                }else {

                    editText.setBackgroundResource(R.drawable.edittext_select_normal);
                }
            }
        });
    }

    public EditText getEditInstance(){
        return editText;
    }
}
