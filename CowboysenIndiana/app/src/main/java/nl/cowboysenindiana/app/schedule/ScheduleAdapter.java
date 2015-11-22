package nl.cowboysenindiana.app.schedule;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

import static java.lang.Integer.*;

/**
 * Created by Tessa on 22/11/15.
 */

public class ScheduleAdapter extends ArrayAdapter<ScheduleObjectDay> {

private String groupColor = "#18A608";
private String themeColor = "#658736";

    Context mContext;
    int layoutResourceId;
    ScheduleObjectDay data[] = null;

    public ScheduleAdapter(Context mContext, int layoutResourceId, ScheduleObjectDay[] data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    public void setGroupColor(String groupColor){
        this.groupColor = groupColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        ScheduleObjectDay objectItem = data[position];

        // get the TextView

        // Day Date
        TextView txtDayDate = (TextView) convertView.findViewById(R.id.txtDayDate);
        txtDayDate.setText(String.valueOf(objectItem.itemDayDate));

        // Check if today Date, draw Circle
        if(txtDayDate.getText().equals(String.valueOf("13"))){
            txtDayDate.setPadding(11, 2, 0, 0);
            Integer a = Integer.valueOf((String) txtDayDate.getText());
            if(a > 10) { txtDayDate.setPadding(2, 0, 0, 0); }
            txtDayDate.setBackgroundResource(R.drawable.schedule_day_border);
            txtDayDate.setTextColor(Color.parseColor("#FFFFFF"));
        }

        Log.d("test", String.valueOf(txtDayDate.getText()));
        //textViewItem.setTag(objectItem.itemId);

        // Teachers Signed Header
        TextView txtLeadSignedHead = (TextView) convertView.findViewById(R.id.txtEntityLeadHead);
        txtLeadSignedHead.setBackgroundResource(R.drawable.schedule_border);
        txtLeadSignedHead.setBackgroundColor(Color.parseColor(groupColor));
        txtLeadSignedHead.setText("Teacher");

        // Teachers Signed
        TextView txtLeadSigned = (TextView) convertView.findViewById(R.id.txtEntityLeadSigned);
        txtLeadSigned.setText(objectItem.itemLeadSigned);

        // Kids Scheduled Head
        TextView itemEntityScheduledHead = (TextView) convertView.findViewById(R.id.txtEntityScheduledHead);
        itemEntityScheduledHead.setBackgroundResource(R.drawable.schedule_border);
        itemEntityScheduledHead.setBackgroundColor(Color.parseColor(groupColor));
        itemEntityScheduledHead.setText("Kids");

        // Kids Scheduled
        TextView itemEntityScheduled = (TextView) convertView.findViewById(R.id.txtEntityScheduled);
        itemEntityScheduled.setText(String.valueOf(objectItem.itemEntityScheduled));

        return convertView;

    }
}