package com.flc.bankpda.modules.home.presenter;

import com.flc.bankpda.base.mvp.BaseContract;

public interface HomeContract  {

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void requestHomeInfo();
    }

    interface View extends BaseContract.BaseView{
        void onHomeInfoResponse();
    }

}
