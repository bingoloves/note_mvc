package com.github.base.debug;

import java.io.Serializable;

/**
 * Created by bingo on 2020/11/13.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 日志打印信息详情
 * @UpdateUser: bingo
 * @UpdateDate: 2020/11/13
 */

public class LoggerInfo implements Serializable{
    private String tag;
    private String head;
    private String message;
    private int type;
    private long currentTime;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LoggerInfo(int type,String tag, String message) {
        this.tag = tag;
        this.message = message;
        this.type = type;
        this.currentTime = System.currentTimeMillis();
    }
    public LoggerInfo(int type,String tag,String head, String message) {
        this.tag = tag;
        this.message = message;
        this.head = head;
        this.type = type;
        this.currentTime = System.currentTimeMillis();
    }
}
