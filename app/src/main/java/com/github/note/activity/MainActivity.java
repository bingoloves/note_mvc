package com.github.note.activity;

import android.os.Bundle;
import android.view.View;

import com.github.base.core.AbsBaseActivity;
import com.github.base.glide.PreviewImageLoader;
import com.github.base.utils.Injector;
import com.github.base.utils.Navigator;
import com.github.base.widget.NineGridLayout;
import com.github.note.R;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.ZoomMediaLoader;
import com.wildma.pictureselector.PictureSelector;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AbsBaseActivity {

    @Injector.OnClick({R.id.btn_click,R.id.btn_keyboard,R.id.btn_form,R.id.btn_selected})
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
            case R.id.btn_selected:
                PictureSelector.create(activity,100).selectPicture();
//                        .openGallery(1)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
//                        .imageSpanCount(4)// 每行显示个数
//                        .selectionMode(1)// 多选 or 单选
//                        .previewImage(true)// 是否可预览图片
//                        .isCamera(true)// 是否显示拍照按钮
//                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                        .enableCrop(true)// 是否裁剪
//                        //.compressSavePath(getPath())//压缩图片保存地址
//                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                        .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                        .isGif(false)// 是否显示gif图片
//                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
//                        .circleDimmedLayer(false)// 是否圆形裁剪
//                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//                        .minimumCompressSize(100)// 小于100kb的图片不压缩
//                        .forResult(100);//结果回调onActivityResult code
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
        ZoomMediaLoader.getInstance().init(new PreviewImageLoader());
        //setStatusBarDarkFont(true);
        nineGridLayout.setOnItemClickListener((list,view,position) -> {
            //第三方预览框架
            GPreviewBuilder.from(activity)
                    .setData(list)
                    .setCurrentIndex(position)
                    .setType(GPreviewBuilder.IndicatorType.Number)
                    .start();
//            Navigator.with(activity)
//                    .withString("userName","xuebing")
//                    .withInt("current",position)
//                    .preview(view)
//                    .withEnterAnim(0,0)
//                    .withExitAnim(0,0)
//                    .withSerializable("paths", new ArrayList<>(Arrays.asList(images)))
//                    .navigate(PreviewActivity.class);
        });
    }
}
