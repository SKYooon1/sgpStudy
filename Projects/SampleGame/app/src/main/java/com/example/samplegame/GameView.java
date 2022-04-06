package com.example.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int BALL_COUNT = 10;
//    Ball ball1, ball2;
    private ArrayList<Ball> balls = new ArrayList<>();
    private Fighter fighter;
    private long previousTimeNanos;
    private int framePerSecond;
    private Paint fpsPaint = new Paint();

    public static GameView view;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        InitView();
    }

    private void InitView() {
        view = this;

//        Resources res = getResources();
//        Bitmap soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
//        Ball.setBitmap(soccerBitmap);

        fighter = new Fighter();
        Random random = new Random();
        for (int i =0; i < BALL_COUNT; i++) {
            int dx = random.nextInt(10) + 5;
            int dy = random.nextInt(10) + 5;
            Ball ball = new Ball(dx, dy);
            balls.add(ball);
        }

        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(100);

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        long now = currentTimeNanos;
        //long now = System.currentTimeMillis();
        int elapsed = (int) (now - previousTimeNanos);
        if (elapsed != 0) {
            framePerSecond = 1_000_000_000 / elapsed;
            Log.v(TAG, "Elapsed: " + elapsed + " FPS: " + framePerSecond);
            previousTimeNanos = now;
            update();
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }

    private void update() {
        for (Ball ball : balls) {
            ball.update();
        }
//        fighter.update();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Ball ball : balls) {
            ball.draw(canvas);
        }
        fighter.draw(canvas);
        canvas.drawText("FPS: " + framePerSecond, framePerSecond * 10,100, fpsPaint);
        //Log.d(TAG, "onDraw()");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
                fighter.setPosition(x, y);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
