package com.nghianv.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;


/**
 * Created by nguyennghia on 29/03/2016.
 */
public class DialogBuilder {

    private final String TAG = DialogBuilder.class.getSimpleName();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private BaseAdapter mAdapter;

    private FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    private int mGravity = Gravity.CENTER;

    private int mBackgroundOverlay =  Color.parseColor("#66000000");

    private Animation mAnimationIn;
    private Animation mAnimationOut;

    private View mContentView;

    // left, top, right, bottom
    private int[] mMargin = new int[4];

    private int[] mPadding = new int[4];

    private TextView mTitle;
    private TextView mMessage;

    private Button mPositive;
    private Button mNegative;
    private Button mNeural;


    private DialogInterface.OnClickListener onClickPositiveListener;
    private DialogInterface.OnClickListener onClickNegativeListener;
    private DialogInterface.OnClickListener onClickNeuralListener;
    private DialogInterface.OnCancelListener onCancelListener;
    private DialogInterface.OnShowListener onShowListener;
    private DialogInterface.OnDismissListener onDismissListener;
    private DialogInterface.OnKeyListener onKeyListener;


    public DialogBuilder(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        params.gravity = mGravity;

        Arrays.fill(mMargin, 12);
        params.setMargins(mMargin[0], mMargin[1], mMargin[2], mMargin[3]);
    }

    public DialogBuilder setBackgroundOverlay(int color){
        mBackgroundOverlay = color;
        return this;
    }
    public DialogBuilder setTitle(CharSequence text){
        mTitle = new TextView(mContext);
        mTitle.setText(text);
        mTitle.setTextSize(18);
        mTitle.setTextColor(Color.parseColor("#17aa8e"));
        return this;
    }

    public DialogBuilder setTitle(@StringRes int resId){
        mTitle = new TextView(mContext);
        mTitle.setText(resId);
        return this;
    }

    public DialogBuilder setMessage(CharSequence text){
        mMessage = new TextView(mContext);
        mMessage.setText(text);
        mTitle.setTextSize(18);
        mTitle.setTextColor(Color.parseColor("#17aa8e"));
        return this;
    }

    public DialogBuilder setMessage(@StringRes int resId){
        mMessage = new TextView(mContext);
        mMessage.setText(resId);
        return this;
    }

    public DialogBuilder setGravity(int gravity){
        if(gravity == Gravity.TOP){
            int statusHeight = Utils.getStatusBarHeight(mContext);
            params.setMargins(mMargin[0], mMargin[1] + statusHeight, mMargin[2], mMargin[3]);
        }

        mGravity = gravity;
        params.gravity = gravity;
        return this;
    }

    public DialogBuilder setContentView(@LayoutRes int id){
        mContentView = mLayoutInflater.inflate(id, null);
        return this;
    }

    public DialogBuilder setContentView(View view){
        mContentView = view;
        return this;
    }

    public DialogBuilder setMargin(int left, int top, int right, int bottom){
        mMargin[0] = left;
        mMargin[1] = top;
        mMargin[2] = right;
        mMargin[3] = bottom;

        if(mGravity == Gravity.TOP)
         params.setMargins(left, top + Utils.getStatusBarHeight(mContext), right, bottom);
        params.setMargins(left, top + Utils.getStatusBarHeight(mContext), right, bottom);

        return this;
    }

    public DialogBuilder setPadding(int left, int top, int right, int bottom){
        mPadding[0] = left;
        mPadding[1] = top;
        mPadding[2] = right;
        mPadding[3] = bottom;
        return this;
    }

    public DialogBuilder setAnimationIn(@AnimRes int id){
        mAnimationIn = AnimationUtils.loadAnimation(mContext, id);
        return this;
    }

    public DialogBuilder setAnimationOut(@AnimRes int id){
        mAnimationOut = AnimationUtils.loadAnimation(mContext, id);
        return this;
    }

    public DialogBuilder setAdapter(BaseAdapter adapter){
        mAdapter = adapter;
        return this;
    }

    public  int[] getPadding(){
        return this.mPadding;
    }
    private void setStyleButton(Button button){
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.selector_button_dialog);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 0, 10, 0);

        if(button != null){
            button.setLayoutParams(params);
            button.setTextSize(14);
            button.setTextColor(Color.parseColor("#17aa8e"));
            if(Build.VERSION.SDK_INT >= 16){
                button.setBackground(drawable);
            }else{
                button.setBackgroundDrawable(drawable);
            }
        }
    }

    public DialogBuilder setPositiveButton(CharSequence text, final DialogInterface.OnClickListener listener){
        mPositive = new Button(mContext);
        mPositive.setText(text);
        setStyleButton(mPositive);
        onClickPositiveListener = listener;
        return this;
    }

    public DialogBuilder setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener){
        mNegative = new Button(mContext);
        mNegative.setText(text);
        setStyleButton(mNegative);
        onClickNegativeListener = listener;
        return this;
    }

    public DialogBuilder setNeuralButton(CharSequence text, DialogInterface.OnClickListener listener){
        mNeural = new Button(mContext);
        mNeural.setText(text);
        setStyleButton(mNeural);
        onClickNeuralListener = listener;
        return this;
    }

    public DialogBuilder setOnShowListener(DialogInterface.OnShowListener listener){
        onShowListener = listener;
        return this;
    }

    public DialogBuilder setOnCloseListener(DialogInterface.OnCancelListener listener){
        onCancelListener = listener;
        return this;
    }

    public DialogBuilder setOnDismissListener(DialogInterface.OnDismissListener listener){
        onDismissListener = listener;
        return this;
    }

    public DialogBuilder setOnKeyListener(DialogInterface.OnKeyListener listener){
        onKeyListener = listener;
        return this;
    }

    public int getGravity(){
        return this.mGravity;
    }

    public FrameLayout.LayoutParams getLayoutParams(){
        return this.params;
    }

    public DialogInterface.OnDismissListener getOnDismissListener(){
        return this.onDismissListener;
    }

    public DialogInterface.OnShowListener getOnShowListener(){
        return this.onShowListener;
    }

    public DialogInterface.OnCancelListener getOnCancelListener(){
        return this.onCancelListener;
    }

    public DialogInterface.OnClickListener getOnClickPositiveListener(){
        return this.onClickPositiveListener;
    }

    public DialogInterface.OnClickListener getOnClickNegativeListener(){
        return this.onClickNegativeListener;
    }

    public DialogInterface.OnClickListener getOnClickNeuralListener(){
        return this.onClickNeuralListener;
    }

    public DialogInterface.OnKeyListener getOnKeyListener(){
        return this.onKeyListener;
    }
    public Animation getmAnimationIn(){
        return this.mAnimationIn;
    }

    public Animation getmAnimationOut(){
        return this.mAnimationOut;
    }

    public BaseAdapter getAdapter(){
        return this.mAdapter;
    }

    public Context getContext(){
        return this.mContext;
    }

    public LayoutInflater getLayoutInflater(){
        return this.mLayoutInflater;
    }


    public int getBackgroundOverlay(){
        return this.mBackgroundOverlay;
    }
    public View getContentView(){
        return this.mContentView;
    }

    public TextView getTitleView(){
        return this.mTitle;
    }

    public TextView getMessageView(){
        return this.mMessage;
    }

    public Button getPositiveButton(){
        return  this.mPositive;
    }

    public Button getNeuralButton(){
        return this.mNeural;
    }

    public Button getNagetiveButton(){
        return this.mNegative;
    }
    public Dialog create(){
        return new Dialog(this);
    }


}