package com.github.base.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.base.R;
import com.github.base.adapter.listview.CommonAdapter;
import com.github.base.adapter.listview.ViewHolder;
import com.github.base.utils.DensityUtils;
import com.github.base.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingo on 2020/11/5.
 * 网格类布局
 */
public class NineGridLayout extends FrameLayout {
    private Context context;
    private GridView mGridView;
    //每張圖片的宽度，单位px
    private int desireSize = 180;
    //图片中间的间距
    private int columnSpace = 4;
    //动态计算的图片宽度
    private int columnWidth = 180;
    //图片圆角
    private int radius = 0;
    private CommonAdapter<String> gridAdapter;
    private List<String> list;
    private OnItemClickListener onItemClickListener;

    public NineGridLayout(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public NineGridLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public NineGridLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        list = new ArrayList<>();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_base_grid,this);
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PhotoView);
//        try {
//            radius = ta.getInteger(R.styleable.PhotoView_radius,0);
//        } catch (Exception e){
//            e.printStackTrace();
//        } finally {
//            ta.recycle();
//        }
        mGridView = view.findViewById(R.id.grid);
        initGridView();
    }

    private void initGridView() {
        gridAdapter = new CommonAdapter<String>(context, R.layout.layout_base_grid_item,list) {
            @Override
            protected void convert(ViewHolder viewHolder, String path, int position) {
                ImageView imageView = viewHolder.getView(R.id.photoView);
                RequestOptions requestOptions = new RequestOptions().centerCrop().override(columnWidth,columnWidth);
                Glide.with(context).load(path).apply(requestOptions).into(imageView);
            }
        };
        mGridView.setAdapter(gridAdapter);
        mGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onGlobalLayout() {
                int width = mGridView.getWidth();
                int numCount = width / desireSize;
                int newColumnWidth = (width - columnSpace * (numCount - 1)) / numCount;
                if (columnWidth == newColumnWidth) {
                    return;
                }
                columnWidth = newColumnWidth;
                mGridView.setColumnWidth(columnWidth);
                gridAdapter.notifyDataSetChanged();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mGridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
        mGridView.setOnItemClickListener((parent, view, position, id) -> {
            if (onItemClickListener != null){
                onItemClickListener.onClick(position);
            }
        });
    }

    /**
     * 动态更新数据
     * @param images
     */
    public void update(List<String> images){
        this.list = images;
        gridAdapter.update(images);
    }

    /**
     * 设置点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onClick(int position);
    }
}
