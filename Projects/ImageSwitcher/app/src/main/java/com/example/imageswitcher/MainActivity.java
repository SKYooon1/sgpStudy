package com.example.imageswitcher;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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
    private ImageButton prevImageButton;
    private ImageButton nextImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        
        pageTextView =findViewById(R.id.pageTextView);
        contentImageView = findViewById(R.id.contentImageView);
        prevImageButton = findViewById(R.id.prevImageButton);
        nextImageButton = findViewById(R.id.nextImageButton);

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
        pageNumber = page;
        String text = pageNumber + " / " + 5;
        pageTextView.setText(text);

        int resID = RES_IDS[pageNumber - 1];
        contentImageView.setImageResource(resID);

        prevImageButton.setEnabled(pageNumber != 1);
        nextImageButton.setEnabled(pageNumber != RES_IDS.length);
    }
}