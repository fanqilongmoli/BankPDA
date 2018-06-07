package com.flc.bankpda.modules.dopackage.presenter;

import com.flc.bankpda.base.mvp.BaseContract;

public interface PackageContract {

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void checkPackage();
    }

    interface View extends BaseContract.BaseView {
        void onCheckPackage();
    }
}
