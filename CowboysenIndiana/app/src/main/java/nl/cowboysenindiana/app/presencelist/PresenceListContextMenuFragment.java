package nl.cowboysenindiana.app.presencelist;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import nl.cowboysenindiana.app.data.ChildToTest;
import nl.cowboysenindiana.app.editdata.AddChildDetail;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;
import nl.cowboysenindiana.app.schedule.ScheduleActivity;

/**
 * PresenceListMenuAdapter class
 * responsible for presentation the items of PresenceList context menu
 * @see PresenceListMenuAdapter, DialogFragment
 * @author Sasha Antipin
 * @since 24-11-2015
 * @version 0.1
 */
public class PresenceListContextMenuFragment extends DialogFragment {

    public static final String CHILD_BUNDLE = "CHILD_BUNDLE";

    private Intent intent;
    private Bundle bundle;
    private ChildToTest child;

    private List<MenuItem> menuItems;

    private PresenceListContextMenuFragment.MenuItem[] f = {
            new PresenceListContextMenuFragment.MenuItem ("item #1", R.drawable.ic_close_circle_black_48dp),
            new PresenceListContextMenuFragment.MenuItem ("item #2", R.drawable.ic_close_circle_black_48dp),
            new PresenceListContextMenuFragment.MenuItem ("item #3", R.drawable.ic_close_circle_black_48dp)
    };


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        getActivity().setContentView(R.layout.precenselist_context_menu);
        menuItems = Arrays.asList(f);
        bundle = getArguments();
        child = new ChildToTest(bundle);

        PresenceListMenuAdapter adapter = new PresenceListMenuAdapter(getActivity(),
                R.layout.presencelist_context_menu_item,
                this.menuItems);

        Dialog dialog = new Dialog(getActivity());
        dialog.setTitle(child.getChildName());
        dialog.setContentView(R.layout.precenselist_context_menu);

        ListView listView = (ListView) dialog.findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Callback method to be invoked when an item in this AdapterView has
             * been clicked.
             * <p>
             * Implementers can call getItemAtPosition(position) if they need
             * to access the data associated with the selected item.
             *
             * @param parent   The AdapterView where the click happened.
             * @param view     The view within the AdapterView that was clicked (this
             *                 will be a view provided by the adapter)
             * @param position The position of the view in the adapter.
             * @param id       The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), ScheduleActivity.class);
                        intent.putExtra(CHILD_BUNDLE, bundle);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), AddChildDetail.class);
                        intent.putExtra(CHILD_BUNDLE, bundle);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), ScheduleActivity.class);
                        intent.putExtra(CHILD_BUNDLE, bundle);
                        startActivity(intent);
                        break;
                    default:
                        // Nothing do!
                }
            }
        });

        return dialog;
    }

    static class MenuItem {
        private String menuItem;
        private int imageResource;

        public String getItemName() {  return menuItem;  }
        public int getImageResource() {  return imageResource; }

        public MenuItem(String id, int imageResource) {
            this.menuItem = id;
            this.imageResource = imageResource;
        }
    }
}