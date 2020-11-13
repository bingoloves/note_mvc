package com.github.base.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import com.github.base.R;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bingo on 2020/11/11.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 页面导航器
 * @UpdateUser: bingo
 * @UpdateDate: 2020/11/11
 */

public class Navigator {
    private Intent intent;
    private Consumer consumer;
    private boolean closeCurrntActivity = false;//跳转是否关闭当前页面,默认不关闭
    private static long lastStartActivityTime;//最近启动activity的时间间隔
    private int[] enterAnim = {R.anim.slide_in_right, R.anim.slide_out_left};
    private int[] exitAnim = {R.anim.slide_in_left, R.anim.slide_out_right};
    private static NavigatorCache navigatorCache;//保存跳转的退出动画

    private Navigator(Consumer consumer){
        this.consumer = consumer;
        this.intent = new Intent();
    }
    public static Navigator with(Activity activity){
        return new Navigator(new ActivityConsumer(activity));
    }
    public static Navigator with(Fragment fragment){
        return new Navigator(new FragmentConsumer(fragment));
    }
    public static Navigator with(android.support.v4.app.Fragment fragment){
        return new Navigator(new FragmentV4Consumer(fragment));
    }

    public Navigator withString(String key,String value){
        intent.putExtra(key,value);
        return this;
    }
    public Navigator withInt(String key,int value){
        intent.putExtra(key,value);
        return this;
    }
    public Navigator withByte(String key,byte value){
        intent.putExtra(key,value);
        return this;
    }
    public Navigator withChar(String key,char value){
        intent.putExtra(key,value);
        return this;
    }
    public Navigator withLoog(String key,long value){
        intent.putExtra(key,value);
        return this;
    }
    public Navigator withFloat(String key,float value){
        intent.putExtra(key,value);
        return this;
    }
    public Navigator withDouble(String key,double value){
        intent.putExtra(key,value);
        return this;
    }
    public Navigator withBoolean(String key,boolean value){
        intent.putExtra(key,value);
        return this;
    }

    public Navigator withSerializable(String key,Serializable value){
        intent.putExtra(key,value);
        return this;
    }
    public Navigator withEnterAnim(int enterAnim, int exitAnim){
        this.enterAnim[0] = enterAnim;
        this.enterAnim[1] = exitAnim;
        return this;
    }
    public Navigator withExitAnim(int enterAnim, int exitAnim){
        this.exitAnim[0] = enterAnim;
        this.exitAnim[1] = exitAnim;
        return this;
    }
    public Navigator closeCurrentActivity(boolean close){
        this.closeCurrntActivity = close;
        return this;
    }

    public void navigate(@NonNull Class<?> cls){
        if (!canStartActivity()) return;
        lastStartActivityTime = System.currentTimeMillis();
        Activity activity = consumer.getActivity();
        navigatorCache = new NavigatorCache(cls,exitAnim);
        intent.setClass(activity,cls);
        activity.startActivity(intent);
        activity.overridePendingTransition(enterAnim[0],enterAnim[1]);
        if (closeCurrntActivity){
            Handler handler = new Handler();
            handler.postDelayed(() -> activity.finish(),300);
        }
    }
    public void navigateForResult(@NonNull Class<?> cls,int code){
        if (!canStartActivity()) return;
        lastStartActivityTime = System.currentTimeMillis();
        Activity activity = consumer.getActivity();
        Object object = consumer.getObject();
        navigatorCache = new NavigatorCache(cls,exitAnim);
        intent.setClass(activity,cls);
        if (object instanceof Activity){
            ((Activity) object).startActivityForResult(intent,code);
        } else if (object instanceof Fragment){
            ((Fragment) object).startActivityForResult(intent,code);
        } else if(object instanceof android.support.v4.app.Fragment){
            ((android.support.v4.app.Fragment) object).startActivityForResult(intent,code);
        }
        activity.overridePendingTransition(enterAnim[0],enterAnim[1]);
        if (closeCurrntActivity){
            Handler handler = new Handler();
            handler.postDelayed(() -> activity.finish(),300);
        }
    }
    static class FragmentV4Consumer implements Consumer{
        private android.support.v4.app.Fragment fragment;
        public FragmentV4Consumer(android.support.v4.app.Fragment fragment){
            this.fragment = fragment;
        }
        @Override
        public Object getObject() {
            return fragment;
        }

        @Override
        public Activity getActivity() {
            return fragment.getActivity();
        }
    }
    static class FragmentConsumer implements Consumer{
        private Fragment fragment;
        public FragmentConsumer(Fragment fragment){
            this.fragment = fragment;
        }
        @Override
        public Object getObject() {
            return fragment;
        }

        @Override
        public Activity getActivity() {
            return fragment.getActivity();
        }
    }
    static class ActivityConsumer implements Consumer{
        private Activity activity;
        public ActivityConsumer(Activity activity){
            this.activity = activity;
        }
        @Override
        public Object getObject() {
            return activity;
        }

        @Override
        public Activity getActivity() {
            return activity;
        }
    }

    public static NavigatorCache getNavigatorCache(){
        return navigatorCache;
    }
    /**
     * 当前消费对象
     */
    interface Consumer{
        Object getObject();
        Activity getActivity();
    }
    /**
     * 是否可以启动activity
     * @return
     */
    private boolean canStartActivity() {
        return System.currentTimeMillis() - lastStartActivityTime > 800;
    }
}
