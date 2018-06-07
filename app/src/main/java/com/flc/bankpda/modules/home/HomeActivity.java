package com.flc.bankpda.modules.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.flc.bankpda.R;
import com.flc.bankpda.base.BaseMvpActivity;
import com.flc.bankpda.base.mvp.factory.CreatePresenter;
import com.flc.bankpda.modules.dopackage.PackageTypeChooseActivity;
import com.flc.bankpda.modules.home.presenter.HomeContract;
import com.flc.bankpda.modules.home.presenter.HomePresenter;

@CreatePresenter(HomePresenter.class)
public class HomeActivity extends BaseMvpActivity<HomeContract.View, HomePresenter> implements HomeContract.View {

    private TextView tv_package;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tv_package = ((TextView) findViewById(R.id.tv_package));


        tv_package.setOnClickListener(v -> startActivity(new Intent(mContext, PackageTypeChooseActivity.class)));
    }

    @Override
    public void onHomeInfoResponse() {

    }
}
