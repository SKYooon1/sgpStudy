package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] RES_IDS = new int[] {
        R.mipmap.android_studio,
            R.mipmap.visual_studio,
            R.mipmap.source_tree,
            R.mipmap.unreal_engine,
            R.mipmap.pycharm
    };
    private int pageNumber;
    private TextView pageTextView;
    private ImageView contentImageView;
    private ImageView prevButtonView;
    private ImageView nextButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pageTextView =findViewById(R.id.pageTextView);
        contentImageView = findViewById(R.id.contentImageView);
        prevButtonView = findViewById(R.id.prevImageButton);
        nextButtonView = findViewById(R.id.nextImageButton);

        setPage(1);
    }

    public void onBtnPrev(View view) {
        Log.d(TAG, "Prev Pressed");
        setPage(pageNumber - 1);
    }

    public void onBtnNext(View view) {
        Log.d(TAG, "Prev Pressed");
        setPage(pageNumber + 1);
    }

    private void setPage(int page){
        if (page < 1 ||page > 5)
            return;
        pageNumber = page;
        String text = pageNumber + " / " + 5;
        pageTextView.setText(text);

        int resID = RES_IDS[pageNumber - 1];
        contentImageView.setImageResource(resID);
        setEnabled(page);
    }

    private void setEnabled(int page){
        if (page == 1)
            prevButtonView.setImageResource(R.mipmap.prev_d);
        else
            prevButtonView.setImageResource(R.mipmap.prev);
        if (page == 5)
            nextButtonView.setImageResource(R.mipmap.next_d);
        else
            nextButtonView.setImageResource(R.mipmap.next);
    }
}