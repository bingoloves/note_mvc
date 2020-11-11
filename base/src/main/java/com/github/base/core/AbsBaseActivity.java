package com.github.base.core;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.base.R;
import com.github.base.utils.ActivityStackManager;
import com.github.base.utils.Injector;
import com.github.base.utils.LogUtils;
import com.github.base.utils.Navigator;
import com.github.base.utils.NavigatorCache;
import com.github.base.widget.CustomToolbar;
import com.gyf.immersionbar.ImmersionBar;

/**
 * Created by bingo on 2020/11/11.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 通用标准的Activity,包含默认Toolbar
 * @UpdateUser: bingo
 * @UpdateDate: 2020/11/11
 */

public abstract class AbsBaseActivity extends AppCompatActivity {
    protected Activity activity;
    protected LinearLayout mRootView;
    protected LinearLayout mContentView;
    protected CustomToolbar mCommonToolbar;
    protected ImmersionBar mImmersionBar;
    /**
     * 获取当前的容器的layoutId
     * @return
     */
    public abstract int getContentView();
    public abstract void onInitView(Bundle savedInstanceState);

    /**
     * 是否延伸到状态中，主要为图片延伸到状态栏进行处理
     * @return
     */
    public boolean fullScreen(){
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View mRootView = getLayoutInflater().inflate(R.layout.activity_base_ui, null);
        setContentView(mRootView);
        this.activity = this;
        initRootView(mRootView);
        initImmersionBar();
        Injector.inject(this);
        onInitView(savedInstanceState);
    }

    private void initRootView(View view) {
        mContentView = (LinearLayout) view.findViewById(R.id.base_content_container);
        mCommonToolbar = (CustomToolbar) view.findViewById(R.id.base_toolbar);
        mRootView = (LinearLayout) view.findViewById(R.id.base_root);
        getLayoutInflater().inflate(getContentView(), mContentView, true);
        mCommonToolbar.setVisibility(View.GONE);
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        if (!fullScreen()){//非全屏状态下的包含Toolbar
            mImmersionBar.titleBar(mCommonToolbar);
            mImmersionBar.fitsSystemWindows(true)
                    .statusBarColor(android.R.color.white)
                    .autoDarkModeEnable(true)
                    .navigationBarColor("#ffffff")
                    .navigationBarDarkIcon(true);
        }
        mImmersionBar.init();
    }

    /**
     * 是否显示自定义标题栏
     * @param show
     */
    public void showToolbar(boolean show){
        if (mCommonToolbar != null){
            mCommonToolbar.setVisibility(show?View.VISIBLE:View.GONE);
        }
    }
    /**
     * 透明状态栏
     */
    protected void setStatusBarTrans() {
        if (mImmersionBar != null) {
            mImmersionBar.transparentStatusBar().init();
        }
    }

    /**
     * 透明状态栏 状态栏文字是否深色
     */
    protected void setStatusBarDarkFont(boolean darkFont) {
        if (mImmersionBar != null) {
            mImmersionBar.transparentStatusBar().statusBarDarkFont(darkFont).init();
        }
    }
    /**
     * 设置中间容器的背景
     * @param resid
     */
    protected void setContentViewBackgroundResource(@DrawableRes int resid){
        if (mContentView != null){
            mContentView.setBackgroundResource(resid);
        }
    }

    /**
     * 设置中间容器的背景颜色
     * @param color
     */
    protected void setContentViewBackgroundColor(@ColorInt int color){
        if (mContentView != null){
            mContentView.setBackgroundColor(color);
        }
    }
    protected void toast(@NonNull CharSequence message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        super.finish();
        int size = ActivityStackManager.getActivityStack().size();
        LogUtils.e("栈中activity数量："+size);
        NavigatorCache navigatorCache = Navigator.getNavigatorCache();
        if (navigatorCache != null){
            Activity act = ActivityStackManager.getStackManager().getActivity(navigatorCache.targetActivity);
            boolean doExitAnim = this.activity.equals(act) && size > 1;
            if (doExitAnim){
                this.activity.overridePendingTransition(navigatorCache.exitAnim[0],navigatorCache.exitAnim[1]);
            } else {
                this.activity.overridePendingTransition(0,0);
            }
        }
    }
}
