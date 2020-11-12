package com.github.note.activity;

import android.os.Bundle;
import android.view.View;

import com.github.base.core.AbsBaseActivity;
import com.github.base.utils.Injector;
import com.github.base.widget.PreviewLayout;
import com.github.note.R;
import java.util.ArrayList;

public class PreviewActivity extends AbsBaseActivity {
    @Injector.IntentParam
    int current;
    @Injector.IntentParam
    ArrayList<Object> paths;
    @Injector.BindView(R.id.previewLayout)
    PreviewLayout previewLayout;

    @Injector.OnClick(R.id.iv_back)
    public void clickEvent(View view){
        finish();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_preview;
    }

    @Override
    public boolean fullScreen() {
        return true;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        showToolbar(false);
        previewLayout.update(current,paths);
    }
}
