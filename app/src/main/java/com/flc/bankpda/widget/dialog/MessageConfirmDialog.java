package com.flc.bankpda.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flc.bankpda.R;
import com.flc.bankpda.utils.DeviceUtils;
import com.flc.bankpda.widget.dialog.callback.CancelCallback;
import com.flc.bankpda.widget.dialog.callback.ConfirmCallback;

public class MessageConfirmDialog extends LinearLayout{


    private final Context mContext;
    TextView tv_message;
    LinearLayout ll_root;
    Dialog mDialog;
    private TextView tv_confirm;
    private TextView tv_cancel;
    private CancelCallback cancelCallback;
    private ConfirmCallback confirmCallback;

    public void setCancelCallback(CancelCallback cancelCallback) {
        this.cancelCallback = cancelCallback;
    }

    public void setConfirmCallback(ConfirmCallback confirmCallback) {
        this.confirmCallback = confirmCallback;
    }

    public MessageConfirmDialog(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {

        LayoutInflater.from(mContext).inflate(R.layout.dialog_message_confirm, this);

        tv_message = ((TextView) findViewById(R.id.tv_message));
        tv_confirm = ((TextView) findViewById(R.id.tv_confirm));
        tv_cancel = ((TextView) findViewById(R.id.tv_cancel));

        ll_root = ((LinearLayout) findViewById(R.id.ll_root));
        ll_root.setLayoutParams(new LinearLayout.LayoutParams((int) (DeviceUtils.getScreenWidth(mContext) * 0.64f), LinearLayout.LayoutParams.WRAP_CONTENT));

        mDialog = new Dialog(mContext, R.style.AlertDialogStyle);
        mDialog.setContentView(this);


        tv_cancel.setOnClickListener(v -> {
            if (cancelCallback != null) {
                cancelCallback.cancel();
            }
            mDialog.dismiss();
        });

        tv_confirm.setOnClickListener(v -> {
            if (confirmCallback != null) {
                confirmCallback.confirm();
            }
            mDialog.dismiss();
        });
    }

    public void setMessage(String message) {
        tv_message.setText(message);
    }

    public void setCancelText(String text) {
        tv_cancel.setText(text);
    }
    public void setConfirmText(String text) {
        tv_confirm.setText(text);
    }

    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }
}
