package com.flc.bankpda.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;


import com.flc.bankpda.base.mvp.BaseContract;
import com.flc.bankpda.base.mvp.BaseMvpProxy;
import com.flc.bankpda.base.mvp.factory.PresenterMvpFactory;
import com.flc.bankpda.base.mvp.factory.PresenterMvpFactoryImpl;
import com.flc.bankpda.base.mvp.factory.PresenterProxyInterface;
import com.flc.bankpda.utils.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by fanqilong on 2018/4/23.
 * 基础 activity
 */

public abstract class BaseMvpActivity<V extends BaseContract.BaseView, P extends BaseContract.BasePresenter<V>> extends RxAppCompatActivity implements PresenterProxyInterface<V, P>, BaseContract.BaseView {
    protected Context mContext;
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理的对象 传入默认的Presenter工厂
     */
    private BaseMvpProxy<V,P> mProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mContext = this;
        mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()),this);
        if (savedInstanceState !=null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        mProxy.onResume((V)this);
        initStatusBar();
        init(savedInstanceState);
    }

    /**
     * 布局文件
     * @return 获取布局文件
     */
    protected abstract @LayoutRes int getLayoutId();

    /**
     * 初始化StatusBar
     */
    protected void initStatusBar() {
        StatusBarUtil.immersive(this);
    }

    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);



    /**
     * 是否显示进度条
     * @param isShow 是否显示
     */
    @Override
    public void showProgressUI(boolean isShow) {

    }
    /**
     *
     * @param msg 错误信息
     * @param isLoadMore 是否加载更多，用来区分刷新出错还是加载更多出错
     */
    @Override
    public void showError(String msg, boolean isLoadMore) {

    }

    /**
     * 请求完成
     */
    @Override
    public void complete() {

    }

    // ===============================//

    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    /**
     * 获取Presenter的工厂类
     * @return
     */
    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    /**
     * 获取创建的Presenter
     * @return
     */
    @Override
    public P getPresenter() {
        return mProxy.getPresenter();
    }

    //====================//

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    protected void onDestroy() {
        mProxy.onDestroy();
        super.onDestroy();
    }
}
