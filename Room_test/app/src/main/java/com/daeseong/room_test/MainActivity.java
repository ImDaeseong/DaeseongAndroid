package com.daeseong.room_test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    EditText editTitle, editContent;
    Button btnAdd, btnRead, btnUpdate, btnDelete;
    TextView textResult;

    private AlarmDao alarmDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        btnAdd = findViewById(R.id.btnAdd);
        btnRead = findViewById(R.id.btnRead);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        textResult = findViewById(R.id.textResult);

        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        alarmDao = AppDatabase.getInstance(this).alarmDao();

        //전체 개수 조회
        updateRowCount();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                add();
                break;
            case R.id.btnRead:
                read();
                break;
            case R.id.btnUpdate:
                update();
                break;
            case R.id.btnDelete:
                delete();
                break;
        }
    }

    private void add() {

        getCompletable(() -> {
            String title = editTitle.getText().toString();
            String content = editContent.getText().toString();

            Alarm alarm = new Alarm();
            alarm.title = title;
            alarm.content = content;
            alarm.regDate = new Date();
            alarmDao.insert(alarm);

            // 입력 데이타가 10개가 넘으면 가장 처음 입력한 데이타 삭제
            int rowCount = alarmDao.getRowCount();
            if (rowCount > 10) {
                alarmDao.deleteOldest();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError);
    }

    private void read() {

        getObservable(() -> alarmDao.getAll())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onReadSuccess, this::onError);
    }

    private void update() {

        getCompletable(() -> {
            String title = editTitle.getText().toString();
            String content = editContent.getText().toString();

            if (alarmDao.isAlarmExists(title)) {
                Alarm alarm = alarmDao.getAlarmByTitle(title);
                alarm.content = content;
                alarmDao.update(alarm);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError);
    }

    private void delete() {

        getCompletable(() -> {
            String title = editTitle.getText().toString();
            alarmDao.delete(alarmDao.getAlarmByTitle(title));
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError);
    }

    private void updateRowCount() {

        getObservable(() -> alarmDao.getRowCount())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRowCount, this::onError);
    }

    private Completable getCompletable(Runnable runnable) {
        return Completable.fromRunnable(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage().toString());
            }
        });
    }

    private <T> Observable<T> getObservable(Callable<T> callable) {
        return Observable.fromCallable(() -> {
            try {
                return callable.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onComplete() {

        editTitle.setText("");
        editContent.setText("");
        editTitle.requestFocus();

        // 전체 개수 조회
        updateRowCount();
    }

    private void onReadSuccess(List<Alarm> alarmList) {
        StringBuilder sMsg = new StringBuilder();
        for (Alarm alarm : alarmList) {
            String line = String.format("id:%d title:%s content:%s regDate:%s%n", alarm.id, alarm.title, alarm.content, formatDate(alarm.regDate));
            sMsg.append(line);
        }
        textResult.setText(sMsg.toString());
    }

    private void onError(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
    }

    private void onRowCount(int rowCount) {
        String sCount = "개수:" + rowCount;
        textResult.setText(sCount);
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }
}
