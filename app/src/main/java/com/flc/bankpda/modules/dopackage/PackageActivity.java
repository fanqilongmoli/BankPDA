package com.flc.bankpda.modules.dopackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanDevice;
import android.os.Bundle;
import android.util.Log;

import com.flc.bankpda.R;
import com.flc.bankpda.base.BaseMvpActivity;
import com.flc.bankpda.base.mvp.factory.CreatePresenter;
import com.flc.bankpda.modules.dopackage.presenter.PackageContract;
import com.flc.bankpda.modules.dopackage.presenter.PackagePresenter;
import com.flc.bankpda.widget.dialog.MessageConfirmDialog;

@CreatePresenter(PackagePresenter.class)
public class PackageActivity extends BaseMvpActivity<PackageContract.View, PackagePresenter> implements PackageContract.View {

    private ScanDevice mScanDevice;

    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            byte[] barocode = intent.getByteArrayExtra("barocode");
            int barocodelen = intent.getIntExtra("length", 0);
            byte temp = intent.getByteExtra("barcodeType", (byte) 0);
            Log.i("debug", "----codetype--" + temp);
            String barcodeStr = new String(barocode, 0, barocodelen);
            Log.e("debug","----barcodeStr--" + barcodeStr);
            // TODO: 2018/6/8 扫描后 添加扫描信息的处理
            mScanDevice.stopScan();
        }

    };



    @Override
    protected int getLayoutId() {
        return R.layout.activity_package;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mScanDevice = new ScanDevice();
        //1 键盘输出；0 广播方式
        mScanDevice.setOutScanMode(0);
        // 设置联系扫描 4   关闭联系扫描 8
//        mScanDevice.setScanLaserMode(4);



        findViewById(R.id.tv_cancel_package).setOnClickListener(v -> {
            MessageConfirmDialog messageConfirmDialog = new MessageConfirmDialog(mContext);
            messageConfirmDialog.setMessage("放弃装包吗？");
            messageConfirmDialog.setCancelText("取消");
            messageConfirmDialog.setConfirmText("确定");
            messageConfirmDialog.show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("scan.rcv.message");
        registerReceiver(mScanReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mScanDevice!=null){
//            mScanDevice.setScanLaserMode(8);
            mScanDevice.stopScan();
        }

        unregisterReceiver(mScanReceiver);

    }

    @Override
    public void onCheckPackage() {

    }
}
