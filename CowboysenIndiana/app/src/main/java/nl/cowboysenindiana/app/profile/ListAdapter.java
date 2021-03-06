package nl.cowboysenindiana.app.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by thijs on 23-11-15.
 */
public class ListAdapter extends ArrayAdapter<ListItem> {

    private ArrayList<ListItem> listItems;
    private Context context;

    public ListAdapter(Context context, int textViewResourceId,
                       ArrayList<ListItem> listItems) {
        super(context, textViewResourceId, listItems);
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    @SuppressWarnings("deprecation")
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder holder;
        ListItem listItem = listItems.get(position);

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.profile_list_item, null);

            LinearLayout textViewWrap = (LinearLayout) convertView
                    .findViewById(R.id.text_wrap);
            TextView text = (TextView) convertView.findViewById(R.id.list_item_text);

            holder = new ListViewHolder(textViewWrap, text);
        } else
            holder = (ListViewHolder) convertView.getTag();

        holder.getTextView().setText(listItem.getText());

        LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
                listItem.getCurrentHeight());
        holder.getTextViewWrap().setLayoutParams(layoutParams);

        holder.getTextView().setCompoundDrawablesWithIntrinsicBounds(
                listItem.getDrawable(), 0, 0, 0);

        convertView.setTag(holder);

        listItem.setHolder(holder);

        return convertView;
    }
}
