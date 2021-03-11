package com.lulakao.servicepractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class MyService extends Service {
    private String TAG = "MyService";
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        // The service is being created
        Log.d(TAG, "=== onCreate() ===");

        //====== 創建前台 Service START ======//
        // 創建 NotificationManager
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        // 設定 PendingIntent（待定意圖）：點擊通知後，所會跳轉的頁面 -> 跳轉到 MainActivity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        // 建立 Notification.Builder 並設定屬性（高於 API Level 16 的版本）
        Notification notification = new Notification.Builder(this)
                .setAutoCancel(true) // 設定點選通知後，通知會自動消失
                .setContentTitle("This is Notification Title") // 設定通知標題
                .setContentText("This is Notification message...") // 設定通知內容
                .setSmallIcon(R.mipmap.ic_launcher) // 設定小 icon
                .setWhen(System.currentTimeMillis()) // 指定通知被建立的時間，以毫秒為單位，下拉通知後會將時間顯示在通知上
                .setOngoing(true) // 設定為正在進行
                .setContentIntent(pendingIntent) // 設定欲執行的 intent
                .setDefaults(Notification.DEFAULT_ALL) // 添加聲音、閃爍、震動的預設效果
                .setVisibility(Notification.VISIBILITY_PUBLIC) // 設定鎖定螢幕時是否允許顯示通知內容
                .build();

        // 執行 notification
        manager.notify(1,notification);
        //====== 創建前台 Service END ======//

        super.onCreate();
    }

    //====== Call to startService() START ======//
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        Log.d(TAG, "=== onStartCommand() ===");
        // 開啟子線程
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 模擬耗時操作，在子執行緒中進行
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyService.this, "onStartCommand : Start Service", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
    //====== Call to startService() END ======//

    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        Log.d(TAG, "=== onDestroy() ===");
        Toast.makeText(this, "Stop Service", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    //====== Call to bindService() START ======//
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        Log.d(TAG, "=== onBind() ===");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        Log.d(TAG, "=== onUnbind() ===");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
        Log.d(TAG, "=== onRebind() ===");
        super.onRebind(intent);
    }

    class MyBinder extends Binder{

        public void startTask(){
            Log.d(TAG, "=== MyBinder.startTask() ===");
            // 開啟子線程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 模擬耗時操作，在子執行緒中進行
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MyService.this, "MyBinder.startTask : Start Task", Toast.LENGTH_SHORT).show();
                        }
                    }, 5000);
                }
            }).start();
        }
    }
    //====== Call to bindService() END ======//
}
