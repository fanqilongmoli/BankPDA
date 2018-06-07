package com.flc.bankpda.base.mvp.factory;


import com.flc.bankpda.base.mvp.BaseContract;

/**
 * Created by fanqilong on 2018/4/23.
 * Presenter工厂接口
 */

public interface PresenterMvpFactory<V extends BaseContract.BaseView,P extends BaseContract.BasePresenter<V>> {
    P createPresenter();
}
