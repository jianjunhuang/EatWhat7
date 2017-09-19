package com.jinjunhuang.eatwhat.weidge;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/18.
 */

public class AnimCircleView extends View {

    private static final String TAG = "AnimCircleView";

    private ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "angle", 0, 360);
    private ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(this, "radius", 0, 50);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path = new Path();
    private float angle;
    private float radius;

    public AnimCircleView(Context context) {
        super(context, null);
    }

    public AnimCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public AnimCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressWarnings("unused")
    public void setAngle(float angle) {
        this.angle = angle;
        invalidate();
    }

    @SuppressWarnings("unused")
    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        objectAnimator.end();
        scaleAnim.end();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        objectAnimator.start();
        scaleAnim.start();
    }

    {
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(80);
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(150000);

        scaleAnim.setRepeatMode(ObjectAnimator.REVERSE);
        scaleAnim.setRepeatCount(ObjectAnimator.INFINITE);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setDuration(5000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int pathR = 10;
        int circleR = 300;
        path.addCircle(centerX, centerY, pathR, Path.Direction.CW);

        paint.setColor(Color.RED);
        paint.setAlpha(80);
        float x = (float) (centerX + pathR * Math.cos(angle));
        float y = (float) (centerY + pathR * Math.sin(angle));
        canvas.drawCircle(x, y, circleR + radius, paint);

        paint.setColor(Color.RED);
        paint.setAlpha(50);
        float x1 = (float) (centerX + pathR + 20 * Math.cos(angle + 60));
        float y1 = (float) (centerY + pathR + 20 * Math.sin(angle + 60));
        canvas.drawCircle(x1, y1, circleR + radius + 10, paint);

        paint.setColor(Color.RED);
        paint.setAlpha(30);
        float x2 = (float) (centerX + pathR + 40 * Math.cos(angle + 120));
        float y2 = (float) (centerY + pathR + 40 * Math.sin(angle + 120));
        canvas.drawCircle(x2, y2, circleR + radius + 10, paint);
    }
}

