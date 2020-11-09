package com.github.note.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.base.core.ImmersiveActivity;
import com.github.base.utils.Injector;
import com.github.base.widget.PreviewLayout;
import com.github.note.R;

import java.util.ArrayList;

public class TwoActivity extends ImmersiveActivity {

    @Injector.IntentParam(name = "userName")
    String name;
    @Injector.IntentParam
    int current;
    @Injector.IntentParam
    ArrayList<Object> paths;

    @Injector.BindView(R.id.tv_name)
    TextView nameTv;
    @Injector.BindView(R.id.previewLayout)
    PreviewLayout previewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        nameTv.setText(name);
        previewLayout.update(current,paths);
    }
}
