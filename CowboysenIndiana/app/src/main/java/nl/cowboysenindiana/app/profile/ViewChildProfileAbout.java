package nl.cowboysenindiana.app.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by thijs on 18-11-15.
 */
public class ViewChildProfileAbout extends Fragment {


    private ListView listView;
    private ArrayList<ListItem> listItems;
    private ListAdapter adapter;

    private final int COLLAPSED_HEIGHT_1 = 110, COLLAPSED_HEIGHT_2 = 110,
            COLLAPSED_HEIGHT_3 = 250;

    private final int EXPANDED_HEIGHT_1 = 200, EXPANDED_HEIGHT_2 = 300,
            EXPANDED_HEIGHT_3 = 350, EXPANDED_HEIGHT_4 = 400;

    private boolean accordion = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_child_profile_about, container, false);

        listView = (ListView) view.findViewById(R.id.list_vaste_dagen);

        listItems = new ArrayList<ListItem>();
        mockItems();

        adapter = new ListAdapter(getContext(), R.layout.profile_list_item, listItems);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggle(view, position);
            }
        });

        return view;
    }

    private void toggle(View view, final int position) {
        view = this.getView();
        ListItem listItem = listItems.get(position);
//        listItem.getHolder().setTextViewWrap((LinearLayout) view);

        int fromHeight = 0;
        int toHeight = 0;

        if (listItem.isOpen()) {
            fromHeight = listItem.getExpandedHeight();
            toHeight = listItem.getCollapsedHeight();
        } else {
            fromHeight = listItem.getCollapsedHeight();
            toHeight = listItem.getExpandedHeight();
//
            // This closes all item before the selected one opens
//            if (accordion) {
//                closeAll();
//            }
        }

        toggleAnimation(listItem, position, fromHeight, toHeight, true);
    }


    private void closeAll() {
        int i = 0;
        for (ListItem listItem : listItems) {
            if (listItem.isOpen()) {
                toggleAnimation(listItem, i, listItem.getExpandedHeight(),
                        listItem.getCollapsedHeight(), false);
            }
            i++;
        }
    }

    private void toggleAnimation(final ListItem listItem, final int position,
                                 final int fromHeight, final int toHeight, final boolean goToItem) {

        ResizeAnimation resizeAnimation = new ResizeAnimation(adapter,
                listItem, 0, fromHeight, 0, toHeight);
        resizeAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listItem.setOpen(!listItem.isOpen());
                listItem.setDrawable(listItem.isOpen() ? R.drawable.seek_thumb_normal
                        : R.drawable.seek_thumb_normal);
                listItem.setCurrentHeight(toHeight);
                adapter.notifyDataSetChanged();

                if (goToItem)
                    goToItem(position);
            }
        });

        listItem.getHolder().getTextViewWrap().startAnimation(resizeAnimation);
    }

    private void goToItem(final int position) {
        listView.post(new Runnable() {
            @Override
            public void run() {
                try {
                    listView.smoothScrollToPosition(position);
                } catch (Exception e) {
                    listView.setSelection(position);
                }
            }
        });
    }

    private void mockItems() {
        listItems
                .add(new ListItem(
                        "Maandag \n10:00 - 17:00",
                        COLLAPSED_HEIGHT_1, COLLAPSED_HEIGHT_1,
                        EXPANDED_HEIGHT_1));

        listItems
                .add(new ListItem(
                        "Dinsdag \n09:00 - 15:00",
                        COLLAPSED_HEIGHT_1, COLLAPSED_HEIGHT_1,
                        EXPANDED_HEIGHT_1));

        listItems
                .add(new ListItem(
                        "Woensdag \n00:00 - 00:00",
                        COLLAPSED_HEIGHT_1, COLLAPSED_HEIGHT_1,
                        EXPANDED_HEIGHT_1));

        listItems
                .add(new ListItem(
                        "Donderdag \n10:00 - 15:00",
                        COLLAPSED_HEIGHT_1, COLLAPSED_HEIGHT_1,
                        EXPANDED_HEIGHT_1));

        listItems
                .add(new ListItem(
                        "Vrijdag \n00:00 - 00:00",
                        COLLAPSED_HEIGHT_1, COLLAPSED_HEIGHT_1,
                        EXPANDED_HEIGHT_1));

    }

}
