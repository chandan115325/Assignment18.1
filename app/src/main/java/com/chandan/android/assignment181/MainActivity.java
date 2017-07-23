package com.chandan.android.assignment181;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Declaring Views objects;
    private TextView mBatteryLevelText;
    private ProgressBar mBatteryLevelProgress;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Referncing to the respective views

        mBatteryLevelText=(TextView)findViewById(R.id.textView);
        mBatteryLevelProgress=(ProgressBar)findViewById(R.id.progressBar);

        mReceiver = new BatteryBroadcastReceiver();

    }

    @Override
    protected  void onStart(){
       //Registering broadcast receiver
        registerReceiver(mReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();
    }
        //Unregistering broadcast receiver
    @Override
    protected void onStop(){
        unregisterReceiver(mReceiver);
        super.onStop();
    }
    //Innner class extending broadcast receiver
    private class BatteryBroadcastReceiver extends BroadcastReceiver{
        /**
         * using BatteryManager to get the response.
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            mBatteryLevelText.setText(getString(R.string.battery_level)+" "+level +"%");
            mBatteryLevelProgress.setProgress(level);
        }
    }
}
