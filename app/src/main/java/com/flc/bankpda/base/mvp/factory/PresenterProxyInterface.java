package com.flc.bankpda.base.mvp.factory;


import com.flc.bankpda.base.mvp.BaseContract;

/**
 * Created by fanqilong on 2018/4/23.
 * 代理接口
 */

public interface PresenterProxyInterface<V extends BaseContract.BaseView,P extends BaseContract.BasePresenter<V>> {
    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory);
    /**
     * 获取Presenter的工厂类
     * @return 返回PresenterMvpFactory类型
     */
    PresenterMvpFactory<V,P> getPresenterFactory();
    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     */
    P getPresenter();
}
