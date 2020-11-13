package com.github.base.debug;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.base.R;
import com.github.base.adapter.recyclerview.CommonAdapter;
import com.github.base.adapter.recyclerview.base.ViewHolder;
import com.github.base.adapter.recyclerview.wrapper.EmptyWrapper;
import com.github.base.core.AbsBaseActivity;
import com.github.base.utils.Injector;
import com.github.base.utils.LogUtils;
import com.github.base.widget.ExpandableTextView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingo on 2020/11/13.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 日志查看工具页面
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/13
 */

public class LoggerActivity extends AbsBaseActivity{
    RecyclerView mRecyclerView;
    CommonAdapter<LoggerInfo> mAdapter;
    EmptyWrapper emptyWrapper;
    List<LoggerInfo> loggerList = new ArrayList<>();
    @Override
    public int getContentView() {
        return R.layout.activity_logger;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        showToolbar(true);
        EventBus.getDefault().registerSticky(this);
        mCommonToolbar.setCenterTitle("日志");
        mCommonToolbar.setBackground(getResources().getColor(android.R.color.white));
        mCommonToolbar.back(v -> finish());
        mCommonToolbar.showBaseLine();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommonAdapter<LoggerInfo>(this,R.layout.layout_logger_item,loggerList) {
            @Override
            protected void convert(ViewHolder holder, LoggerInfo bean, int position) {
                ExpandableTextView expandableTextView = holder.getView(R.id.expand_view);
                expandableTextView.setText(bean.getMessage());
            }
        };

        emptyWrapper = new EmptyWrapper(mAdapter);
        emptyWrapper.setEmptyView(R.layout.layout_empty_view);
        mRecyclerView.setAdapter(emptyWrapper);
    }

    @Subscriber
    public void onReceiveEvent(LoggerInfo event) {
        LogUtils.e("1111111");
        if (event != null){
            loggerList.add(event);
            mAdapter.add(event);
            emptyWrapper.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
