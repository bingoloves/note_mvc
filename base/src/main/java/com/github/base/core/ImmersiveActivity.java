package com.github.base.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.gyf.immersionbar.ImmersionBar;

/**
 * Created by bingo on 2020/9/27.
 * 对沉浸式状态栏的封装使用
 */

public class ImmersiveActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setFullScreen()){
            ImmersionBar.with(this)
                    .statusBarDarkFont(setStatusBarDarkFont())
                    .init();
        } else {
            ImmersionBar.with(this)
                    .fitsSystemWindows(true)
                    .statusBarDarkFont(setStatusBarDarkFont())
                    .statusBarColor(getStatusBarColor())
                    .init();
        }
    }

    /**
     * 非全屏模式下的状态栏背景颜色 默认白色
     * @return
     */
    protected int getStatusBarColor(){
        return android.R.color.white;
    }

    /**
     * 设置状态栏字体 默认深色字体
     * @return
     */
    protected boolean setStatusBarDarkFont(){
        return true;
    }

    /**
     * 设置是否延伸到状态栏的效果 -> 全屏效果  默认非全屏
     * @return
     */
    protected boolean setFullScreen(){
        return false;
    }
}
