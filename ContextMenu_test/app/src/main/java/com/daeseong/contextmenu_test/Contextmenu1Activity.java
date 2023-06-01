package com.daeseong.contextmenu_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Contextmenu1Activity extends AppCompatActivity {

    private static final String TAG = Contextmenu1Activity.class.getSimpleName();

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contextmenu1);

        //ContextMenu 메뉴는 길게 클릭해야 호출됨
        button1 = (Button) findViewById(R.id.button1);
        registerForContextMenu(button1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        if( v == button1) {
            menuInflater.inflate(R.menu.menu1, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.Item1:
                Log.e(TAG, "Item1");
                return true;

            case R.id.Item2:
                Log.e(TAG, "Item2");
                return true;
        }

        return super.onContextItemSelected(item);
    }
}