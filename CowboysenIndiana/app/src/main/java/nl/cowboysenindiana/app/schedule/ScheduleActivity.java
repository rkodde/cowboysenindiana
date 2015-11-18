package nl.cowboysenindiana.app.schedule;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;


public class ScheduleActivity extends BaseActivity{



    @Override
    protected void goNext() {

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linid);

        // Set actionBarTheme
        this.setActionBarTheme();



        for(int i=1;i<=5;i++) {
            GridView gridView = new GridView(this);
            //GridView gridView = (GridView) LayoutInflater.from(this).inflate(R.layout.schedule_grid_view, null);
            gridView.setAdapter(new ScheduleAdapter(this));
            gridView.setBackgroundResource(R.drawable.schedule_border);
            gridView.setNumColumns(7);
            gridView.setClipChildren(false);
            gridView.setPadding(10, 5, 10, 5);
            gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(gridView);
        }

        //this.generateSchedule();
        //this.generateSchedule();
        //this.generateSchedule();


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

        // Find existing linearLayout in xml by ID


        // Create new GridView
       // GridView gridView = new GridView(this);

        // Set config
        // Adapter is set for Data
        //GridView gridView = (GridView) View.inflate(this, R.layout.schedule_grid_view, null);
        GridView gridView = (GridView) LayoutInflater.from(this).inflate(R.layout.schedule_grid_view, null);
        gridView.setAdapter(new ScheduleAdapter(this));
        gridView.setBackgroundResource(R.drawable.schedule_border);





//        gridView.setNumColumns(scheduleDayLength);
//        gridView.setClipChildren(false);
//        gridView.setPadding(10, 5, 10, 5);
//
//        gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));

//      	gridView.setColumnWidth(200);
//     	gridView.setHorizontalSpacing(10);
//    	gridView.setVerticalSpacing(10);

        // Adding the gridview to the RelativeLayout as a child

//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                showToast("Item Clicked: " + position);
//            }
//        });
    }

    @Override
    protected int getContentView() {
        return R.layout.schedule_main;
    }
}
