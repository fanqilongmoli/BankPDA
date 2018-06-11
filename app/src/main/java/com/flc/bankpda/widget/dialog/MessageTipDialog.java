package com.flc.bankpda.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flc.bankpda.R;
import com.flc.bankpda.utils.DeviceUtils;
import com.flc.bankpda.widget.dialog.callback.CancelCallback;

public class MessageTipDialog extends LinearLayout {

    private Context mContext;
    private Dialog mDialog;
    LinearLayout ll_root;
    private TextView tv_btn;
    private TextView tv_message;
    private CancelCallback cancelCallback;

    public void setCancelCallback(CancelCallback cancelCallback) {
        this.cancelCallback = cancelCallback;
    }

    public MessageTipDialog(Context context) {
        this(context, null);
    }

    public MessageTipDialog(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageTipDialog(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }


    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.dialog_message_tip, this);
        tv_btn = ((TextView) findViewById(R.id.tv_btn));
        tv_message = ((TextView) findViewById(R.id.tv_message));
        ll_root = ((LinearLayout) findViewById(R.id.ll_root));
        ll_root.setLayoutParams(new LinearLayout.LayoutParams((int) (DeviceUtils.getScreenWidth(mContext) * 0.64f), LinearLayout.LayoutParams.WRAP_CONTENT));

        mDialog = new Dialog(mContext, R.style.AlertDialogStyle);
        mDialog.setContentView(this);


        tv_btn.setOnClickListener(v -> {
            if (cancelCallback != null) {
                cancelCallback.cancel();
            }
            mDialog.dismiss();
        });
    }

    public void setMessage(String message) {
        tv_message.setText(message);
    }

    public void setBtnText(String text) {
        tv_btn.setText(text);
    }

    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }
}
