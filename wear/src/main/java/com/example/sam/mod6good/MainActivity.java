package com.example.sam.mod6good;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends WearableActivity implements View.OnClickListener, SensorEventListener {

    public static final String TAG = "MainActivity";
    private TextView mTextViewHeart;
    private Button button2;
    private long currentTime;
    public  AlertDialog.Builder builder ;
    public  int stress = 0;
    private SensorManager mSensorManager;
    private Sensor mHeartRateSensor;

// adb -e logcat MainActivity:i *:S > mod6log.txt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAmbientEnabled();
        setContentView(R.layout.round_activity_main);
        button2 = (Button) findViewById(R.id.ButtonOptions);
        button2.setOnClickListener(this);
        mTextViewHeart = (TextView) findViewById(R.id.heart);
        mSensorManager = ((SensorManager)getSystemService(SENSOR_SERVICE));
        mHeartRateSensor = mSensorManager.getDefaultSensor(21);
        Log.i("", "LISTENER REGISTERED.");
        mTextViewHeart.setText("Stressmeter");
        currentTime = System.currentTimeMillis();
        Log.d("timestamp: ", ""+(currentTime/1000000));
       mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_FASTEST);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        startActivity(new Intent("com.example.sam.mod6good.MusicActivity"));
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.cancel();
                        break;
                }
            }
        };

      builder = new AlertDialog.Builder(mTextViewHeart.getContext());
        builder.setTitle("I measure stress\nListen to music?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ButtonOptions:
                button2Click();
                break;
        }
    }

    private void button2Click() {
        startActivity(new Intent("com.example.sam.mod6good.Options"));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            int hr = (int)event.values[0];
            String msg = "" + (int)event.values[0] + " ";
            mTextViewHeart.setText(msg);
            String time = "" + System.currentTimeMillis();
            Log.i(TAG, msg + " " + time);
            if(hr>70){
                stress+= 1;
            }
            if(hr>100){
                stress += 2;
            }
            if (stress > 10) {

                builder.show();
                stress = 0;
            }
        }
        else
            Log.d(TAG, "Unknown sensor type");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged - accuracy: " + accuracy);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_FASTEST);


    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mHeartRateSensor);


    }

}
