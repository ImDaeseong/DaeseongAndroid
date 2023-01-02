package com.daeseong.paging_test.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchApi {

    private static final String TAG = SearchApi.class.getSimpleName();

    private static SearchApi mApi = null;

    private HashMap<Integer, List<SearchApi.itemData>> map;

    public SearchApi(){
        map = new HashMap<>();
    }

    public static SearchApi getInstance() {
        if(mApi == null){
            mApi = new SearchApi();
        }
        return mApi;
    }

    public void clear(){
        map.clear();
    }

    public boolean setMap(int nIndex, List<SearchApi.itemData> list){
        if(map.containsKey(nIndex)){
            return false;
        }
        map.put(nIndex, list);
        return true;
    }

    public List<SearchApi.itemData> getItem(int nIndex){
        if(map.containsKey(nIndex)){
            return map.get(nIndex);
        }
        return null;
    }

    public HashMap<Integer, List<SearchApi.itemData>> getItem(){
        return map;
    }

    public List<SearchApi.itemData> getSearch(String sKey){

        List<SearchApi.itemData> listA = new ArrayList<>();

        for (Integer key : map.keySet()) {
            List<SearchApi.itemData> list = map.get(key);
            if (list != null) {
                for (SearchApi.itemData i : list) {

                    if(i.NAME.toString().indexOf(sKey) > -1){
                        SearchApi.itemData item = new SearchApi.itemData();
                        item.setItem(i.ID, i.NAME, i.HTMLURL, i.CreateData);
                        listA.add(item);
                    }
                }
            }
        }
        return listA;
    }

    public static class itemData {

        public String ID;
        public String NAME;
        public String HTMLURL;
        public String CreateData;

        public void setItem(String ID, String NAME, String HTMLURL, String CreateData){

            this.ID = ID;
            this.NAME = NAME;
            this.HTMLURL = HTMLURL;
            this.CreateData = CreateData;
        }
    }
}
