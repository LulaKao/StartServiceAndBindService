package com.lulakao.servicepractice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class MyService extends Service {

    @Override
    public void onCreate() {
        // The service is being created
        Log.d("MyService", "=== onCreate() ===");
        super.onCreate();
    }

    //====== Call to startService() START ======//
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        Log.d("MyService", "=== onStartCommand() ===");
        // Do the task below
        // ...
        Toast.makeText(this, "Start Service...", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }
    //====== Call to startService() END ======//

    //====== Call to bindService() START ======//
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService", "=== onBind() ===");
        return null;
    }
    //====== Call to bindService() END ======//

    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        super.onDestroy();
        Log.d("MyService", "=== onDestroy() ===");
    }
}
