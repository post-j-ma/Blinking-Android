package com.postjma.postjma.BlinkingAndroid;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import static android.R.drawable.sym_def_app_icon;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BlinkingAndroidActivity extends AppCompatActivity {
    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mShowHideHandler = new Handler();
    private View mContentView;
    private final Runnable mShowHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            toggleAndroid();
            mShowHideHandler.postDelayed(mShowHidePart2Runnable, UI_ANIMATION_DELAY);
        }
    };
    private View mControlsView;

    private void toggleAndroid()
    {
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        Drawable xDraw = getResources().getDrawable(sym_def_app_icon);
        if (mCount > 0) {
            if (mOldDraw == null) mOldDraw = iv.getDrawable();
            iv.setImageDrawable(xDraw);
        }
        else
        {
            if (mOldDraw != null)
                iv.setImageDrawable(mOldDraw);
            else
                iv.setImageDrawable(null);
        }
        mCount = mCount + 1;
        if (mCount > 1) {
            mCount = 0;
        }
    }

    private Drawable mOldDraw = null;
    private int mCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleAndroid();
            }
        });

        mShowHideHandler.postDelayed(mShowHidePart2Runnable, UI_ANIMATION_DELAY);
    }
}
