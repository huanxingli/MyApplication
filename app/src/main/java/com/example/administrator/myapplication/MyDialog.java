package com.example.administrator.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/16.
 */

public class MyDialog extends Dialog implements View.OnClickListener {

    private TextView mTextViewTitle;
    private TextView mTextViewActionTake;
    private TextView mTextViewActionPick;

    private String mTitle = "选择图片";
    private String[] mItems = new String[]{"拍照","相册"};
    private OnActionClickListener onActionClickListener;

    @Override
    public void onClick(View view) {
        dismiss();
        if (onActionClickListener!=null){
            switch (view.getId()){
                case R.id.tv_take_photo:
                    onActionClickListener.onActionClick(0);
                    break;
                case R.id.tv_pick_photo:
                    onActionClickListener.onActionClick(1);
                    break;
            }

        }
    }

    public interface OnActionClickListener{
        void onActionClick(int item);
    }

    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_photo);
        initView();
        initWidth();
    }

    /**
     * 设置对话框的宽度
     */
    private void initWidth() {
        Window dialogWin = getWindow();
        WindowManager.LayoutParams lp = dialogWin.getAttributes();
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        lp.width = (int) (dm.widthPixels * 0.8);
        dialogWin.setAttributes(lp);
    }

    private void initView() {
        setCancelable(true);
        mTextViewTitle = (TextView) findViewById(R.id.tv_dialog_title);
        mTextViewActionTake = (TextView) findViewById(R.id.tv_take_photo);
        mTextViewActionPick = (TextView) findViewById(R.id.tv_pick_photo);
        mTextViewTitle.setText(mTitle);
        mTextViewActionTake.setText(mItems[0]);
        mTextViewActionPick.setText(mItems[1]);
        mTextViewActionTake.setOnClickListener(this);
        mTextViewActionPick.setOnClickListener(this);

    }

    public MyDialog setOnActionClickListener(OnActionClickListener onActionClickListener){
        this.onActionClickListener = onActionClickListener;
        return this;
    }

    public MyDialog setTitle(String title){
        this.mTitle = title;
        return this;
    }

    public MyDialog setItems(String[] items){
        this.mItems = items;
        return this;
    }
}
