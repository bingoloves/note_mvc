package com.github.note.activity;

import android.os.Bundle;
import android.view.View;

import com.github.base.core.AbsBaseActivity;
import com.github.base.utils.Injector;
import com.github.base.utils.Navigator;
import com.github.base.widget.NineGridLayout;
import com.github.note.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AbsBaseActivity {

    @Injector.OnClick({R.id.btn_click,R.id.btn_keyboard,R.id.btn_form})
    public void clickEvent(View v){
        switch (v.getId()){
            case R.id.btn_click:
                nineGridLayout.update(Arrays.asList(images));
                break;
            case R.id.btn_keyboard:
                Navigator.with(activity).navigate(KeyboardActivity.class);
                break;
            case R.id.btn_form:
                Navigator.with(activity).navigate(FormValidateActivity.class);
                break;
            default:
                break;
        }
    }
    @Injector.BindView(R.id.nineGridLayout)
    NineGridLayout nineGridLayout;
    private String[] images = {
            "https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg",
            "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=151472226,3497652000&fm=26&gp=0.jpg",
            "https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1689053532,4230915864&fm=26&gp=0.jpg",
            "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1473836766,4030812874&fm=26&gp=0.jpg",
            "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg"
    };

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        showToolbar(true);
        mCommonToolbar.setCenterTitle("首页");
        mCommonToolbar.setBackground(getResources().getColor(android.R.color.white));
        //setStatusBarDarkFont(true);
        nineGridLayout.setOnItemClickListener(position -> {
            Navigator.with(activity)
                    .withString("userName","xuebing")
                    .withInt("current",position)
                    .withSerializable("paths", new ArrayList<>(Arrays.asList(images)))
                    .navigate(TwoActivity.class);
        });
    }
}
