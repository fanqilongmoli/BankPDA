package com.flc.bankpda.modules.dopackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.flc.bankpda.R;
import com.flc.bankpda.base.BaseMvpActivity;

public class PackageTypeChooseActivity extends BaseMvpActivity {


    private ImageView iv_package2;
    private ImageView iv_package1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_package_type_choose;
    }

    @Override
    protected void init(Bundle savedInstanceState) {


        iv_package2 = ((ImageView) findViewById(R.id.iv_package2));
        iv_package1 = ((ImageView) findViewById(R.id.iv_package1));


        // 点击一代包
        findViewById(R.id.rl_package1).setOnClickListener(v -> {
            iv_package1.setVisibility(View.VISIBLE);
            iv_package2.setVisibility(View.GONE);
        });
        // 点击二代包
        findViewById(R.id.rl_package2).setOnClickListener(v -> {
            iv_package1.setVisibility(View.GONE);
            iv_package2.setVisibility(View.VISIBLE);

        });


        findViewById(R.id.tv_confirm).setOnClickListener(v -> startActivity(new Intent(mContext,PackageActivity.class)));
    }
}
