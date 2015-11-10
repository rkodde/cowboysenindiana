package nl.cowboysenindiana.app.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by Tessa on 10/11/15.
 */
public class ScheduleActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_main);

        // @// TODO: 10/11/15 needs to create NEW for other groupID so you can have more dif schedule on 1 page
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ScheduleAdapter(this, "#658736"));
        Integer scheduleShowLength = 5;
        gridview.setNumColumns(scheduleShowLength);

    }

}
