package com.nghianv.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.RectF;;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ActionMenuView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ListView;

/**
 * Created by NguyenNghia on 3/19/2016.
 */
public class Dialog implements DialogInterface, Window.Callback{

    private final String TAG = Dialog.class.getSimpleName();

    private Context mContext;
    private Window mWindow;
    private ViewGroup mDecorView;
    private ViewGroup mContainer;
    private LayoutInflater mLayoutInflater;
    private ListView mListView;
    private BaseAdapter mAdapter;

    private boolean mShowing;
    private boolean mCanceled;

    private DialogInterface.OnClickListener onClickPositiveListener;
    private DialogInterface.OnClickListener onClickNagetiveListener;
    private DialogInterface.OnClickListener onClickNeuralListener;
    private DialogInterface.OnCancelListener onCancelListener;
    private DialogInterface.OnShowListener onShowListener;
    private DialogInterface.OnDismissListener onDismissListener;
    private DialogInterface.OnKeyListener onKeyListener;

    private Animation mAniFadeIn;
    private Animation mAniFadeOut;

    private FrameLayout rootView;

    private FrameLayout mHeaderContainer;
    private FrameLayout mContentContainer;
    private View mContentView;
    private LinearLayout mFooterContainer;

    private TextView mTitle;
    private TextView mMessage;
    private Button mPositive;
    private Button mNagetive;
    private Button mNeural;


    public Dialog(DialogBuilder builder){

        onKeyListener = builder.getOnKeyListener();
        onClickPositiveListener = builder.getOnClickPositiveListener();
        onClickNagetiveListener = builder.getOnClickNegativeListener();
        onClickNeuralListener = builder.getOnClickNeuralListener();
        onShowListener = builder.getOnShowListener();
        onCancelListener = builder.getOnCancelListener();
        onDismissListener  = builder.getOnDismissListener();

        mContext = builder.getContext();
        mWindow = ((Activity)mContext).getWindow();
        mDecorView = (ViewGroup)mWindow.getDecorView();
        mWindow.setCallback(this);

        mAniFadeIn = builder.getmAnimationIn();
        mAniFadeOut = builder.getmAnimationOut();

        mLayoutInflater = builder.getLayoutInflater();

        mAdapter = builder.getAdapter();
        createDialogView(builder);
    }


    private void createDialogView(DialogBuilder builder) {
        rootView = new FrameLayout(mContext);
        rootView.setBackgroundColor(builder.getBackgroundOverlay());
        rootView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));


        int[] padding = builder.getPadding();

        mContainer = new LinearLayout(mContext);
        mContainer.setLayoutParams(builder.getLayoutParams());
        mContainer.setMinimumHeight(120);
        mContainer.setBackgroundColor(Color.WHITE);
        mContainer.setPadding(padding[0], padding[1], padding[2], padding[3]);
        ((LinearLayout)mContainer).setOrientation(LinearLayout.VERTICAL);

        if (Build.VERSION.SDK_INT < 16) {
            mContainer.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.border_dialog));
        } else {
            mContainer.setBackground(mContext.getResources().getDrawable(R.drawable.border_dialog));
        }



        mTitle = builder.getTitleView();
        mContentView = builder.getContentView();
        mMessage = builder.getMessageView();
        mPositive = builder.getPositiveButton();
        mNeural = builder.getNeuralButton();
        mNagetive = builder.getNagetiveButton();

        if(mTitle != null){
            mHeaderContainer = new FrameLayout(mContext);
            mHeaderContainer.addView(mTitle);
            mContainer.addView(mHeaderContainer);

        }

        if (mMessage != null) {
            mContainer.addView(mMessage);
        }

        if(mMessage == null && mContentView != null) {
            mContentContainer = new FrameLayout(mContext);
            mContentContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mContentContainer.addView(mContentView);
            mContainer.addView(mContentContainer);

        }

        if(mAdapter != null){
            mContentContainer = new FrameLayout(mContext);
            LinearLayout.LayoutParams pl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            pl.weight = 1;
            mContentContainer.setLayoutParams(pl);
            mListView = new ListView(mContext);
            mListView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mListView.setAdapter(mAdapter);
            mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            mContentContainer.addView(mListView);
            mContainer.addView(mContentContainer);
        }

        if(mPositive != null){
            if(mFooterContainer == null){
                mFooterContainer = new LinearLayout(mContext);
                mFooterContainer.setOrientation(LinearLayout.HORIZONTAL);
                mFooterContainer.setGravity(Gravity.END);
            }

            mFooterContainer.addView(mPositive);
            if(onClickPositiveListener != null){
                mPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickPositiveListener.onClick(Dialog.this, BUTTON_POSITIVE);
                    }
                });

            }
        }

        if(mNagetive != null){
            if(mFooterContainer == null){
                mFooterContainer = new LinearLayout(mContext);
                mFooterContainer.setOrientation(LinearLayout.HORIZONTAL);
                mFooterContainer.setGravity(Gravity.END);
            }

            mFooterContainer.addView(mNagetive);
            if(onClickNagetiveListener != null){
                mNagetive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickNagetiveListener.onClick(Dialog.this, BUTTON_NEGATIVE);
                    }
                });

            }
        }

        if(mNeural != null){
            if(mFooterContainer == null){
                mFooterContainer = new LinearLayout(mContext);
                mFooterContainer.setOrientation(LinearLayout.HORIZONTAL);
                mFooterContainer.setGravity(Gravity.END);
            }

            mFooterContainer.addView(mNeural);
            if(onClickNeuralListener != null){
                mNeural.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickNeuralListener.onClick(Dialog.this, BUTTON_NEGATIVE);
                    }
                });

            }
        }

        if(mFooterContainer != null)
            mContainer.addView(mFooterContainer);

        rootView.addView(mContainer);
    }


    private void createListView(){
        mListView = new ListView(mContext);
    }

