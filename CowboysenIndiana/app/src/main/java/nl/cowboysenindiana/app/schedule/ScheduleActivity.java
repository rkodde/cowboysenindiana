package nl.cowboysenindiana.app.schedule;

import android.widget.GridView;
import android.widget.LinearLayout;
import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by Tessa on 10/11/15.
 */
public class ScheduleActivity extends BaseActivity{


//Bundle savedInstanceState
    @Override
    protected void goNext() {

        this.generateSchedule();
        this.generateSchedule();
        this.generateSchedule();

    }

    public void generateSchedule(){
        Integer scheduleDayLength = 7;

        // Find existing linearLayout in xml by ID
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linid);

        // Create new GridView
        GridView gridView = new GridView(this);

        // Set config Gridview
        // Adapter is set for Data
        gridView.setAdapter(new ScheduleAdapter(this));
        gridView.setNumColumns(scheduleDayLength);
        gridView.setClipChildren(false);

        // Adding the gridview to the RelativeLayout as a child
        linearLayout.addView(gridView);

        // set the RelativeLayout as our content view
        setContentView(linearLayout);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.schedule_main;
    }
}
