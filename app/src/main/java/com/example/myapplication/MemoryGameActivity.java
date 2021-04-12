package com.example.myapplication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryGameActivity extends AppCompatActivity
{
    LottieAnimationView animation_view;
    private Drawable drawable_obj;
    private Drawable[] drawables = new Drawable[6];
    private int currentOpenImagePos = -1;
    private int[] image_data = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
    private int numberOfPaired = 0;
    public final static int[] all_imageview_id = {
            R.id.image_view1, R.id.image_view2, R.id.image_view3, R.id.image_view4,
            R.id.image_view5, R.id.image_view6, R.id.image_view7, R.id.image_view8,
            R.id.image_view9, R.id.image_view10, R.id.image_view11, R.id.image_view12
    };

    private int[] RANDOMLIZATION(int[] array) {
        Random rgen = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        return array;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        drawable_obj = getResources().getDrawable(android.R.drawable.gallery_thumb);

        final String[] all_image_names = this.getIntent().getStringArrayExtra("imageNames");
        for (int i = 0; i < all_image_names.length; i++) {
            byte[] bytes = this.getIntent().getByteArrayExtra(all_image_names[i]);
            drawables[i] = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
        }

        RANDOMLIZATION(image_data);

        for (int i = 0; i < all_imageview_id.length; i++) {
            ImageView img = findViewById(all_imageview_id[i]);
            img.setImageDrawable(drawable_obj);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        for (int i = 0; i < all_imageview_id.length; i++) {
            final ImageView imgView = findViewById(all_imageview_id[i]);
            final int pos = i;
            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickImage(imgView, pos);
                }
            });
        }

        Chronometer c = findViewById(R.id.chronometer);
        c.setText("00:00:00");

        c.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer cArg) {
                long t = SystemClock.elapsedRealtime() - cArg.getBase();
                cArg.setText(DateFormat.format("kk:mm:ss", t));
            }
        });
        TextView textView = findViewById(R.id.matchText);
        textView.setText("0/6 matches");

        animation_view = (LottieAnimationView)findViewById(R.id.animation_item);
        animation_view.setVisibility(View.GONE);
    }

    public void clickImage(final ImageView image_view_obj, final int pos) {
        TextView textView = findViewById(R.id.matchText);
        Chronometer c = findViewById(R.id.chronometer);
        // start stopwatch
        if (!c.isActivated()) {
            c.setActivated(true);
            c.start();
        }

        image_view_obj.setImageDrawable(drawables[image_data[pos]]);

        // MATCH CARD
        if (currentOpenImagePos != -1) {
            final ImageView openImgView = findViewById(all_imageview_id[currentOpenImagePos]);

            if (image_data[pos] == image_data[currentOpenImagePos]) {
                image_view_obj.setClickable(false);
                numberOfPaired++;

                // ANIMATION
                Toast.makeText(this,"MATCH",Toast.LENGTH_SHORT).show();

                ANIMATION_FEATURE();

                String matchText = new StringBuilder().append(numberOfPaired).append("/6 matches").toString();
                textView.setText(matchText);
                if (numberOfPaired == 6)
                {
                    if (c.isActivated()) {
                        c.setActivated(false);
                        c.stop();
                    }
                    Toast.makeText(this, "You won!!! " + ("\ud83d\ude01"),Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(MemoryGameActivity.this, MainActivity.class);
                    //startActivity(intent);
                }

                currentOpenImagePos = -1;
                return;
            }
            else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    return;
                                }

                                image_view_obj.setImageDrawable(drawable_obj);
                                image_view_obj.setClickable(true);
                                openImgView.setImageDrawable(drawable_obj);
                                openImgView.setClickable(true);
                            }
                        });
                    }
                }).start();

                currentOpenImagePos = -1;

                return;
            }
        }
        if (currentOpenImagePos == -1) {
            currentOpenImagePos = pos;
            image_view_obj.setClickable(false);
        }
    }

    public void ANIMATION_FEATURE()
    {

        new CountDownTimer(1000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                animation_view.setVisibility(View.VISIBLE);
                animation_view.playAnimation();
                MediaPlayer correct = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                correct.start();
            }
            @Override
            public void onFinish() {
                animation_view.setVisibility(View.GONE);
            }
        }.start();
    }
}