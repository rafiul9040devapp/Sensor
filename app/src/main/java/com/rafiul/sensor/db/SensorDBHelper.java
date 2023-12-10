package com.rafiul.sensor.db;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class SensorDBHelper {

    private AppDatabase database;

    public SensorDBHelper(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, "sensor_database").build();
    }

    public Completable insertSensorData(SensorData sensorData) {
        return database.sensorDataDao().insertSensorData(sensorData);
    }

    public Observable<List<SensorData>> getAllSensorData() {
       return database.sensorDataDao().getAllSensorData();
    }
}
