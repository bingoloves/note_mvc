package com.github.base.utils;

import android.app.Activity;

/**
 * Created by bingo on 2020/11/11.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 页面跳转的导航器缓存 保存退出动画
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/11
 */

public class NavigatorCache {
    public Class<?> targetActivity;
    public int[] exitAnim;
    public NavigatorCache(Class<?> targetActivity,int[] exitAnim){
        this.targetActivity = targetActivity;
        this.exitAnim = exitAnim;
    }
}
