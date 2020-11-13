package com.github.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.github.base.debug.LoggerActivity;
import com.github.base.R;
import com.github.base.debug.LoggerInfo;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.Screen;
import com.yhao.floatwindow.ViewStateListener;

/**
 * Created by bingo on 2020/11/13.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 日志窗口工具
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/13
 */

public class LoggerWindowUtils {
    private LoggerWindowUtils(){}

    private static class LoggerWindowUtilsHolder{
        private static LoggerWindowUtils INSTANCE = new LoggerWindowUtils();
    }

    public static LoggerWindowUtils getInstance(){
        return LoggerWindowUtilsHolder.INSTANCE;
    }
    private ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(int x, int y) {

        }

        @Override
        public void onShow() {

        }

        @Override
        public void onHide() {

        }

        @Override
        public void onDismiss() {

        }

        @Override
        public void onMoveAnimStart() {

        }

        @Override
        public void onMoveAnimEnd() {

        }

        @Override
        public void onBackToDesktop() {

        }
    };
    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {

        }

        @Override
        public void onFail() {

        }
    };
    /**
     * 是否已经初始化
     */
    private boolean hasInit = false;
    public void init(){
        if (hasInit) return;
        Context context = Utils.getApp().getApplicationContext();
        View window = LayoutInflater.from(context).inflate(R.layout.layout_float_window,null);
        FloatWindow
                .with(context)
                .setView(window)
                .setWidth(100)                               //设置控件宽高
                .setHeight(Screen.width,0.2f)
                .setX(Screen.width,0.8f)              //设置控件初始位置
                .setY(Screen.height,0.8f)
                .setDesktopShow(true)                        //桌面显示
                .setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
                .setPermissionListener(mPermissionListener)  //监听权限申请结果
                .build();
        window.setOnClickListener(v -> {
//            Intent intent = new Intent(context, LoggerActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
            Activity activity = ActivityStackManager.getStackManager().currentActivity();
            if (activity != null){
                Navigator.with(activity).navigate(LoggerActivity.class);
            }
        });
        hasInit = true;
    }
    /**
     * 显示窗口
     */
    public void show(){
        if (!hasInit){
            LogUtils.e("please do init");
            return;
        }
        boolean showing = FloatWindow.get().isShowing();
        if (!showing){
            FloatWindow.get().show();
        }
    }

    /**
     * 隐藏窗口
     */
    public void hide(){
        FloatWindow.get().hide();
    }

}
