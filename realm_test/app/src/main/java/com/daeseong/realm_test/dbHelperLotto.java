package com.daeseong.realm_test;

import android.util.Log;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class dbHelperLotto {

    private static final String TAG = dbHelperLotto.class.getSimpleName();

    private Realm realm;
    private RealmResults<Lotto> realmResults;

    public dbHelperLotto(){

        realm =  MyApplicaton.getInstance().getRealm();
    }

    public RealmResults<Lotto> getLotto() {

        try {
            return realmResults = realm.where(Lotto.class)
                    //.sort("_rIndex", Sort.ASCENDING)
                    .sort("_rIndex", Sort.DESCENDING)
                    .findAll();
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
        return null;
    }

    public Realm getRealm() {
        return realm;
    }

    public void closeRealm(){

        try{
            if(realm != null) {
                realm.close();
                realm = null;
            }
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    public void addLotto(int rIndex, String Date, int Part1, int Part2, int Part3, int Part4, int Part5, int Part6, int Bonus) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                try {
                    Lotto lotto = realm.createObject(Lotto.class, rIndex);
                    lotto.setDate(Date);
                    lotto.setPart1(Part1);
                    lotto.setPart2(Part2);
                    lotto.setPart3(Part3);
                    lotto.setPart4(Part4);
                    lotto.setPart5(Part5);
                    lotto.setPart6(Part6);
                    lotto.setBonus(Bonus);
                }catch (Exception ex){
                    Log.e(TAG, ex.getMessage().toString());
                }
            }
        });
    }

    public boolean isExistData(int rIndex) {

        boolean bFindData = false;
        try {
            Lotto lotto = realm.where(Lotto.class).equalTo("_rIndex", rIndex).findFirst();
            if (lotto != null)
                bFindData = true;
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
        return bFindData;
    }

    public Lotto getData(int rIndex) {
        try {
            return realm.where(Lotto.class).equalTo("_rIndex", rIndex).findFirst();
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
        return null;
    }

    public void deleteLotto(int rIndex) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                try {
                    Lotto lotto = realm.where(Lotto.class).equalTo("_rIndex", rIndex).findFirst();
                    if (lotto != null) {
                        lotto.deleteFromRealm();
                    }
                }catch (Exception ex){
                    Log.e(TAG, ex.getMessage().toString());
                }
            }
        });
    }

    public int getLottoRowCount() {

        int nTotalcount = 0;

        try {
            RealmResults<Lotto> results = realm.where(Lotto.class).findAll();
            nTotalcount = results.size();
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
        return nTotalcount;
    }

    public void deleteLottoAll(){
        try {
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }
}
