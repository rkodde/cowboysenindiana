package nl.cowboysenindiana.app.profile;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by thijs on 18-11-15.
 */
public class ViewChildProfile extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_view_child_profile;
    }

    @Override
    protected int getMenuView() {return R.menu.menu_main;}

    @Override
    public void goNext() {
        //super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        String label1 = "About";
        ActionBar.Tab tab = actionBar.newTab();
        tab.setText(label1);
        TabListener<ViewChildProfileAbout> tl = new TabListener<ViewChildProfileAbout>(this,
                label1, ViewChildProfileAbout.class);
        tab.setTabListener(tl);
        actionBar.addTab(tab);

        String label2 = "Planning";
        tab = actionBar.newTab();
        tab.setText(label2);
        TabListener<ViewChildProfilePlanning> tl2 = new TabListener<ViewChildProfilePlanning>(this,
                label2, ViewChildProfilePlanning.class);
        tab.setTabListener(tl2);
        actionBar.addTab(tab);

        String label3 = "Geschiedenis";
        tab = actionBar.newTab();
        tab.setText(label3);
        TabListener<ViewChildProfileHistory> tl3 = new TabListener<ViewChildProfileHistory>(this,
                label2, ViewChildProfileHistory.class);
        tab.setTabListener(tl3);
        actionBar.addTab(tab);

        String label4 = "Financieel";
        tab = actionBar.newTab();
        tab.setText(label4);
        TabListener<ViewChildProfileFinancial> tl4 = new TabListener<ViewChildProfileFinancial>(this,
                label2, ViewChildProfileFinancial.class);
        tab.setTabListener(tl4);
        actionBar.addTab(tab);

    }









    private class TabListener<T extends Fragment> implements
            ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        /**
         * Constructor used each time a new tab is created.
         *
         * @param activity
         *            The host Activity, used to instantiate the fragment
         * @param tag
         *            The identifier tag for the fragment
         * @param clz
         *            The fragment's Class, used to instantiate the fragment
         */
        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            // Check if the fragment is already initialized
            if (mFragment == null) {
                // If not, instantiate and add it to the activity
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Usually do nothing.
        }
    }


}
