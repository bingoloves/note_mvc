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
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.base.R;
import com.github.base.adapter.listview.CommonAdapter;
import com.github.base.adapter.listview.ViewHolder;
import com.github.base.glide.CornerTransform;
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
    //图片水平间距
    private int horizontalSpacing;
    //图片的垂直间距
    private int verticalSpacing;
    //动态计算的图片宽度
    private int itemWidth;
    //动态计算的图片高度
    private int itemHeight;
    //图片圆角
    private int radius;
    //网格列数
    private int columns = 4;
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
        View view = LayoutInflater.from(context).inflate(R.layout.layout_base_grid,this);
        this.context = context;
        list = new ArrayList<>();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NineGridLayout);
        try {
            radius = ta.getInteger(R.styleable.NineGridLayout_radius,4);
            itemHeight = ta.getDimensionPixelSize(R.styleable.NineGridLayout_itemHeight,dp2px(context,90));
            horizontalSpacing = ta.getDimensionPixelSize(R.styleable.NineGridLayout_horizontalSpacing,dp2px(context,2));
            verticalSpacing = ta.getDimensionPixelSize(R.styleable.NineGridLayout_verticalSpacing,dp2px(context,2));
            columns = ta.getInteger(R.styleable.NineGridLayout_columns,columns);
            LogUtils.e("radius = "+radius+"----columns = "+columns+"----horizontalSpacing = "+horizontalSpacing+"----verticalSpacing ="+verticalSpacing);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ta.recycle();
        }
        mGridView = view.findViewById(R.id.grid);
        mGridView.setNumColumns(columns);
        mGridView.setHorizontalSpacing(horizontalSpacing);
        mGridView.setVerticalSpacing(verticalSpacing);
        initGridView();
    }

    private void initGridView() {
        gridAdapter = new CommonAdapter<String>(context, R.layout.layout_base_grid_item,list) {
            @Override
            protected void convert(ViewHolder viewHolder, String path, int position) {
                ImageView imageView = viewHolder.getView(R.id.imageView);
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.width = itemWidth;
                layoutParams.height = itemHeight;
                CornerTransform transformation = new CornerTransform(context, dp2px(context, radius));
                transformation.setExceptCorner(false, false, false, false);
                RequestOptions requestOptions = new RequestOptions().dontAnimate()
                        .override(itemWidth,itemHeight)
                        .transform(transformation);
                Glide.with(context).load(path)
                        .apply(requestOptions)
                        .into(imageView);
            }
        };
        mGridView.setAdapter(gridAdapter);
//        mGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            public void onGlobalLayout() {
//                int width = mGridView.getWidth();
//                int newColumnWidth = (width - horizontalSpacing * (columns - 1)) / columns;
//                if (itemWidth == newColumnWidth) {
//                    return;
//                }
//                itemWidth = newColumnWidth;
//                mGridView.setColumnWidth(itemWidth);
//                gridAdapter.notifyDataSetChanged();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                } else {
//                    mGridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                }
//            }
//        });
        mGridView.setOnItemClickListener((parent, view, position, id) -> {
            if (onItemClickListener != null){
                onItemClickListener.onClick(position);
            }
        });
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context 上下文
     * @param dpValue 尺寸dip
     * @return 像素值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 动态更新数据
     * @param images
     */
    public void update(List<String> images){
        this.list = images;
        int width = mGridView.getWidth();
        itemWidth = (width - horizontalSpacing * (columns - 1)) / columns;
        mGridView.setColumnWidth(itemWidth);
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
