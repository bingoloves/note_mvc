package com.github.note.activity;

import android.os.Bundle;
import android.os.Handler;
import com.github.base.core.AbsBaseActivity;
import com.github.base.utils.Navigator;
import com.github.note.R;

/**
 * Created by bingo on 2020/11/6.
 */

public class SplashActivity extends AbsBaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean fullScreen() {
        return true;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
         new Handler().postDelayed(() -> {
             Navigator.with(this).withString("name","xuebing").closeCurrentActivity(true).navigate(MainActivity.class);
        },1200);

    }
}
