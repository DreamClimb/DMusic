package com.d.dmusic.module.events;

/**
 * Created by D on 2017/5/7.
 */
public class RefreshEvent {
    public final static int SYNC_CUSTOM_LIST = -100;//刷新首页自定义歌曲列表
    public final static int SYNC_COLLECTIONG = -101;//刷新歌曲页
    public int type;
    public int event;

    public RefreshEvent(int event) {
        this.event = event;
    }
}
