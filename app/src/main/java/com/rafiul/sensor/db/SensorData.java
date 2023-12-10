package com.rafiul.sensor.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sensor_data")
public class SensorData {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private long timestamp;
    private float proximity;
    private float light;
    private float accelerometerX;
    private float accelerometerY;
    private float accelerometerZ;
    private float gyroscopeX;
    private float gyroscopeY;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getProximity() {
        return proximity;
    }

    public void setProximity(float proximity) {
        this.proximity = proximity;
    }

    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
    }

    public float getAccelerometerX() {
        return accelerometerX;
    }

    public void setAccelerometerX(float accelerometerX) {
        this.accelerometerX = accelerometerX;
    }

    public float getAccelerometerY() {
        return accelerometerY;
    }

    public void setAccelerometerY(float accelerometerY) {
        this.accelerometerY = accelerometerY;
    }

    public float getAccelerometerZ() {
        return accelerometerZ;
    }

    public void setAccelerometerZ(float accelerometerZ) {
        this.accelerometerZ = accelerometerZ;
    }

    public float getGyroscopeX() {
        return gyroscopeX;
    }

    public void setGyroscopeX(float gyroscopeX) {
        this.gyroscopeX = gyroscopeX;
    }

    public float getGyroscopeY() {
        return gyroscopeY;
    }

    public void setGyroscopeY(float gyroscopeY) {
        this.gyroscopeY = gyroscopeY;
    }

    public float getGyroscopeZ() {
        return gyroscopeZ;
    }

    public void setGyroscopeZ(float gyroscopeZ) {
        this.gyroscopeZ = gyroscopeZ;
    }

    private float gyroscopeZ;

}
