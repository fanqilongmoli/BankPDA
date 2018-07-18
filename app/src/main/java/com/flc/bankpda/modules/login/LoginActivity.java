package com.flc.bankpda.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flc.bankpda.R;
import com.flc.bankpda.base.BaseMvpActivity;
import com.flc.bankpda.base.bean.LoginRequest;
import com.flc.bankpda.base.bean.RequestBase;
import com.flc.bankpda.base.mvp.factory.CreatePresenter;
import com.flc.bankpda.modules.home.HomeActivity;
import com.flc.bankpda.modules.login.presenter.LoginContract;
import com.flc.bankpda.modules.login.presenter.LoginPresenter;
import com.flc.bankpda.utils.serialport.SerialPortUtil;
import com.google.gson.Gson;

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

            String account = et_account.getText().toString();
            String password = et_password.getText().toString();

            LoginRequest loginRequest = new LoginRequest(account,password);
            byte[] bytes = loginRequest.getBytes();


//            RequestBase requestBase = new RequestBase();
//            requestBase.setUrl("admin-user/login");
//            requestBase.setMethod("POST");
//            requestBase.setBody(bytes);
//            String s = requestBase.toRequest();
//            SerialPortUtil.getInstance().send(s);

            RequestBase requestBase = new RequestBase();
            requestBase.setUrl("pack-rules");
            requestBase.setMethod("GET");
            requestBase.setToken("Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6IkIwMTQwQjIzMjAxMDAwMSIsImlhdCI6MTUzMTc0NjY3NywiZXhwIjoxNTMxODMzMDc3fQ.TAnEz49CE-q3L6qBRR_xqaEqE_NQh4--444grgVUy6c");
            requestBase.setCode("B0001L232010001");
            String s = requestBase.toRequest();
            SerialPortUtil.getInstance().send(s);

        });
    }

    @Override
    public void onLoginResponse() {
        Toast.makeText(mContext,"登录成功",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mContext, HomeActivity.class));
    }
}
