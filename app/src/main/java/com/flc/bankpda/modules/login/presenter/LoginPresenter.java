package com.flc.bankpda.modules.login.presenter;

import com.flc.bankpda.base.mvp.factory.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter<LoginContract.View> {
    @Override
    public void requestLogin() {
        mView.onLoginResponse();
    }
}
