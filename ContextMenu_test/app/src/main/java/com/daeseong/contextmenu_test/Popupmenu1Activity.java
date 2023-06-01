package com.daeseong.contextmenu_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class Popupmenu1Activity extends AppCompatActivity {

    private static final String TAG = Popupmenu1Activity.class.getSimpleName();

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupmenu1);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(Popupmenu1Activity.this, v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu1, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.Item1:
                                Log.e(TAG, "Item1");
                                return true;

                            case R.id.Item2:
                                Log.e(TAG, "Item2");
                                return true;
                        }

                        return false;
                    }
                });
                popupMenu.show();

            }
        });

    }
}