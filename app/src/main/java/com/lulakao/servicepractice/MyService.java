package com.lulakao.servicepractice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class MyService extends Service {
    private MyBinder myBinder = new MyBinder();

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
        Toast.makeText(this, "onStartCommand : Start Service", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }
    //====== Call to startService() END ======//

    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        Log.d("MyService", "=== onDestroy() ===");
        Toast.makeText(this, "Stop Service", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    //====== Call to bindService() START ======//
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService", "=== onBind() ===");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("MyService", "=== onUnbind() ===");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("MyService", "=== onRebind() ===");
        super.onRebind(intent);
    }

    class MyBinder extends Binder{

        public void startTask(){
            Log.d("MyService", "=== MyBinder.startTask() ===");
            // Do the task below
            // ...
            Toast.makeText(MyService.this, "MyBinder.startTask : Start Task", Toast.LENGTH_SHORT).show();
        }
    }
    //====== Call to bindService() END ======//
}
