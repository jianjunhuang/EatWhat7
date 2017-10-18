package com.jinjunhuang.loadingcirclebtn;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;

import static android.content.ContentValues.TAG;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public class LoadingCircleBtn extends View {

    private Paint defaultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint loadingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint successPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint failedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private ObjectAnimator defaultToLoadingAnim;
    private ObjectAnimator loadingAnim = ObjectAnimator.ofFloat(this, "sweepAngle", 0, 360);
    private float sweepAngle;
    private ObjectAnimator loadingToFinishAnim;
    private float scaleSize;

    private static final int DEFAULT_COLOR = Color.parseColor("#58abe3");
    private static final int DEFAULT_SUCCESS_COLOR = Color.parseColor("#72c3a8");
    private static final int DEFAULT_FAILED_COLOR = Color.parseColor("#F12C3C");
    private static final float DEFAULT_RADIUS = 80;
    private static final float DEFAULT_PROGRESS_WIDTH = 15;
    private static final float DEFAULT_PROGRESS_GAP_ANGLE = 30;
    //按钮半径
    private float mRadius = DEFAULT_RADIUS;
    //进度条宽度
    private float mProgressWidth = DEFAULT_PROGRESS_WIDTH;
    //进度条空隙
    private float mProgressGapAngle = DEFAULT_PROGRESS_GAP_ANGLE;
    private int mDefaultDrawable = R.drawable.ic_save_white_24dp;
    private int mSuccessDrawable = R.drawable.ic_done_white_24dp;
    private int mFailedDrawable = R.drawable.ic_clear_white_24dp;
    //图片
    private Bitmap mDefaultBmp = null;
    private Bitmap mSuccessBmp = null;
    private Bitmap mFailedBmp = null;
    //按钮颜色
    private int mColor = DEFAULT_COLOR;
    //加载成功后按钮颜色
    private int mLoadingSuccessColor = DEFAULT_SUCCESS_COLOR;
    //加载失败后按钮颜色
    private int mLoadingFailedColor = DEFAULT_FAILED_COLOR;
    //加载条的颜色
    private int mLoadingProgressColor = DEFAULT_COLOR;
    private int mStatus = STATUS_DEFAULT;

    private RectF arcRectF;

    public static final int STATUS_DEFAULT = 0;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_LOAD_SUCCESS = 2;
    public static final int STATUS_LOAD_FAILED = 3;

    private static final int DEFAULT_DRAWABLE = R.drawable.ic_save_white_24dp;
    private static final int SUCCESS_DRAWABLE = R.drawable.ic_done_white_24dp;
    private static final int FAILED_DRAWABLE = R.drawable.ic_clear_white_24dp;

    public LoadingCircleBtn(Context context) {
        this(context, null);
    }

    public LoadingCircleBtn(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingCircleBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingCircleBtn);
        if (typedArray != null) {
            mRadius = typedArray.getDimension(R.styleable.LoadingCircleBtn_radius, DEFAULT_RADIUS);
            mProgressWidth = typedArray.getDimension(R.styleable.LoadingCircleBtn_loadingProgressWidth, mRadius * 3 / 16);
            mProgressGapAngle = typedArray.getFloat(R.styleable.LoadingCircleBtn_loadingProgressGapAngle, DEFAULT_PROGRESS_GAP_ANGLE);
            mDefaultDrawable = typedArray.getResourceId(R.styleable.LoadingCircleBtn_defaultDrawable, DEFAULT_DRAWABLE);
            mSuccessDrawable = typedArray.getResourceId(R.styleable.LoadingCircleBtn_successDrawable, SUCCESS_DRAWABLE);
            mFailedDrawable = typedArray.getResourceId(R.styleable.LoadingCircleBtn_failedDrawable, FAILED_DRAWABLE);

            mLoadingSuccessColor = typedArray.getColor(R.styleable.LoadingCircleBtn_loadingSuccessColor, DEFAULT_SUCCESS_COLOR);
            mLoadingFailedColor = typedArray.getColor(R.styleable.LoadingCircleBtn_loadingFailedColor, DEFAULT_FAILED_COLOR);

            mColor = typedArray.getColor(R.styleable.LoadingCircleBtn_backgroundColor, DEFAULT_COLOR);

            mLoadingProgressColor = typedArray.getColor(R.styleable.LoadingCircleBtn_loadingProgressColor, DEFAULT_COLOR);
            typedArray.recycle();
        }
        initPaint();
        initAnim();
    }

    private void initPaint() {
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        defaultPaint.setColor(mColor);
        mDefaultBmp = BitmapFactory.decodeResource(getResources(), mDefaultDrawable);
        defaultPaint.setShadowLayer(5, 0, 5, Color.LTGRAY);

        loadingPaint.setColor(mLoadingProgressColor);
        loadingPaint.setStyle(Paint.Style.STROKE);
        loadingPaint.setStrokeWidth(mProgressWidth);
        loadingPaint.setStrokeCap(Paint.Cap.ROUND);

        successPaint.setColor(mLoadingSuccessColor);
        mSuccessBmp = BitmapFactory.decodeResource(getResources(), mSuccessDrawable);

        failedPaint.setColor(mLoadingFailedColor);
        mFailedBmp = BitmapFactory.decodeResource(getResources(), mFailedDrawable);
    }

    private void initAnim() {
        loadingAnim.setDuration(1800);
        loadingAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        loadingAnim.setRepeatCount(ValueAnimator.INFINITE);
        loadingAnim.setRepeatMode(ValueAnimator.RESTART);

        loadingToFinishAnim = ObjectAnimator.ofFloat(this, "scaleSize", 0, mRadius);
        loadingToFinishAnim.setDuration(500);
        loadingToFinishAnim.setInterpolator(new AnticipateOvershootInterpolator());

        defaultToLoadingAnim = ObjectAnimator.ofFloat(this, "scaleSize", mRadius, 0);
        defaultToLoadingAnim.setDuration(500);
        defaultToLoadingAnim.setInterpolator(new AnticipateOvershootInterpolator());

    }

    public void setStatus(int status) {
        this.mStatus = status;
        invalidate();
    }

    public int getStatus() {
        return mStatus;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mStatus == STATUS_LOADING) {
            return true;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(setMeasure(widthMeasureSpec), setMeasure(heightMeasureSpec));
    }

    private int setMeasure(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size;
        if (mode == MeasureSpec.EXACTLY) {
            size = MeasureSpec.getSize(measureSpec);
        } else {
            size = (int) (mRadius * 2 + mRadius / 3);
        }
        return size;
    }

    private boolean isLoading = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        //默认状态
        if (mStatus == STATUS_DEFAULT) {
            canvas.drawCircle(centerX, centerY, mRadius, defaultPaint);
            canvas.drawBitmap(mDefaultBmp, centerX - mDefaultBmp.getWidth() / 2, centerY - mDefaultBmp.getHeight() / 2, defaultPaint);
            //加载状态
        } else if (mStatus == STATUS_LOADING) {

            if (!defaultToLoadingAnim.isRunning() && !isLoading) {
                defaultToLoadingAnim.start();
                isLoading = true;
            }

            if (defaultToLoadingAnim.isRunning()) {
                canvas.drawCircle(centerX, centerY, scaleSize, defaultPaint);
            }

            if (!defaultToLoadingAnim.isRunning()) {
                if (!loadingAnim.isRunning()) {
                    loadingAnim.start();
                }

                if (arcRectF == null) {
                    arcRectF = new RectF(mProgressWidth, mProgressWidth, getWidth() - mProgressWidth, getHeight() - mProgressWidth);
                }
                canvas.drawArc(arcRectF, sweepAngle, getSweepAngleEnd(sweepAngle), false, loadingPaint);
            }
            //加载成功
        } else if (mStatus == STATUS_LOAD_SUCCESS) {

            if (!loadingToFinishAnim.isRunning() && isLoading) {
                loadingToFinishAnim.start();
                isLoading = false;
            }

            if (loadingToFinishAnim.isRunning()) {
                canvas.drawCircle(centerX, centerY, scaleSize, successPaint);
            }

            if (!loadingToFinishAnim.isRunning()) {
                if (loadingAnim.isRunning()) {
                    loadingAnim.end();
                }
                canvas.drawCircle(centerX, centerY, mRadius, successPaint);
                canvas.drawBitmap(mSuccessBmp, centerX - mSuccessBmp.getWidth() / 2, centerY - mSuccessBmp.getHeight() / 2, successPaint);
            }
            //加载失败
        } else if (mStatus == STATUS_LOAD_FAILED) {
            if (!loadingToFinishAnim.isRunning() && isLoading) {
                loadingToFinishAnim.start();
                isLoading = false;
            }

            if (loadingToFinishAnim.isRunning()) {
                canvas.drawCircle(centerX, centerY, scaleSize, failedPaint);
            }

            if (!loadingToFinishAnim.isRunning()) {
                if (loadingAnim.isRunning()) {
                    loadingAnim.end();
                }
                canvas.drawCircle(centerX, centerY, mRadius, failedPaint);
                canvas.drawBitmap(mFailedBmp, centerX - mFailedBmp.getWidth() / 2, centerY - mFailedBmp.getHeight() / 2, failedPaint);
            }
        }
    }

//    private float dpToPx(float dp) {
//        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
//        return dp * metrics.density;
//    }

    private float getSweepAngleEnd(float sweepAngle) {
        float end = sweepAngle + 360 - mProgressGapAngle;
        if (end < 360) {
            return end;
        }
        return end - 360;
    }

    public float getSweepAngle() {
        return sweepAngle;
    }

    @SuppressWarnings("unused")
    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
        invalidate();
    }

    public float getScaleSize() {
        return scaleSize;
    }

    @SuppressWarnings("unused")
    public void setScaleSize(float scaleSize) {
        this.scaleSize = scaleSize;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mFailedBmp.recycle();
        mDefaultBmp.recycle();
        mSuccessBmp.recycle();
    }
}
