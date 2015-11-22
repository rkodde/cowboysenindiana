package nl.cowboysenindiana.app.schedule;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import nl.cowboysenindiana.app.database.GroupDBHandler;
import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.model.Group;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;


public class ScheduleActivity extends BaseActivity{

    @Override
    protected void goNext() {

        // Get new DV
        GroupDBHandler db = new GroupDBHandler(this);

        // ADD Group To DB
//      db.addGroup(new Group("Group 3", "#997733"));

        // Get The LinearLayout
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.scheduleLinearLayout);


        // Set actionBarTheme
        this.setActionBarTheme();

        // Get All Groups from DB
        List<Group> groups = db.getAllGroups();

        // Loop all Groups
        for (Group cn : groups) {


            String leadSigned = "Monica, Lowinskie";

            Date today = new Date();

            // Set The Data of DB to Abject Day and Gen.
            ScheduleObjectDay[] ObjectItemData = new ScheduleObjectDay[14];
            for (int i = 0; i < ObjectItemData.length; i++) {

                if(i > 0) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(today);
                    cal.add(Calendar.DATE, 1);

                    today = cal.getTime();
                }
                ObjectItemData[i] = new ScheduleObjectDay(today, leadSigned, (25+i));
            }


            // Log Get all DB groups back
            String log = "Id: " + cn.getGroupID() + " , " + cn.getGroupName() + " , " + cn.getColor();
            Log.d("Name: ", log);

            // Create new Adapter for Schedule_grid_layout
            ScheduleAdapter adapter = new ScheduleAdapter(this, R.layout.schedule_grid_layout, ObjectItemData);

            // Areate a new ListView, set the adapter and item click listener
            GridView gridView = new GridView(this);
            gridView.setAdapter(adapter);
            adapter.setGroupColor(cn.getColor());
            gridView.setBackgroundResource(R.drawable.schedule_border);
            gridView.setNumColumns(7);
            gridView.setClipChildren(false);
            gridView.setPadding(10, 5, 10, 50);
            gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(gridView);

        }

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
