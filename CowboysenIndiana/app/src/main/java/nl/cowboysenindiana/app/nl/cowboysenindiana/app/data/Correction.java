package nl.cowboysenindiana.app.nl.cowboysenindiana.app.data;

import android.app.Activity;
import android.os.Bundle;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

public class Correction extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction);

        // create RangeSeekBar as Date range between 1950-12-01 and now
        Date minDate = null;
        try {
            minDate = new SimpleDateFormat("yyyy-MM-dd").parse("1950-12-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date maxDate = new Date();
        RangeSeekBar<Long> seekBar = new RangeSeekBar<Long>(minDate.getTime(), maxDate.getTime(), getApplicationContext());
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Long>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Long minValue, Long maxValue) {
                // handle changed range values
                Log.i("FYI", "User selected new date range: MIN=" + new Date(minValue) + ", MAX=" + new Date(maxValue));
            }
        });

        // add RangeSeekBar to pre-defined layout
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_RangeSeekBar);
        layout.addView(seekBar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_correction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
