package com.daeseong.listview_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapApi {

    private static final String TAG = MapApi.class.getSimpleName();

    private static MapApi instance = null;

    private List<String> list;
    private HashMap<String, List<itemData>> map;

    public static MapApi getInstance() {
        if(instance == null){
            synchronized (MapApi.class){
                if(instance==null)
                    instance = new MapApi();
            }
        }
        return instance;
    }

    public MapApi(){

        list = new ArrayList<>();
        map = new HashMap<>();

        initList();
        initMap();
    }

    public boolean setMap(String sIndex, List<itemData> list){
        if(map.containsKey(sIndex)){
            return false;
        }
        map.put(sIndex, list);
        return true;
    }

    public List<itemData> getItem(String sIndex){
        if(map.containsKey(sIndex)){
            return map.get(sIndex);
        }
        return null;
    }

    public HashMap<String, List<itemData>> getItem(){
        return map;
    }

    public List<String> getList(){
        return list;
    }

    public void addList(String sValue){
        list.add(sValue);
    }

    public void updateList(int nSelect, String sValue){
        list.set(nSelect, sValue);
    }

    public void removeList(int nSelect){
        list.remove(nSelect);
    }

    private void initList(){

        list.add("서울");
        list.add("경기");
        list.add("인천");
        list.add("강원");
        list.add("부산");
        list.add("경남");
        list.add("대구");
        list.add("경북");
        list.add("울산");
        list.add("대전");
        list.add("충남");
        list.add("충북");
        list.add("광주");
        list.add("전남");
        list.add("전북");
        list.add("제주");
    }

    private void initMap(){

        ArrayList<itemData> list = new ArrayList<>();
        list.add(new itemData("강남 역삼 삼성 논현", 0f, 0f));
        list.add(new itemData("서초 방배 반포", 0f, 0f));
        list.add(new itemData("신길 영등포 여의도 문래", 0f, 0f));
        list.add(new itemData("구로 금천 신도림", 0f, 0f));
        list.add(new itemData("강서 화곡 까치산 목동", 0f, 0f));
        list.add(new itemData("천호 길동 둔촌", 0f, 0f));
        list.add(new itemData("서울대 신림 봉천", 0f, 0f));
        list.add(new itemData("대방 노량진 사당", 0f, 0f));
        list.add(new itemData("종로 대학로", 0f, 0f));
        list.add(new itemData("용산 중구 명동 이태원", 0f, 0f));
        list.add(new itemData("성북 도봉 노원", 0f, 0f));
        list.add(new itemData("강북 수유 미아", 0f, 0f));
        list.add(new itemData("왕십리 성수", 0f, 0f));
        list.add(new itemData("건대 광진", 0f, 0f));
        list.add(new itemData("동대문 장안 청량리", 0f, 0f));
        list.add(new itemData("중랑 상봉 면목 태릉", 0f, 0f));
        list.add(new itemData("신촌 홍대 서대문 마포", 0f, 0f));
        list.add(new itemData("은평 연신내 불광", 0f, 0f));
        list.add(new itemData("잠실 신천 송파 석촌", 0f, 0f));
        setMap("서울", list);

        list = new ArrayList<>();
        list.add(new itemData("수원 인계 권선 영통", 0f, 0f));
        list.add(new itemData("수원역 세류 팔달문 구운 장안", 0f, 0f));
        list.add(new itemData("안성 평택 송탄", 0f, 0f));
        list.add(new itemData("오산 화성 동탄", 0f, 0f));
        list.add(new itemData("파주 김포", 0f, 0f));
        list.add(new itemData("고양 일산", 0f, 0f));
        list.add(new itemData("의정부", 0f, 0f));
        list.add(new itemData("부천", 0f, 0f));
        list.add(new itemData("안양 평촌", 0f, 0f));
        list.add(new itemData("군포 금정 산본 의왕", 0f, 0f));
        list.add(new itemData("안산", 0f, 0f));
        list.add(new itemData("광명 시흥", 0f, 0f));
        list.add(new itemData("용인", 0f, 0f));
        list.add(new itemData("이천 광주 여주", 0f, 0f));
        list.add(new itemData("성남", 0f, 0f));
        list.add(new itemData("구리 남양주 하남", 0f, 0f));
        list.add(new itemData("가평 양평", 0f, 0f));
        list.add(new itemData("포천 동두천 연천 양주", 0f, 0f));
        setMap("경기", list);

        list = new ArrayList<>();
        list.add(new itemData("부평", 0f, 0f));
        list.add(new itemData("주안", 0f, 0f));
        list.add(new itemData("동암 남동구", 0f, 0f));
        list.add(new itemData("계양 서구", 0f, 0f));
        list.add(new itemData("남구 동구 중구 월미도", 0f, 0f));
        list.add(new itemData("연수구 송도 강화 옹진", 0f, 0f));
        setMap("인천", list);

        list = new ArrayList<>();
        list.add(new itemData("경포대 강릉 정동진", 0f, 0f));
        list.add(new itemData("속초 양양 고성 인제", 0f, 0f));
        list.add(new itemData("춘천 홍천 철원", 0f, 0f));
        list.add(new itemData("원주 횡성", 0f, 0f));
        list.add(new itemData("동해 삼척 태백", 0f, 0f));
        list.add(new itemData("평창 영월 정선", 0f, 0f));
        setMap("강원", list);

        list = new ArrayList<>();
        list.add(new itemData("해운대 재송", 0f, 0f));
        list.add(new itemData("송정 기장", 0f, 0f));
        list.add(new itemData("서면 초읍 양정", 0f, 0f));
        list.add(new itemData("연산 토곡", 0f, 0f));
        list.add(new itemData("동래 온천장 부산대 구서 사직", 0f, 0f));
        list.add(new itemData("동구 부산역 남포동 송도 영도", 0f, 0f));
        list.add(new itemData("광안리 경성대 남구", 0f, 0f));
        list.add(new itemData("사상", 0f, 0f));
        list.add(new itemData("덕천 북구", 0f, 0f));
        list.add(new itemData("하단 사하 명지", 0f, 0f));
        setMap("부산", list);

        list = new ArrayList<>();
        list.add(new itemData("김해 장유", 0f, 0f));
        list.add(new itemData("양산", 0f, 0f));
        list.add(new itemData("거제 통영 고성군", 0f, 0f));
        list.add(new itemData("진주 사천", 0f, 0f));
        list.add(new itemData("남해 하동", 0f, 0f));
        list.add(new itemData("창원 진해", 0f, 0f));
        list.add(new itemData("마산", 0f, 0f));
        list.add(new itemData("거창 함안 창녕 합천 산청", 0f, 0f));
        list.add(new itemData("밀양", 0f, 0f));
        setMap("경남", list);

        list = new ArrayList<>();
        list.add(new itemData("동성로 중구 서구", 0f, 0f));
        list.add(new itemData("수성구 남구 수성못", 0f, 0f));
        list.add(new itemData("동대구역 동구 신천 대구공항", 0f, 0f));
        list.add(new itemData("경북대 북구 칠곡", 0f, 0f));
        list.add(new itemData("성서 죽전 달서 달성", 0f, 0f));
        setMap("대구", list);

        list = new ArrayList<>();
        list.add(new itemData("경주", 0f, 0f));
        list.add(new itemData("구미 김천 의성", 0f, 0f));
        list.add(new itemData("포항 영덕", 0f, 0f));
        list.add(new itemData("울진 울릉도 청송", 0f, 0f));
        list.add(new itemData("영천 칠곡 경산 청도 성주 고령", 0f, 0f));
        list.add(new itemData("문경 상주 안동 영주 예천", 0f, 0f));
        setMap("경북", list);

        list = new ArrayList<>();
        list.add(new itemData("동구", 0f, 0f));
        list.add(new itemData("중구", 0f, 0f));
        list.add(new itemData("남구", 0f, 0f));
        list.add(new itemData("북구", 0f, 0f));
        list.add(new itemData("울주군", 0f, 0f));
        setMap("울산", list);

        list = new ArrayList<>();
        list.add(new itemData("유성구", 0f, 0f));
        list.add(new itemData("중구 은행", 0f, 0f));
        list.add(new itemData("동구 대덕 용전", 0f, 0f));
        list.add(new itemData("서구 둔산 괴정", 0f, 0f));
        setMap("대전", list);

        list = new ArrayList<>();
        list.add(new itemData("천안", 0f, 0f));
        list.add(new itemData("세종", 0f, 0f));
        list.add(new itemData("계룡 공주 금산 논산", 0f, 0f));
        list.add(new itemData("아산 예산 청양 홍성", 0f, 0f));
        list.add(new itemData("서산 태안 당진 안면도", 0f, 0f));
        list.add(new itemData("대천 서천 보령 부여", 0f, 0f));
        setMap("충남", list);

        list = new ArrayList<>();
        list.add(new itemData("청주", 0f, 0f));
        list.add(new itemData("충주 제천", 0f, 0f));
        list.add(new itemData("진천 음성 단양", 0f, 0f));
        list.add(new itemData("증평 괴산 영동 옥천 보은", 0f, 0f));
        setMap("충북", list);

        list = new ArrayList<>();
        list.add(new itemData("광산구", 0f, 0f));
        list.add(new itemData("동구", 0f, 0f));
        list.add(new itemData("서구", 0f, 0f));
        list.add(new itemData("남구", 0f, 0f));
        list.add(new itemData("북구", 0f, 0f));
        setMap("광주", list);

        list = new ArrayList<>();
        list.add(new itemData("순천", 0f, 0f));
        list.add(new itemData("여수 광양", 0f, 0f));
        list.add(new itemData("목포 무안 해남 나주 영암", 0f, 0f));
        list.add(new itemData("화순 보성 담양 구례 곡성", 0f, 0f));
        setMap("전남", list);

        list = new ArrayList<>();
        list.add(new itemData("전주 완주", 0f, 0f));
        list.add(new itemData("군산 익산", 0f, 0f));
        list.add(new itemData("김제 부안 임실 정읍 남원", 0f, 0f));
        setMap("전북", list);

        list = new ArrayList<>();
        list.add(new itemData("제주시", 0f, 0f));
        list.add(new itemData("서귀포시", 0f, 0f));
        setMap("제주", list);

        /*
        //데이터 확인
        HashMap<String, List<itemData>> map = getItem();
        for(String key : map.keySet()){
            for (int i = 0; i < map.get(key).size(); i++) {
                Log.e(TAG, key + " " + map.get(key).get(i).getLocName());
            }
        }
        */

    }
}
