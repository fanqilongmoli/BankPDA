package com.flc.bankpda.modules.dopackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanDevice;
import android.os.Bundle;

import com.flc.bankpda.R;
import com.flc.bankpda.base.BaseMvpActivity;
import com.flc.bankpda.base.mvp.factory.CreatePresenter;
import com.flc.bankpda.modules.dopackage.presenter.PackageContract;
import com.flc.bankpda.modules.dopackage.presenter.PackagePresenter;

@CreatePresenter(PackagePresenter.class)
public class PackageActivity extends BaseMvpActivity<PackageContract.View, PackagePresenter> implements PackageContract.View {



    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            byte[] barocode = intent.getByteArrayExtra("barocode");
            int barocodelen = intent.getIntExtra("length", 0);
            byte temp = intent.getByteExtra("barcodeType", (byte) 0);
            android.util.Log.i("debug", "----codetype--" + temp);
//            barcodeStr = new String(barocode, 0, barocodelen);
//            showScanResult.append("�㲥�����");
//            showScanResult.append(barcodeStr);
//            showScanResult.append("\n");
//            //       showScanResult.setText(barcodeStr);
//            sm.stopScan();
        }

    };




    @Override
    protected int getLayoutId() {
        return R.layout.activity_package;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        new ScanDevice();
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
        unregisterReceiver(mScanReceiver);
    }

    @Override
    public void onCheckPackage() {

    }
}
