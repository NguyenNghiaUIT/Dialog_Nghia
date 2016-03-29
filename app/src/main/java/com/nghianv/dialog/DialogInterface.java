package com.nghianv.dialog;

import android.view.KeyEvent;

/**
 * Created by NguyenNghia on 3/19/2016.
 */
public interface DialogInterface {
    public static final int BUTTON_POSITIVE = -1;

    public static final int BUTTON_NEGATIVE = -2;

    public static final int BUTTON_NEUTRAL = -3;


    void show();
    void dismiss();
    void cancel();

    interface OnCancelListener{
        void onCancel(DialogInterface dialog);
    }

    interface OnDismissListener{
        void onDismiss(DialogInterface dialog);
    }

    interface OnShowListener{
        void onShow(DialogInterface dialog);
    }

    interface OnClickListener{
         void onClick(DialogInterface dialog, int which);
    }

    interface OnKeyListener{
        void onKey(KeyEvent event);
    }

    interface OnClickItemListener{
        void onClickItem(DialogInterface dialog, int position);
    }



}
