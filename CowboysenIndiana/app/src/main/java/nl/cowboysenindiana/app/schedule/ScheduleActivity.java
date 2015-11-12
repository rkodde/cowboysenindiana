package nl.cowboysenindiana.app.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
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
//    public void goNext(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.schedule_main);

        // @// TODO: 10/11/15 needs to create NEW for other groupID so you can have more dif schedule on 1 page
        GridView gridview = (GridView) findViewById(R.id.gridview);

        gridview.setAdapter(new ScheduleAdapter(this, "#658736"));
        Integer scheduleShowLength = 7;

        gridview.setNumColumns(scheduleShowLength);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Item Clicked: " + position, Toast.LENGTH_SHORT).show();

            }
        });




    }



    @Override
    protected int getLayoutResourceId() {
        return R.layout.schedule_main;
    }
}
