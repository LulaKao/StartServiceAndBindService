package com.lulakao.servicepractice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //====== Call to startService() START ======//
        // 在 Activity 中呼叫 startService() 來執行第一種啟動 Service 的方法
        Intent intent = new Intent(this, MyService.class);
        this.startService(intent);
        //====== Call to startService() END ======//

        /** 當 Service 使用 startService() 啟動後，就算 Activity 被關閉，Service 也會持續在 Background 工作。
         *  Service 與 Activity 之間沒有交集，Service 完成任務也不會回傳東西給原來的應用程式。
         *
         *  應用程式呼叫 startService() 後，系統呼叫 Service 類別內的 onCreate()，接著呼叫 onStartCommand()，
         *  利用 Intent 提供的參數做事。直到工作結束或是應用程式呼叫 stopService() 才會停止。    */
    }
}