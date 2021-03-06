package com.d.music.local.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.d.lib.common.module.mvp.MvpView;
import com.d.lib.common.module.mvp.base.BaseFragment;
import com.d.lib.xrv.XRecyclerView;
import com.d.music.R;
import com.d.music.local.presenter.LMMusicPresenter;
import com.d.music.local.view.ILMMusicView;
import com.d.music.view.sort.SideBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * LazyLoad Fragment
 * Created by D on 2017/4/30.
 */
public abstract class AbstractLMFragment extends BaseFragment<LMMusicPresenter> implements ILMMusicView {
    @BindView(R.id.xrv_list)
    XRecyclerView xrvList;
    @BindView(R.id.sb_sidebar)
    SideBar sbSideBar;

    protected boolean isVisibleToUser;
    protected boolean isLazyLoaded;
    private boolean isPrepared;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_local_sort;
    }

    @Override
    protected int getDSLayoutRes() {
        return R.id.dsl_ds;
    }

    @Override
    public LMMusicPresenter getPresenter() {
        return new LMMusicPresenter(getActivity().getApplicationContext());
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void init() {
        isPrepared = true;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onVisible();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            this.isVisibleToUser = true;
            onVisible();
        } else {
            this.isVisibleToUser = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        if (!isPrepared || !isVisibleToUser) {
            return;
        }
        isPrepared = false;//仅仅懒加载加载一次
        isLazyLoaded = true;
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
