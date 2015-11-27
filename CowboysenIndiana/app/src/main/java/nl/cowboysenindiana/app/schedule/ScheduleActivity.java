package nl.cowboysenindiana.app.schedule;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    public Date currentDate;

    @Override
    protected void goNext() {

        String cActionbarTheme = "Dashboard";

        // Get new DV
        GroupDBHandler db = new GroupDBHandler(this);

//         ADD Group To DB
      db.addGroup(new Group("Group 1", "#997733"));
      db.addGroup(new Group("Group 2", "#857453"));
      db.addGroup(new Group("Group 3", "#998634"));


        // Get All Groups from DB
        List<Group> groups = db.getAllGroups();

        // Get The LinearLayout
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.scheduleLinearLayout);

        // Loop all Groups
        if(groups.size() != 0) {



            for (Group cn : groups) {


                String leadSigned = "Monica, Lowinskie";

                Date today = new Date();

                // Set The Data of DB to Abject Day and Gen.
                ScheduleObjectDay[] ObjectItemData = new ScheduleObjectDay[14];
                for (int i = 0; i < ObjectItemData.length; i++) {

                    if (i > 0) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(today);
                        cal.add(Calendar.DATE, 1);

                        today = cal.getTime();
                    }
                    ObjectItemData[i] = new ScheduleObjectDay(today, leadSigned, (25 + i));
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
                gridView.setPadding(5, 5, 0, 50);
                gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(gridView);


                SimpleDateFormat sdfYear = new SimpleDateFormat("MMMM yyyy");
                String dayDateItemFormat = sdfYear.format(today);

                cActionbarTheme = dayDateItemFormat;
            }
        }else{
            TextView msg = new TextView(this);
            msg.setText("No data available");
            msg.setPadding(10, 10, 10, 10);
            msg.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            linearLayout.addView(msg);
        }

        // Set actionBarTheme
        this.setActionBarTheme(cActionbarTheme);

    }

    public void setActionBarTheme(String cActionbarTheme){


        // Set first char Capital! Bam Bam Bam
        cActionbarTheme = cActionbarTheme.substring(0,1).toUpperCase() + cActionbarTheme.substring(1);

        this.setTitle(cActionbarTheme);

        // Change to setActionBarBG(getMontPic())
        Resources res = getResources();
        Bitmap bMap = BitmapFactory.decodeResource(res, R.drawable.action_christmas);
        BitmapDrawable actionBarBackground = new BitmapDrawable(res, bMap);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(actionBarBackground);
    }

    @Override
    protected int getContentView() {
        return R.layout.schedule_main;
    }

    @Override
    protected int getMenuView() {return R.menu.menu_main;}
}
