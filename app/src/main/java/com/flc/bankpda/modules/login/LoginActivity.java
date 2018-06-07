package com.flc.bankpda.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flc.bankpda.R;
import com.flc.bankpda.base.BaseMvpActivity;
import com.flc.bankpda.base.mvp.factory.CreatePresenter;
import com.flc.bankpda.modules.home.HomeActivity;
import com.flc.bankpda.modules.login.presenter.LoginContract;
import com.flc.bankpda.modules.login.presenter.LoginPresenter;

@CreatePresenter(LoginPresenter.class)
public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    private EditText et_account;
    private EditText et_password;
    private TextView tv_login;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        et_account = ((EditText) findViewById(R.id.et_account));
        et_password = ((EditText) findViewById(R.id.et_password));
        tv_login = ((TextView) findViewById(R.id.tv_login));





        tv_login.setOnClickListener(v -> {
                getPresenter().requestLogin();
        });
    }

    @Override
    public void onLoginResponse() {
        Toast.makeText(mContext,"登录成功",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mContext, HomeActivity.class));
    }
}
