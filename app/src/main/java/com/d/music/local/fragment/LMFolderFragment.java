package com.d.music.local.fragment;

import com.d.music.R;
import com.d.music.local.adapter.FolderAdapter;
import com.d.music.model.AlbumModel;
import com.d.music.model.FolderModel;
import com.d.music.model.SingerModel;
import com.d.music.module.events.MusicModelEvent;
import com.d.music.module.greendao.db.MusicDB;
import com.d.music.module.greendao.music.base.MusicModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页-本地歌曲-文件夹
 * Created by D on 2017/4/29.
 */
public class LMFolderFragment extends AbstractLMFragment {
    private FolderAdapter adapter;

    @Override
    protected void lazyLoad() {
        List<FolderModel> datas = new ArrayList<>();
        adapter = new FolderAdapter(mContext, datas, R.layout.adapter_folder);
        xrvList.showAsList();
        xrvList.setCanRefresh(false);
        xrvList.setCanLoadMore(false);
        xrvList.setAdapter(adapter);
        mPresenter.getFolder();
    }

    @Override
    public void setSong(List<MusicModel> models) {

    }

    @Override
    public void setSinger(List<SingerModel> models) {

    }

    @Override
    public void setAlbum(List<AlbumModel> models) {

    }

    @Override
    public void setFolder(List<FolderModel> models) {
        adapter.setDatas(models);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void notifyDataCountChanged(int count) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MusicModelEvent event) {
        if (event == null || getActivity() == null || getActivity().isFinishing()
                || event.type != MusicDB.LOCAL_ALL_MUSIC || mPresenter == null || !isLazyLoaded) {
            return;
        }
        mPresenter.getFolder();
    }
}