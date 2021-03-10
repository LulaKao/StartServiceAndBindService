package com.lulakao.servicepractice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // this is for startService() and stopService()
    private Intent intent_start;

    // this is for bindService() and unbindService()
    private Intent intent_bind;
    private MyService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startTask();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    //====== onCreate() START ======//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //====== onCreate() END ======//

    //====== onClick : Call to startService() START ======//
    public void startService(View view) {
        intent_start = new Intent(this, MyService.class);
        Log.d("MyService","startService --- intent_start = " + intent_start);
        startService(intent_start);

        /** 當 Service 使用 startService() 啟動後，就算 Activity 被關閉，Service 也會持續在 Background 工作。
         *  Service 與 Activity 之間沒有交集，Service 完成任務也不會回傳東西給原來的應用程式。
         *
         *  應用程式呼叫 startService() 後，系統呼叫 Service 類別內的 onCreate()，接著呼叫 onStartCommand()，
         *  利用 Intent 提供的參數做事。直到工作結束或是應用程式呼叫 stopService() 才會停止。    */
    }
    //====== onClick : Call to startService() END ======//

    //====== onClick : Call to stopService() START ======//
    public void stopService(View view) {
        Log.d("MyService","stopService --- intent_start = " + intent_start);
        if(intent_start != null) { // 加上此判斷式，才不會因用戶點了 Start 的按鈕，但 intent_start = null 而 shutdown
            stopService(intent_start);
            intent_start = null; // 使用完畢後指定 intent_start = null 以利 GC 回收
        }
    }
    //====== onClick : Call to stopService() END ======//

    //====== onClick : Call to bindService() START ======//
    public void bindService(View view) {
        intent_bind = new Intent(this, MyService.class);
        Log.d("MyService","bindService --- intent_bind = " + intent_bind);
        bindService(intent_bind, connection, BIND_AUTO_CREATE);
    }
    //====== onClick : Call to bindService() END ======//

    //====== onClick : Call to unbindService() START ======//
    public void unbindService(View view) {
        Log.d("MyService","unbindService --- intent_bind = " + intent_bind);
        if(intent_bind != null) { // 加上此判斷式，才不會因用戶點了 Unbind 的按鈕，但 intent_bind = null 而 shutdown
            unbindService(connection);
            intent_bind = null; // 使用完畢後指定 intent_bind = null 以利 GC 回收
        }
    }
    //====== onClick : Call to unbindService() END ======//
}