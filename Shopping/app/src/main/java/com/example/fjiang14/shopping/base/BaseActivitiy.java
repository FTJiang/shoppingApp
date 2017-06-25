package com.example.fjiang14.shopping.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fjiang14.shopping.home.MainHomeActivity;
import com.example.fjiang14.shopping.R;

import java.util.Timer;
import java.util.TimerTask;


public class BaseActivitiy extends BoreBaseActivity {

    public SharedPreferences sp;

    private boolean couldDoubleBackExit;
    private boolean doubleBackExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 如果是退出应用flag,则直接关闭当前页面,不加载UI
        boolean exit = getIntent().getBooleanExtra("exit", false);
        if (exit) {
            finish();
            return;
        }

        sp = getSharedPreferences(this.getString(R.string.SP_NAME), MODE_PRIVATE);
    }

    /**
     * 设置是否可以双击返回退出，需要有该功能的页面set true即可
     *
     * @param couldDoubleBackExit true-开启双击退出
     */
    public void setCouldDoubleBackExit(boolean couldDoubleBackExit) {
        this.couldDoubleBackExit = couldDoubleBackExit;
    }

    @Override
    public void onBackPressed() {
        if (!couldDoubleBackExit) {
            // 非双击退出状态，使用原back逻辑
            super.onBackPressed();
            return;
        }

        // 双击返回键关闭程序
        // 如果两秒重置时间内再次点击返回,则退出程序
        if (doubleBackExitPressedOnce) {
            exit();
            return;
        }

        doubleBackExitPressedOnce = false;
        showToast("One more press for terminate");
        Timer tExit = new Timer();
        tExit.schedule(new TimerTask() {
            @Override
            public void run() {
                doubleBackExitPressedOnce = true;; // 取消退出
            }
        }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

    }

    /**
     * 退出程序
     */
    protected void exit() {
        // 退出程序方法有多种
        // 这里使用clear + new task的方式清空整个任务栈,只保留新打开的Main页面
        // 然后Main页面接收到退出的标志位exit=true,finish自己,这样就关闭了全部页面
        Intent intent = new Intent(this, MainHomeActivity.class);
        intent.putExtra("exit", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
