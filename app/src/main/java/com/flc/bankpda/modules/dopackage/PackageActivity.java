package com.flc.bankpda.modules.dopackage;

import android.os.Bundle;

import com.flc.bankpda.R;
import com.flc.bankpda.base.BaseMvpActivity;
import com.flc.bankpda.base.mvp.factory.CreatePresenter;
import com.flc.bankpda.modules.dopackage.presenter.PackageContract;
import com.flc.bankpda.modules.dopackage.presenter.PackagePresenter;

@CreatePresenter(PackagePresenter.class)
public class PackageActivity extends BaseMvpActivity<PackageContract.View, PackagePresenter> implements PackageContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_package;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public void onCheckPackage() {

    }
}
