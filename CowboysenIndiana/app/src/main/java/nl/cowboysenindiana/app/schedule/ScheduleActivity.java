package nl.cowboysenindiana.app.schedule;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;


public class ScheduleActivity extends BaseActivity{

    @Override
    protected void goNext() {

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.scheduleLinearLayout);

        // Set actionBarTheme
        this.setActionBarTheme();



// add your items, this can be done programatically
        // your items can be from a database

        SimpleDateFormat sdf = new SimpleDateFormat("dd");

        String date = sdf.format(new Date());

        this.setActionBarTheme();



        String leadSigned = "dd";

        ScheduleObjectDay[] ObjectItemData = new ScheduleObjectDay[15];

        for (int i = 0; i < ObjectItemData.length; i++) {
            ObjectItemData[i] = new ScheduleObjectDay(date, leadSigned, 30);
        }

        // our adapter instance
        ScheduleAdapter adapter = new ScheduleAdapter(this, R.layout.schedule_grid_layout, ObjectItemData);

        // create a new ListView, set the adapter and item click listener
        GridView gridView = new GridView(this);
        gridView.setAdapter(adapter);
        adapter.setGroupColor("#756483");
        gridView.setBackgroundResource(R.drawable.schedule_border);
        gridView.setNumColumns(7);
        gridView.setClipChildren(false);
        gridView.setPadding(10, 5, 10, 5);
        gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(gridView);

        // our adapter instance
        ScheduleAdapter adapter2 = new ScheduleAdapter(this, R.layout.schedule_grid_layout, ObjectItemData);

        // create a new ListView, set the adapter and item click listener
        GridView gridView2 = new GridView(this);
        gridView2.setAdapter(adapter2);
        adapter2.setGroupColor("#546564");
        gridView2.setBackgroundResource(R.drawable.schedule_border);
        gridView2.setNumColumns(7);
        gridView2.setClipChildren(false);
        gridView2.setPadding(10, 5, 10, 5);
        gridView2.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(gridView2);



        // our adapter instance
        ScheduleAdapter adapter3 = new ScheduleAdapter(this, R.layout.schedule_grid_layout, ObjectItemData);

        // create a new ListView, set the adapter and item click listener
        GridView gridView3 = new GridView(this);
        gridView3.setAdapter(adapter3);
        adapter3.setGroupColor("#997733");
        gridView3.setBackgroundResource(R.drawable.schedule_border);
        gridView3.setNumColumns(7);
        gridView3.setClipChildren(false);
        gridView3.setPadding(10, 5, 10, 5);
        gridView3.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(gridView3);

    }

    public void setActionBarTheme(){
        // Change to getMonth();
        this.setTitle("December 2016");

        // Change to setActionBarBG(getMontPic())
        Resources res = getResources();
        Bitmap bMap = BitmapFactory.decodeResource(res, R.drawable.action_christmas);
        BitmapDrawable actionBarBackground = new BitmapDrawable(res, bMap);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(actionBarBackground);
    }

    public void generateSchedule(LinearLayout linearLayout){
        Integer scheduleDayLength = 7;

    }

    @Override
    protected int getContentView() {
        return R.layout.schedule_main;
    }
}
