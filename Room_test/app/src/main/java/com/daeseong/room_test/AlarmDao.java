package com.daeseong.room_test;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface AlarmDao {

    @Query("SELECT * FROM alarms")
    List<Alarm> getAll();

    @Insert
    void insert(Alarm alarm);

    @Update
    void update(Alarm alarm);

    @Delete
    void delete(Alarm alarm);

    @Query("DELETE FROM alarms WHERE id = (SELECT MIN(id) FROM alarms)")
    void deleteOldest();

    @Query("SELECT * FROM alarms WHERE id = (SELECT MIN(id) FROM alarms)")
    Alarm getOldest();

    @Query("DELETE FROM alarms")
    void clearAlarms();

    @Query("SELECT COUNT(*) FROM alarms")
    int getRowCount();

    @Query("SELECT EXISTS(SELECT 1 FROM alarms WHERE title = :title LIMIT 1)")
    boolean isAlarmExists(String title);

    @Query("SELECT * FROM alarms WHERE title = :title LIMIT 1")
    Alarm getAlarmByTitle(String title);
}
