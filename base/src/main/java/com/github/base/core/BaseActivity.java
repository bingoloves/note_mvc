package com.github.base.core;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.base.utils.Injector;


/**
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity activity;
    public Handler mainHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        super.onCreate(savedInstanceState);
        activity = this;
        mainHandler = new Handler();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Injector.inject(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        Injector.inject(this);
    }

    /**
     * 简单统一调用自定义 Toast
     */
    public void toast(@NonNull CharSequence message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
