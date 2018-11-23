package com.tim.tdlibrary.marquee;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.tim.tdlibrary.R;

public class MarqueeTextView extends View {

    /**
     * 日志 tag
     */
    private static String TAG = MarqueeTextView.class.getSimpleName();

    /**
     * 跑马灯内容
     */
    private String mContent;

    /**
     * 动画
     */
    private ValueAnimator mAnimator;

    /**
     * 画笔
     */
    private Paint mPaint;
    private int mOffset;
    private int mBaseLine;
    private Rect mTargetRect;
    private int mTextWidth = 0;

    /**
     * 文字颜色
     */
    private int mTextColor;

    /**
     * 文字大小
     */
    private float mTextSize;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MarqueeTextView, defStyleAttr, 0);

        mTextSize = typedArray.getDimensionPixelSize(R.styleable.MarqueeTextView_text_size, 16);

        mTextColor = typedArray.getColor(R.styleable.MarqueeTextView_text_color, Color.RED);

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setAntiAlias(true);
        //释放资源
        typedArray.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mContent == null) {
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
            return;
        }


        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int height = fontMetrics.bottom - fontMetrics.top + getPaddingBottom() + getPaddingTop();
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));

        int top = getPaddingTop();
        int bottom = top + fontMetrics.bottom - fontMetrics.top;
        int left = getPaddingLeft() + mOffset;
        int right = left + mTextWidth;
        if (mTargetRect == null) {
            mTargetRect = new Rect();
        }
        mTargetRect.set(left, top, right, bottom);
        mBaseLine = (mTargetRect.bottom + mTargetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mAnimator.cancel();
        if (mTextWidth > getMeasuredWidth()) {
            mAnimator.start();
        }

    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if(visibility == View.VISIBLE){
            start();
        }else{
            cancel();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mContent == null || mTargetRect == null) {
            return;
        }

        mTargetRect.left = getPaddingLeft() + mOffset;
        mTargetRect.right = mTargetRect.left + mTextWidth;
        canvas.drawText(mContent, mTargetRect.left, mBaseLine, mPaint);
    }

    public void setText(String text) {
        mContent = text;
        mTextWidth = (int) (mPaint.measureText(mContent, 0, mContent.length())+1);
        if(null == mAnimator){
            mAnimator = ValueAnimator.ofFloat(0, mTextWidth);
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    mOffset -= 2;
                    if (mOffset < -mTextWidth) {
                        mOffset = getWidth();
                    }
                    invalidate();
                }
            });
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator.setRepeatMode(ValueAnimator.REVERSE);
            //5.为ValueAnimator设置目标对象并开始执行动画
            mAnimator.setTarget(this);
            mAnimator.setDuration((long) (mTextWidth));

        }

    }

    public void start(){
        if(null != mAnimator && mTextWidth > getMeasuredWidth()){
            mAnimator.start();
        }
    }

    public void cancel(){
        if(null != mAnimator && mTextWidth > getMeasuredWidth()){
            mAnimator.cancel();
        }
    }
}