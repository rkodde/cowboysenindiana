package nl.cowboysenindiana.app.schedule;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by Tessa on 10/11/15.
 */

public class ScheduleAdapter extends BaseAdapter {
    Context mContext;

    public void setShowDayName(boolean showDayName) {
        this.showDayName = showDayName;
    }

    boolean showDayName = true;



    // Db Values6
    private String groupColor = "#18A608";
    private String themeColor = "#658736";

    private String [] dayName = {"Ma","Di","Wo","Do","Vr","Za","Zo",
            "","","","","","","",
            "","","","","","","",
            "","","","","","","",
            "","","","","","","",};

    protected String[] dayDate = {"30","1","2","3","4","5","6",
            "7","8","9","10","11","12","13",
            "14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27",
            "28","29","31","1","2","3","14"};

    private String entityLeadHead = "Teacher";
    protected String[] entityLeadSigned ={"Rohit","Rahul","Ravi","Amit","Arun","","",
            "Rohit","Rahul","Ravi","Amit","Arun","","",
            "Rohit","Rahul","Ravi","Amit","Arun","","",
            "Rohit","Rahul","Ravi","Amit","Arun","","",
            "Rohit","Rahul","Ravi","Amit","Arun","",""};

    private String entityScheduledHead = "Kids";
    protected String [] entityScheduled ={"25 / 30","25 / 30","25 / 30","25 / 30","25 / 30","","",
            "25 / 30","25 / 30","25 / 30","25 / 30","25 / 30","","",
            "25 / 30","25 / 30","25 / 30","25 / 30","25 / 30","","",
            "25 / 30","25 / 30","25 / 30","25 / 30","25 / 30","","",
            "25 / 30","25 / 30","25 / 30","25 / 30","25 / 30","",""};



    private LayoutInflater mInflater;

    public void setGroupColor(String groupColor){
        this.groupColor = groupColor;
    }

    public ScheduleAdapter(Context c)
    {
        mContext = c;
        mInflater = LayoutInflater.from(c);
    }

    public int getCount() {
    //    return dayName.length;
        return dayDate.length;
    }

    public Object getItem(int position)
    {
        return position;
    }
    public long getItemId(int position)
    {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

    ViewHolder holder = new ViewHolder();



    if ( convertView == null )

    {
        convertView = mInflater.inflate(R.layout.schedule_grid_layout,
                parent, false);
        //holder = new ViewHolder();

        // Gif the TextView custom settings.
        // Day of the week
        //holder.txtDayName = (TextView) convertView.findViewById(R.id.txtDayName);
        //holder.txtDayName.setPadding(10, 10, 10, 10);

        // Date of the day
        holder.txtDayDate = (TextView) convertView.findViewById(R.id.txtDayDate);
        holder.txtDayDate.setPadding(10, 10, 10, 10);


        // Entity Lead Header
        holder.txtEntityLeadHead = (TextView) convertView.findViewById(R.id.txtEntityLeadHead);
        holder.txtEntityLeadHead.setBackgroundColor(Color.parseColor(groupColor));
        //holder.txtEntityLeadHead.setBackgroundResource(R.drawable.schedule_border);

        holder.txtEntityLeadHead.setPadding(10, 10, 10, 10);

        // Entity Lead that dayName
        holder.txtEntityLeadSigned = (TextView) convertView.findViewById(R.id.txtEntityLeadSigned);
        holder.txtEntityLeadSigned.setPadding(10, 10, 10, 10);

        // Entity Scheduled Header
        holder.txtEntityScheduledHead = (TextView) convertView.findViewById(R.id.txtEntityScheduledHead);
        holder.txtEntityScheduledHead.setBackgroundColor(Color.parseColor(groupColor));
        holder.txtEntityScheduledHead.setPadding(10, 10, 10, 10);

        // Entity Scheduled
        holder.txtEntityScheduled = (TextView) convertView.findViewById(R.id.txtEntityScheduled);
        holder.txtEntityScheduled.setPadding(10, 10, 10, 10);

        //if (position == 0) {
            convertView.setTag(holder);
        //}

    }

    else

    {
            holder = (ViewHolder) convertView.getTag();
    }



        try {
            // Set DB data to TextViewID
            //holder.txtDayName.setText(dayName[position]);
            holder.txtDayDate.setText(dayDate[position]);
            holder.txtEntityLeadHead.setText(entityLeadHead);
            holder.txtEntityLeadSigned.setText(entityLeadSigned[position]);
            holder.txtEntityScheduledHead.setText(entityScheduledHead);
            holder.txtEntityScheduled.setText(entityScheduled[position]);
        }
        catch (NullPointerException e){
            Log.e("holder", e.toString());
        }

        Log.d("pos", String.valueOf(position));

    return convertView;
    }

        static class ViewHolder
    {
        //TextView txtDayName;
        TextView txtDayDate;
        TextView txtEntityLeadSigned;
        TextView txtEntityLeadHead;
        TextView txtEntityScheduledHead;
        TextView txtEntityScheduled;
    }

}

