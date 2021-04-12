package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MyAsynTask.ICallback
{
    MyAsynTask myasyntask_obj;
    ImageView image_view_ele;
    int pos;
    public final Map<Integer, byte[]> hit_images = new HashMap<>();
    protected String input_url =null;
    private int count;
    final String [] url=new String[]{"/1.jpg","/2.jpg","/3.jpg","/4.jpg","/5.jpg",
            "/6.jpg","/7.jpg","/8.jpg","/9.jpg","/10.jpg","/11.jpg","/12.jpg",
            "/13.jpg","/14.jpg","/15.jpg","/16.jpg","/17.jpg","/18.jpg","/19.jpg","/20.jpg"};
    final static int[] drawable=new int[]{
            R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5
            ,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9,R.id.imageView10
            ,R.id.imageView11,R.id.imageView12,R.id.imageView13
            ,R.id.imageView14,R.id.imageView15,R.id.imageView16
            ,R.id.imageView17,R.id.imageView18,R.id.imageView19,R.id.imageView20};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button load_button = findViewById(R.id.load_button);

        load_button.setOnClickListener(this);

        image_view_ele = (ImageView) findViewById(R.id.imageView1);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView2);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView3);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView4);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView5);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView6);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView7);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView8);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView9);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView10);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView11);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView12);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView13);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView14);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView15);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView16);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView17);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView18);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView19);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView20);
        image_view_ele.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {

        switch(v.getId()){
            case R.id.load_button:
                EditText search=findViewById(R.id.search_input_box);
                String target_site = search.getText().toString();

                if(target_site.equals(null) || target_site.equals(""))
                {
                    Toast.makeText(MainActivity.this,"EMPTY SEARCH VALUES",Toast.LENGTH_SHORT).show();
                    break;
                }
                else
                {
                    if(myasyntask_obj!= null && !myasyntask_obj.isCancelled())
                    {
                        myasyntask_obj.cancel(true);
                    }
                    pos = 0;
                    myasyntask_obj = new MyAsynTask(MainActivity.this);
                    myasyntask_obj.execute(target_site);
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    break;
                }


            case R.id.imageView1:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW ONE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView2:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW TWO",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView3:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW THREE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView4:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW FOUR",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView5:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW FIVE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView6:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW SIX",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView7:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW SEVEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView8:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW EIGHT",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView9:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW NINE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView10:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW TEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView11:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW ELEVEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView12:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW TWELVE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView13:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW THIRTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView14:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW FOURTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView15:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW FIFTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView16:
               // Toast.makeText(MainActivity.this,"IMAGE VIEW SIXTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView17:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW SEVENTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView18:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW EIGHTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView19:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW NINETEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
            case R.id.imageView20:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW TWENTY",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES((ImageView)v);
                break;
        }
    }

public void SELECT_IMAGES(ImageView img_view)
{
    if(img_view.isSelected()) {
        img_view.setSelected(false);
        img_view.setColorFilter(Color.argb(0, 0, 0, 0));

        REMOVE_IMAGE_FROM_LIST(img_view);
    }
    else {
        img_view.setSelected(true);
        img_view.setColorFilter(Color.argb(80, 0, 128, 255));
        ADD_IMAGE_TO_LIST(img_view);
    }
    if(hit_images.size()==6)
    {
        MOVE_TO_GAME();
    }
}
public void MOVE_TO_GAME()
{
    // add image data to intent
    Intent intent = new Intent(MainActivity.this, MemoryGameActivity.class);
    String[] select_image_names = new String[6];

    int i = 0;

    for(Map.Entry<Integer, byte[]> data : hit_images.entrySet())
    {
        String constant_image_name = new StringBuilder().append("image").append(data.getKey()).toString();
        select_image_names[i] = constant_image_name;
        intent.putExtra(constant_image_name, data.getValue());
        i++;
    }
    intent.putExtra("imageNames", select_image_names);
    startActivity(intent);
}

public void REMOVE_IMAGE_FROM_LIST(ImageView image_view)
{
    hit_images.remove(image_view.getId());
}

public void ADD_IMAGE_TO_LIST(ImageView image_view)
{
    Bitmap bitmap_obj = ((BitmapDrawable) image_view.getDrawable()).getBitmap();
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap_obj.compress(Bitmap.CompressFormat.JPEG, 70, stream);
    hit_images.put(image_view.getId(), stream.toByteArray());
}

    @Override
    public void DownloadFinished(final Bitmap bitmap) {
        runOnUiThread(
                new Runnable() {
            @Override
            public void run() {

                // clear existing picture
                if(pos == 0) {
                    for(int i = 0; i < drawable.length; i++) {
                        ImageView img = findViewById(drawable[i]);
                        img.setImageResource(android.R.color.transparent);
                    }
                }

                // update progress bar
                ProgressBar progressBar = findViewById(R.id.progressBar);
                TextView progressLabel = findViewById(R.id.progressText);

                int curProgress = (pos+1) * 100 / drawable.length;

                String label = new StringBuilder().append("Downloading ").append(String.valueOf(pos + 1)).append(" of ").append(String.valueOf(drawable.length)).append(" images...").toString();

                progressBar.setProgress(curProgress);
                progressLabel.setText(label);

                progressBar.setVisibility(View.VISIBLE);
                progressLabel.setVisibility(View.VISIBLE);

                // show picture
                ImageView img = findViewById(drawable[pos]);
                img.setImageBitmap(bitmap);
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                img.setClickable(true);

                pos++;

                // at the end, close progress bae
                if (curProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                    progressLabel.setVisibility(View.GONE);
                    return;
                }
            }
        });
    }
}
