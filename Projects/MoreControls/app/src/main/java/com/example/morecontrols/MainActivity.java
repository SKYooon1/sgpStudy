package com.example.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = findViewById(R.id.checkbox);
    }

    public void onCheckbox(View view) {
        CheckBox cb = (CheckBox) view;
        Log.d(TAG, "Checkbox stat: " + cb.isChecked());
    }

    public void onBtnDoIt(View view) {
        Log.d(TAG, "onBtnDoIt, check=" + checkBox.isChecked());
    }
}