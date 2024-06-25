package com.testdemo.apps;

import static android.content.Context.SENSOR_SERVICE;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.testdemo.apps.databinding.ActivityAccelerometerBinding;

import java.util.StringTokenizer;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    private String TAG = "AccelerometerActivity";
    private ActivityAccelerometerBinding binding;
    private SensorManager sensorManager;
    private Sensor accelerometer, gyroscope, magnetometer, slight, spressure, stemperature, shumidity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccelerometerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        slight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        spressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        stemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        shumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        int zs=0;
        Log.e(TAG, "zs :: "+zs++);
        Log.e(TAG, "zs: "+(++zs));


       /* while (zs<=5){

            //Toast.makeText(this, "Value: "+zs, Toast.LENGTH_SHORT).show();

            zs++;
        }*/

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            binding.tvXValues.setText("X: " + event.values[0]);
            binding.tvYValues.setText("Y: " + event.values[1]);
            binding.tvZValues.setText("Z: " + event.values[2]);
            //  Log.e("Accelerometer", "x: " + event.values[0] + ", y: " + event.values[1] + ", z: " +event.values[2]);
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            binding.tvXGyrValues.setText("X: " + event.values[0]);
            binding.tvYGyrValues.setText("Y: " + event.values[1]);
            binding.tvZGyrValues.setText("Z: " + event.values[2]);
           // Log.e("Gyroscope", "x: " + event.values[0] + ", y: " + event.values[1] + ", z: " + event.values[2]);
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            binding.tvXMagnoValues.setText("X: " + event.values[0]);
            binding.tvYMagnoValues.setText("Y: " + event.values[1]);
            binding.tvZMagnoValues.setText("Z: " + event.values[2]);
           // Log.e("Magnetometer", "x: " + event.values[0] + ", y: " + event.values[1] + ", z: " + event.values[2]);
        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            binding.tvLight.setText("Light Values: " + event.values[0]);
            //  Log.e("Light", "x: " + event.values[0]);
        } else if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            binding.tvPressure.setText("Pressure Values: " + event.values[0]);
        } else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            binding.tvTemp.setText("Temp Values: " + event.values[0]);
        } else if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            binding.tvHumi.setText("Humidity Values: " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.e(TAG, "onAccuracyChanged: " + sensor.getName() + " Accuracy: " + accuracy);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e(TAG, "onStart: accelerometer not supported");
        }
        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e(TAG, "onStart: gyroscope not supported");
        }
        if (magnetometer != null) {
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e(TAG, "onStart: magnetometer not supported");
        }
        if (slight != null) {
            sensorManager.registerListener(this, slight, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e(TAG, "onStart: light not supported");
        }
        if (spressure != null) {
            sensorManager.registerListener(this, spressure, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e(TAG, "onStart: pressure not supported");
        }
        if (stemperature != null) {
            sensorManager.registerListener(this, stemperature, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e(TAG, "onStart: temperature not supported");
        }
        if (shumidity != null) {
            sensorManager.registerListener(this, shumidity, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e(TAG, "onStart: humidity not supported");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

}