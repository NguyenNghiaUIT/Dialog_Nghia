package com.nghianv.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.test.ActivityTestCase;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by NguyenNghia on 3/19/2016.
 */
public class Dialog implements DialogInterface{
    private Context mContext;
    private Window mWindow;
    private ViewGroup mDecorView;
    private ViewGroup mContainer;
    private View mShadowView;
    private View mContentView;
    private LayoutInflater mLayoutInflater;
    private int mGravity = Gravity.CENTER;
    private boolean mShowing;




    public Dialog(Builder builder){
        this.mContext = builder.mContext;
        this.mWindow = builder.mWindow;
        this.mDecorView = builder.mDecorView;
        this.mContainer = builder.mContainer;
        this.mContentView = builder.mContentView;
        this.mShadowView = builder.mShadowView;
        this.mLayoutInflater = builder.mLayoutInflater;
        this.mGravity = builder.mGravity;


        mContainer.addView(mContentView);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = mGravity;
        params.setMargins(32, 0, 32, 0);
        mContentView.setLayoutParams(params);
    }
    @Override
    public void show() {

        if(!isShowing())
        mDecorView.addView(mContainer);
        mShowing = true;
    }
    @Override
    public void dismiss() {
        if(isShowing())
            mDecorView.removeView(mContainer);
        mShowing = false;
    }

    public Context getContext(){
        return this.mContext;
    }

    public LayoutInflater getLayoutInflater(){
        return this.mLayoutInflater;
    }

    public View findViewById(@IdRes int id){
        return mContainer.findViewById(id);
    }

    private boolean isShowing(){
        return this.mShowing;
    }

    public static class Builder{
        private Context mContext;
        private Window mWindow;
        private ViewGroup mDecorView;
        private ViewGroup mContainer;
        private View mShadowView;
        private View mContentView;
        private int mGravity = Gravity.CENTER;
        private LayoutInflater mLayoutInflater;

        private ViewGroup mPanelTop;
        private ViewGroup mPanelView;
        private ViewGroup mPanelButton;

        private TextView mTitle;

        private TextView mMessage;

        private Button mPositive;

        private Button mNegative;

        private Button mNeutral;

        public Builder(Context context){
            this.mContext = context;
            this.mWindow = ((Activity)context).getWindow();
            this.mDecorView = (ViewGroup)mWindow.getDecorView();
            this.mLayoutInflater = LayoutInflater.from(context);
            this.mContainer = (ViewGroup)mLayoutInflater.inflate(R.layout.dl_container_layout, mDecorView, false);
            this.mShadowView = mContainer.findViewById(R.id.shadow_layout);

            this.mContentView = (ViewGroup)mLayoutInflater.inflate(R.layout.dl_layout_view, null, false);
            this.mPanelTop =(ViewGroup) mContentView.findViewById(R.id.panelTop);
            this.mPanelButton = (ViewGroup) mContentView.findViewById(R.id.panelButton);
            this.mPanelView = (ViewGroup)mContentView.findViewById(R.id.customViewPanel);

            mTitle = (TextView)mContentView.findViewById(R.id.dl_title);
            mMessage = (TextView)mContentView.findViewById(R.id.dl_message);
            mNegative = (Button)mContentView.findViewById(R.id.button1);
            mNeutral = (Button)mContentView.findViewById(R.id.button2);
            mPositive = (Button)mContentView.findViewById(R.id.button3);
        }

        public Builder setGravity(int gravity){
            this.mGravity = gravity;
            return this;
        }

        public Builder setMessage(String message){
            if(mMessage != null && !TextUtils.isEmpty(message)){
                mMessage.setText(message);
                mMessage.setVisibility(View.VISIBLE);
            }
            return this;

        }

        public Builder setTitle(String title){
            if(mTitle != null && !TextUtils.isEmpty(title)){
                mTitle.setText(title);
                mTitle.setVisibility(View.VISIBLE);
                Log.i("Nghia", "HHIHIHI");
            }
            return this;
        }

        public Builder setView(@LayoutRes int id){
            View view = mLayoutInflater.inflate(id, null, false);
            mPanelView.removeAllViews();
            mPanelView.addView(view);
            return this;
        }

        public Builder setView(View v){
            mPanelView.removeAllViews();
            mPanelView.addView(v);
            return this;
        }

        public Builder setPositiveButton(String value, View.OnClickListener listener){
            mPositive.setText(value);
            mPositive.setOnClickListener(listener);
            mPositive.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setNegativeButton(String value, View.OnClickListener listener){
            mNegative.setText(value);
            mNegative.setOnClickListener(listener);
            mNegative.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setNeutralButton(String value, View.OnClickListener listener){
            mNeutral.setText(value);
            mNeutral.setOnClickListener(listener);
            mNeutral.setVisibility(View.VISIBLE);
            return this;
        }


        public Dialog build(){
            return new Dialog(this);
        }
    }
}
