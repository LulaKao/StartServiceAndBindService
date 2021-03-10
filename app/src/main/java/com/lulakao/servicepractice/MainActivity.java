package com.lulakao.servicepractice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent intent_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //====== onClick : Call to startService() START ======//
    public void startService(View view) {
        intent_service = new Intent(this, MyService.class);
        Log.d("MyService","startService --- intent_service = " + intent_service);
        startService(intent_service);

        /** 當 Service 使用 startService() 啟動後，就算 Activity 被關閉，Service 也會持續在 Background 工作。
         *  Service 與 Activity 之間沒有交集，Service 完成任務也不會回傳東西給原來的應用程式。
         *
         *  應用程式呼叫 startService() 後，系統呼叫 Service 類別內的 onCreate()，接著呼叫 onStartCommand()，
         *  利用 Intent 提供的參數做事。直到工作結束或是應用程式呼叫 stopService() 才會停止。    */
    }
    //====== onClick : Call to startService() END ======//

    //====== onClick : Call to stopService() START ======//
    public void stopService(View view) {
        Log.d("MyService","stopService --- intent_service = " + intent_service);
        stopService(intent_service);
    }
    //====== onClick : Call to stopService() END ======//
}