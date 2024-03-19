package com.daeseong.spinner_test.Controls;

import android.content.Context;
import android.util.AttributeSet;
import java.util.List;

public class ComboBox extends androidx.appcompat.widget.AppCompatSpinner {

    private ComboBoxAdapter adapter;

    public ComboBox(Context context) {
        super(context);
        init(context, null);
    }

    public ComboBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ComboBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        adapter = new ComboBoxAdapter(context);
        setAdapter(adapter);
    }

    public void addItem(String item){
        adapter.addItem(item);
    }

    public void removeItem(String item){
        adapter.removeItem(item);
    }

    public List<String> getList() {
        return adapter.getList();
    }
}