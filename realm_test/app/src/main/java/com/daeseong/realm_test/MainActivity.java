package com.daeseong.realm_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import com.opencsv.CSVReader;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG = MainActivity.class.getSimpleName();

    private dbHelperLotto dbHelperLotto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelperLotto = new dbHelperLotto();

        //전체 데이터 삭제
        //deleteLottoAll();

        //csv 파일에서 데이터를 가져와서 입력
        //ReadCSVtoAdd();

        //데이터 조회
        //readLotto1();
        //readLotto2();
        //readLotto3();

        //데이터 삭제
        //deleteLotto();

        //한개 데이터 입력
        //addLotto();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dbHelperLotto.closeRealm();
    }

    private void readLotto1(){

        RealmResults<Lotto> realmResults = dbHelperLotto.getLotto();
        for (int i=0; i < realmResults.size(); i++) {
            Log.e(TAG, realmResults.get(i).getrIndex() + " "
                    + realmResults.get(i).getDate() + " "
                    + realmResults.get(i).getPart1() + " "
                    + realmResults.get(i).getPart2() + " "
                    + realmResults.get(i).getPart3() + " "
                    + realmResults.get(i).getPart4() + " "
                    + realmResults.get(i).getPart5() + " "
                    + realmResults.get(i).getPart6() + " "
                    + realmResults.get(i).getBonus());
        }
    }

    private void readLotto2(){

        RealmResults<Lotto> realmResults = dbHelperLotto.getLotto();
        for (Lotto lotto :realmResults) {
            Log.e(TAG, lotto.getrIndex() + " "
                    + lotto.getDate() + " "
                    + lotto.getPart1() + " "
                    + lotto.getPart2() + " "
                    + lotto.getPart3() + " "
                    + lotto.getPart4() + " "
                    + lotto.getPart5() + " "
                    + lotto.getPart6() + " "
                    + lotto.getBonus());
        }
    }

    private void readLotto3(){

        Lotto lotto = dbHelperLotto.getData(1);
        if (lotto != null) {
            Log.e(TAG, String.valueOf(lotto.getrIndex()));
            Log.e(TAG, lotto.getDate());
            Log.e(TAG, String.valueOf(lotto.getPart1()));
            Log.e(TAG, String.valueOf(lotto.getPart2()));
            Log.e(TAG, String.valueOf(lotto.getPart3()));
            Log.e(TAG, String.valueOf(lotto.getPart4()));
            Log.e(TAG, String.valueOf(lotto.getPart5()));
            Log.e(TAG, String.valueOf(lotto.getPart6()));
            Log.e(TAG, String.valueOf(lotto.getBonus()));
        }
    }

    private void deleteLotto() {

        boolean bfind = dbHelperLotto.isExistData(6);
        if (bfind) {
            dbHelperLotto.deleteLotto(6);
        }
    }

    private void deleteLottoAll(){

        int nTotalcount = dbHelperLotto.getLottoRowCount();
        Log.e(TAG, "nTotalcount: " + String.valueOf(nTotalcount));

        dbHelperLotto.deleteLottoAll();
    }

    private void addLotto() {
        dbHelperLotto.addLotto(6, "2022.03.25", 1, 1, 1, 1, 1, 1, 64);
    }

    private void ReadCSVtoAdd() {

        try {

            CSVReader csvReader = new CSVReader(new InputStreamReader(getResources().getAssets().open("lotto.csv"),"UTF-8"));
            String[] sline;

            dbHelperLotto.getRealm().beginTransaction();

            while ((sline = csvReader.readNext()) != null){

                Number nMax = dbHelperLotto.getRealm().where(Lotto.class).max("_rIndex");
                int nextId = nMax == null ? 1 : nMax.intValue() + 1;

                Lotto lotto = dbHelperLotto.getRealm().createObject(Lotto.class, nextId);
                lotto.setDate(sline[1]);
                lotto.setPart1(Integer.parseInt(sline[2]));
                lotto.setPart2(Integer.parseInt(sline[3]));
                lotto.setPart3(Integer.parseInt(sline[4]));
                lotto.setPart4(Integer.parseInt(sline[5]));
                lotto.setPart5(Integer.parseInt(sline[6]));
                lotto.setPart6(Integer.parseInt(sline[7]));
                lotto.setBonus(Integer.parseInt(sline[8]));
            }
            dbHelperLotto.getRealm().commitTransaction();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}