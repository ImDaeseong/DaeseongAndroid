package com.daeseong.room_test;

import androidx.room.TypeConverter;
import java.util.Date;

public class Converters {

    //Room에서 Date와 같은 일부 데이터 형식을 SQLite에서 지원하는 형식으로 변환하기 위해 TypeConverter를 사용

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
