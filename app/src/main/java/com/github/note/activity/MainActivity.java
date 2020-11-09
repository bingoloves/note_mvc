package com.github.note.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;

import com.github.base.core.ImmersiveActivity;
import com.github.base.utils.Injector;
import com.github.base.utils.LogUtils;
import com.github.base.widget.NineGridLayout;
import com.github.note.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ImmersiveActivity {

    @Injector.OnClick({R.id.btn_click,R.id.btn_keyboard,R.id.btn_form})
    public void clickEvent(View v){
        switch (v.getId()){
            case R.id.btn_click:
                nineGridLayout.update(Arrays.asList(images));
                break;
            case R.id.btn_keyboard:
                startActivity(new Intent(activity,KeyboardActivity.class));
                break;
            case R.id.btn_form:
                startActivity(new Intent(activity,FormValidateActivity.class));
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nineGridLayout.setOnItemClickListener(position -> {
            LogUtils.e("11111111");
            Intent intent = new Intent(activity, TwoActivity.class);
            intent.putExtra("userName","bingo");
            intent.putExtra("current",position);
            intent.putExtra("paths", new ArrayList<String>(Arrays.asList(images)));
            startActivity(intent);
        });
    }
}
