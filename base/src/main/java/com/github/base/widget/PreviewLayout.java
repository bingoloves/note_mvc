package com.github.base.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.base.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingo on 2020/11/5.
 * 预览的View
 */

public class PreviewLayout extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private TextView numTv;
    private Context context;
    private List<Object> picList;
    private PreviewPagerAdapter pagerAdapter;
    private int current = 0;

    public PreviewLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PreviewLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        picList = new ArrayList<>();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_base_preview,this);
        mViewPager = view.findViewById(R.id.viewPager);
        numTv = view.findViewById(R.id.tv_num);
        pagerAdapter = new PreviewPagerAdapter();
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(current);
        mViewPager.addOnPageChangeListener(this);
    }

    public void update(int current,ArrayList<Object> paths){
        this.current = current;
        this.picList = paths;
        pagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(current);
        numTv.setText((current+1)+"/"+picList.size());
    }
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        numTv.setText((position+1)+"/"+picList.size());
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private class PreviewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return picList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view.equals(o);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(context, R.layout.layout_base_preview_item, null);
            ImageView imageView = view.findViewById(R.id.iv_pic);
            RequestOptions requestOptions = new RequestOptions().centerCrop().override(800, 600);
            Glide.with(context).load(picList.get(position)).apply(requestOptions).into(imageView);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                imageView.setTransitionName("preview"+position);
//            }
            container.addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            System.gc();
        }
    }
}
