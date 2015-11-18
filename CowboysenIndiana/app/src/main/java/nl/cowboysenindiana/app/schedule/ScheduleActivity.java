package nl.cowboysenindiana.app.schedule;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by Tessa on 10/11/15.
 */
public class ScheduleActivity extends BaseActivity{


//Bundle savedInstanceState
    @Override
    protected void goNext() {
// @// TODO: 13/11/15 Scrolling whole page, keeps crashing... ****

        // Set actionBarTheme
        this.setActionBarTheme();



        this.generateSchedule();
//        this.generateSchedule();
//        this.generateSchedule();
//        this.generateSchedule();
//        this.generateSchedule();
//        this.generateSchedule();



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

    public void generateSchedule(){
        Integer scheduleDayLength = 7;

        // Find existing linearLayout in xml by ID
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linid);

        // Create new GridView
        GridView gridView = new GridView(this);

        // Set config
        // Adapter is set for Data
        gridView.setAdapter(new ScheduleAdapter(this));
        gridView.setNumColumns(scheduleDayLength);
        gridView.setClipChildren(false);
        gridView.setPadding(10, 5, 10, 5);
        //gridView.setBackgroundResource(R.drawable.schedule_border);
//      	gridView.setColumnWidth(200);
//     	gridView.setHorizontalSpacing(10);
//    	gridView.setVerticalSpacing(10);


//        android:layout_width="fill_parent"
//        android:layout_height="wrap_content"

        // Adding the gridview to the RelativeLayout as a child
        linearLayout.addView(gridView);

        // set the RelativeLayout as our content view
        setContentView(linearLayout);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Item Clicked: " + position, Toast.LENGTH_SHORT).show();

            }
        });


    }




    @Override
    protected int getContentView() {
        return R.layout.schedule_main;
    }
}
