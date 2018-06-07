package com.flc.bankpda.modules.login.presenter;

import com.flc.bankpda.base.mvp.BaseContract;

public interface LoginContract {

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void requestLogin();
    }


    interface View extends BaseContract.BaseView {
        void onLoginResponse();
    }
}
