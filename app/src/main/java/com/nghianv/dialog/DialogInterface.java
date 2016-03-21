package com.nghianv.dialog;

/**
 * Created by NguyenNghia on 3/19/2016.
 */
public interface DialogInterface {
    public interface OnCloseListener{
        public void onClose(DialogInterface dialog);
    }

    public interface OnDismissListener{
        public void onDismiss(DialogInterface dialog);
    }

    public interface OnShowListener{
        public void onShow(DialogInterface dialog);
    }

    public void show();
    public void dismiss();

}
