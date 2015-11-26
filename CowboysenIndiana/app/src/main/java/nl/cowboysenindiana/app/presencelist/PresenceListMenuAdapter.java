package nl.cowboysenindiana.app.presencelist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * PresenceListMenuAdapter class
 * responsible for presentation the items of PresenceList context menu
 * @see ArrayAdapter
 * @author Sasha Antipin
 * @since 24-11-2015
 * @version 0.1
 */
public class PresenceListMenuAdapter extends ArrayAdapter {

    private Context context;
    private List<PresenceListContextMenuFragment.MenuItem> menuItems;

    public PresenceListMenuAdapter(Context context, int resource, List<PresenceListContextMenuFragment.MenuItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.menuItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PresenceListContextMenuFragment.MenuItem item = menuItems.get(position);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.presencelist_context_menu_item, null);

        ImageView image = (ImageView) view.findViewById(R.id.ivMenuItemImage);
        image.setImageResource(item.getImageResource());

        TextView tv = (TextView) view.findViewById(R.id.tvMenuItemName);
        tv.setText(item.getItemName());

        return view;
    }
}