//    public Dialog(Context context, int defStyle) {
//        TypedArray a = context.getTheme().obtainStyledAttributes(defStyle, R.styleable.MyDialog);
//        try {
//            mShadowColor = a.getColor(R.styleable.MyDialog_shadowColor, Color.parseColor("#66000000"));
//            mBackgroundColor = a.getColor(R.styleable.MyDialog_backgroundColor, Color.WHITE);
//            titleColor = a.getColor(R.styleable.MyDialog_titleColor, Color.parseColor("#17aa8e"));
//        } finally {
//            a.recycle();
//        }
//
//    }

    private boolean isOutBound(float x, float y){
        //get rectf bound of view
        RectF rect = new RectF(mContainer.getLeft(), mContainer.getTop(), mContainer.getRight(), mContainer.getBottom());
        if(rect.contains(x, y))
            return false;
        return true;
    }

    @Override
    public void show() {
        if(!mShowing) {
            try{
                mDecorView.addView(rootView);
                runAnimationIn();
                mShowing = true;
                mCanceled = false;
            } finally {

            }
        }
        if(onShowListener != null)
            onShowListener.onShow(this);

    }
    @Override
    public void dismiss() {
        if(mShowing){
            runAnimationOut();
          //mDecorView.removeView(mContainer);
        }
        if(onDismissListener != null)
            onDismissListener.onDismiss(this);
        mShowing = false;
        mCanceled = true;
    }

    @Override
    public void cancel(){
        if(!mCanceled){
            if(onCancelListener != null)
                onCancelListener.onCancel(this);
            dismiss();
            mCanceled = true;
        }
    }


    public void onBackPressed(){
        if(!mCanceled)
            cancel();
    }

    public View findViewById(@IdRes int id){
       return mContentView.findViewById(id);
    }

    public Context getContext(){
        return this.mContext;
    }

    private boolean isShowing(){
        return this.mShowing;
    }


    private void runAnimationIn(){
        mAniFadeIn = AnimationUtils.loadAnimation(mContext, R.anim.dl_anim_center_fade_in);
        mContainer.startAnimation(mAniFadeIn);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(mAniFadeIn.getDuration());
    }

    private void runAnimationOut(){
        mAniFadeOut = AnimationUtils.loadAnimation(mContext, R.anim.dl_anim_center_fade_out);
        mContainer.startAnimation(mAniFadeOut);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(mAniFadeOut.getDuration());
        mAniFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDecorView.removeView(rootView);
                mAniFadeOut = null;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }



    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(mShowing && event.getKeyCode() == KeyEvent.KEYCODE_BACK ){
            onBackPressed();
            return true;
        }
        if(onKeyListener != null){
            onKeyListener.onKey(event);
        }
        return ((Activity)mContext).dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mShowing){
            rootView.dispatchTouchEvent(event);
            if(event.getAction() == MotionEvent.ACTION_DOWN){
               if(isOutBound(event.getX(), event.getY()))
                    dismiss();
            }
            return true;
        }

        if (mWindow.superDispatchTouchEvent(event))
            return true;
        return false;
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return false;
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        return null;
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return false;
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return false;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return false;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return false;
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
    }

    @Override
    public void onContentChanged() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public void onDetachedFromWindow() {

    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {

    }

    @Override
    public boolean onSearchRequested() {
        return false;
    }

    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return false;
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return null;
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onActionModeFinished(ActionMode mode) {

    }


}
