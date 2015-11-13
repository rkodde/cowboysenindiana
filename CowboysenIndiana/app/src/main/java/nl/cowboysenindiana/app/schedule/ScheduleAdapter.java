package nl.cowboysenindiana.app.schedule;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by Tessa on 10/11/15.
 */

public class ScheduleAdapter extends BaseAdapter
{
    Context mContext;

    // Db Values6
    private String groupColor = "#18A608";
    private String themeColor = "#658736";

    private String [] day = {"Maandag","Dinsdag","Woensdag","Donderdag","Vrijdag","Zaterdag","Zondag"};

    private String entityLeadHead = "Teacher";
    private String[] entityLeadSigned ={"Rohit","Rahul","Ravi","Amit","Arun","",""};

    private String entityScheduledHead = "Kids";
    private String [] entityScheduled ={"25 / 30","25 / 30","25 / 30","25 / 30","25 / 30","",""};



    private LayoutInflater mInflater;

    public void setGroupColor(String groupColor){
        this.groupColor = groupColor;
    }

    public ScheduleAdapter(Context c)
    {
        mContext=c;
        mInflater = LayoutInflater.from(c);
    }

    public int getCount()
    {
        return day.length;
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

    ViewHolder holder = null;
    if(convertView==null)

    {
        convertView = mInflater.inflate(R.layout.schedule_main_grid,
                parent, false);
        holder = new ViewHolder();

        // Gif the TextView custom settings.
        // Day of the week
        holder.txtDay = (TextView) convertView.findViewById(R.id.txtId);
        holder.txtDay.setPadding(100, 10, 10, 10);
        // Entity Lead Header
        holder.txtEntityLeadHead = (TextView) convertView.findViewById(R.id.txtEntityLeadHead);
        holder.txtEntityLeadHead.setBackgroundColor(Color.parseColor(groupColor));
        holder.txtEntityLeadHead.setPadding(100, 10, 10, 10);
        // Entity Lead that day
        holder.txtEntityLeadSigned = (TextView) convertView.findViewById(R.id.txtEntityLeadSigned);
        holder.txtEntityLeadSigned.setPadding(100, 10, 10, 10);
        // Entity Scheduled Header
        holder.txtEntityScheduledHead = (TextView) convertView.findViewById(R.id.txtEntityScheduledHead);
        holder.txtEntityScheduledHead.setBackgroundColor(Color.parseColor(groupColor));
        holder.txtEntityScheduledHead.setPadding(100, 10, 10, 10);
        // Entity Scheduled
        holder.txtEntityScheduled = (TextView) convertView.findViewById(R.id.txtEntityScheduled);
        holder.txtEntityScheduled.setPadding(100, 10, 10, 10);

        if (position == 0) {
            convertView.setTag(holder);
        }

    }

    else

    {
        holder = (ViewHolder) convertView.getTag();
    }

    // Set DB data to TextViewID
    holder.txtDay.setText(day[position]);
    holder.txtEntityLeadHead.setText(entityLeadHead);
    holder.txtEntityLeadSigned.setText(entityLeadSigned[position]);
    holder.txtEntityScheduledHead.setText(entityScheduledHead);
    holder.txtEntityScheduled.setText(entityScheduled[position]);


        Log.d("pos", String.valueOf(position));

    return convertView;
}

        static class ViewHolder
    {
        TextView txtDay;
        TextView txtEntityLeadSigned;
        TextView txtEntityLeadHead;
        TextView txtEntityScheduledHead;
        TextView txtEntityScheduled;
    }

}

