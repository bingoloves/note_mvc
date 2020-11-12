package com.github.base.glide;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Parcel;
import android.support.annotation.Nullable;

import com.previewlibrary.enitity.IThumbViewInfo;

/**
 * Created by bingo on 2020/11/12.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/12
 */

public class PreviewInfo implements IThumbViewInfo {
    private String url;  //图片地址
    private Rect mBounds; // 记录坐标
    private String videoUrl;//视频链接 //不为空是视频

    public PreviewInfo(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {//将你的图片地址字段返回
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public Rect getBounds() {//将你的图片显示坐标字段返回
        return mBounds;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setBounds(Rect bounds) {
        mBounds = bounds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.mBounds, flags);
        dest.writeString(this.videoUrl);
    }
    protected PreviewInfo(Parcel in) {
        this.url = in.readString();
        this.mBounds = in.readParcelable(Rect.class.getClassLoader());
        this.videoUrl = in.readString();
    }

    public static final Creator<PreviewInfo> CREATOR = new Creator<PreviewInfo>() {
        @Override
        public PreviewInfo createFromParcel(Parcel source) {
            return new PreviewInfo(source);
        }

        @Override
        public PreviewInfo[] newArray(int size) {
            return new PreviewInfo[size];
        }
    };
}
