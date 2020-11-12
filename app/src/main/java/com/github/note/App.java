package com.github.note;

import com.github.base.core.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by bingo on 2020/11/5.
 */

public class App extends BaseApplication {
    @Override
    public boolean isDebug() {
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
