package edu.csulb.android.arttherapy;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class DrawActivity extends AppCompatActivity implements SensorEventListener {

    DrawingView dv ;
    static Paint mPaint;
    private SensorManager sensorManager;
    private long lastupdate;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       constraintLayout=(ConstraintLayout)findViewById(R.id.mainview);
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
        dv = new DrawingView(this);
        setContentView(dv);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
       // mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        //mPaint.setStrokeJoin(Paint.Join.ROUND);
      //  mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        lastupdate= System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            checkShake(event);
        }
    }

    private void checkShake(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float acclsqrt =(x*x + y*y + z*z)/(SensorManager.GRAVITY_EARTH* SensorManager.GRAVITY_EARTH);
        long actualtime = System.currentTimeMillis();

        if(acclsqrt >=2)
        {
            if(actualtime - lastupdate <200)
                return;
            lastupdate=actualtime;
           // Toast.makeText(this, "Motion!", Toast.LENGTH_SHORT).show();
            dv.clearDrawing();
            Intent sound = new Intent(DrawActivity.this,PlaySound.class);
            startService(sound);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
