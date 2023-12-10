package com.rafiul.sensor.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface SensorDataDao {

    @Insert
    Completable insertSensorData(SensorData sensorData);
    @Query("SELECT * FROM sensor_data")
    Observable<List<SensorData>> getAllSensorData();


}
