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

    protected ScheduleAdapter adapter;

    @Override
    protected void goNext() {

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linid);

        // Set actionBarTheme
        this.setActionBarTheme();


        // add items to gridView programmatically by changing adapter dataset:
        // -------------------------------------------------------

        // make adapter for gridView
        adapter = new ScheduleAdapter(this);

        // make new String[] to hold more data for dayDate
        int oldLength = adapter.dayDate.length;
        String[] dayDate_new = new String[oldLength+21];
        // add old data to new String[]
        for (int i = 0; i < oldLength; i++){
            dayDate_new[i] = adapter.dayDate[i];
        }
        // add new data to new String[]
        int p = 4;
        for (int i = dayDate_new.length-1; i >= oldLength; i--){
            dayDate_new[i] = String.valueOf(p);
            p--;
            if (p == 0){
                p = 31;
            }
        }
        // set new String[] as dayDate of adapater
        adapter.dayDate = dayDate_new;

        // do the same for entityLeadSigned and entityScheduled so we don't get array out of bounds error on those
        oldLength = adapter.entityLeadSigned.length;
        String[] entityLeadSigned_new = new String[oldLength+21];
        // add old data to new String[]
        for (int i = 0; i < oldLength; i++){
            entityLeadSigned_new[i] = adapter.entityLeadSigned[i];
        }
        // add new data to new String[]
        for (int i = dayDate_new.length-1; i >= oldLength; i--){
            entityLeadSigned_new[i] = "Rahun";
        }
        // set new String[] as entityLeadSigned of adapter
        adapter.entityLeadSigned = entityLeadSigned_new;

        oldLength = adapter.entityScheduled.length;
        String[] entityScheduled_new = new String[oldLength+21];
        // add old data to new String[]
        for (int i = 0; i < oldLength; i++){
            entityScheduled_new[i] = adapter.entityScheduled[i];
        }
        // add new data to new String[]
        for (int i = dayDate_new.length-1; i >= oldLength; i--){
            entityScheduled_new[i] = "25 / 30";
        }
        // set new String[] as entityScheduled of adapter
        adapter.entityScheduled = entityScheduled_new;


        // notify adapter dataset has changed so it updates gridView
        adapter.notifyDataSetChanged();

        // ---------------------------------------------------------

        // set up gridView
        GridView gridView = new GridView(this);
        //GridView gridView = (GridView) LayoutInflater.from(this).inflate(R.layout.schedule_grid_view, null);
        gridView.setAdapter(adapter);
        gridView.setBackgroundResource(R.drawable.schedule_border);
        gridView.setNumColumns(7);
        gridView.setClipChildren(false);
        gridView.setPadding(10, 5, 10, 5);
        gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(gridView);


//        for(int i=1;i<=5;i++) {
//            GridView gridView = new GridView(this);
//            //GridView gridView = (GridView) LayoutInflater.from(this).inflate(R.layout.schedule_grid_view, null);
//            gridView.setAdapter(new ScheduleAdapter(this));
//            gridView.setBackgroundResource(R.drawable.schedule_border);
//            gridView.setNumColumns(7);
//            gridView.setClipChildren(false);
//            gridView.setPadding(10, 5, 10, 5);
//            gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
//            linearLayout.addView(gridView);
//        }

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
