package com.example.morecontrols;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private Paint paint = new Paint();
    private Bitmap soccerBitmap;
    private Rect soccerSrcRect = new Rect();
    private Rect soccerDstRect = new Rect();
    private Paint leftCirclePaint = new Paint();
    private Paint rightCirclPaint = new Paint();
    private Paint textPaint = new Paint();
    private Rect textExtentRect = new Rect();

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint.setColor(0xFFCCCCCC);
        leftCirclePaint.setColor(Color.BLUE);
        rightCirclPaint.setColor(Color.RED);
        rightCirclPaint.setStyle(Paint.Style.STROKE);
        rightCirclPaint.setStrokeWidth(10);
        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(50);
        Resources res = getResources();
        soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        soccerSrcRect.set(0,0,soccerBitmap.getWidth(), soccerBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //Paint paint = new Paint();
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = width - paddingLeft - paddingRight;
        int contentHeight = height - paddingTop - paddingBottom;

        int size = contentWidth < contentHeight ? contentWidth : contentHeight;
        int ballRadius = size / 10;

        int centerX = paddingLeft + contentWidth / 2;
        int centerY = paddingTop + contentHeight / 2;

        canvas.drawRoundRect(paddingLeft, paddingTop, paddingLeft+ contentWidth, paddingTop + contentHeight, 30, 40, paint);

        soccerDstRect.set(centerX - ballRadius, centerY - ballRadius, centerX + ballRadius, centerY +ballRadius);
        canvas.drawBitmap(soccerBitmap, soccerSrcRect, soccerDstRect, null);

        int leftCenterX = paddingLeft + contentWidth / 4;
        int leftCenterY = paddingTop + contentHeight / 4;
        int circleRadius = size / 16;

        canvas.drawCircle(leftCenterX, leftCenterY, circleRadius, leftCirclePaint);

        int rightCenterX = centerX + contentWidth / 4;
        int rightCenterY = paddingTop + contentHeight / 4;

        canvas.drawCircle(rightCenterX, rightCenterY, circleRadius, rightCirclPaint);

        String text = "Soccer";
        textPaint.getTextBounds(text, 0, text.length(), textExtentRect);
        int textX = centerX - textExtentRect.width() / 2;
        int textY = centerY + contentHeight / 4;
        canvas.drawText(text, textX, textY, textPaint);
    }
}
