package nl.cowboysenindiana.app.rooster.cowboysenindiana;

import android.support.v4.widget.DrawerLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.presencelist.ContentAdapter;

public class ScheduleDaySignedIn extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_dashboard;
    }


    @Override
    protected void goNext() {

        showToast("ScheduleDaySignedIn GO next");
        /** Initialise a gridView for PresenceList and Set ContentAdapter to GridView */

//        LinearLayout GridFrame = (LinearLayout) findViewById(R.id.GridFrame);

        GridView gridView = (GridView) findViewById(R.id.gridview);

//        GridFrame.addView(gridView);
        gridView.setAdapter(new ContentAdapter(this));
    }
//        /** Initialise Navigation ------------------*/
//        mNavigationDrawerFragment = (NavigationDrawerFragment)
//                getFragmentManager().findFragmentById(R.id.navigation_drawer);
//        mTitle = getTitle();
//
//        // Set up the drawer.
//        mNavigationDrawerFragment.setUp(
//                R.id.navigation_drawer,
//                (DrawerLayout) findViewById(R.id.drawer_layout));
//    }
//
//    @Override
//    public void onNavigationDrawerItemSelected(int position) {
//        // update the main content by replacing fragments
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();
//    }
//
//    // On select intnet to activity
//    private void onSectionAttached(int number) {
//        Intent intent;
//        switch (number) {
//            case 1:
//                mTitle = getString(R.string.nav_menu_dashboard);
//                break;
//            case 2:
//                mTitle = getString(R.string.nav_menu_kid);
//                intent = new Intent(this, ScheduleActivity.class);
//                startActivity(intent);
//                finish();
//                Log.d("NaV: ", String.valueOf(mTitle));
//                break;
//            case 3:
//                mTitle = getString(R.string.nav_menu_birthday);
//                break;
//            case 4:
//                mTitle = getString(R.string.nav_menu_theme);
//                break;
//            case 5:
//                mTitle = getString(R.string.nav_menu_about_app);
//                break;
//            case 6:
//                mTitle = getString(R.string.nav_menu_log_out);
//                System.exit(0);
//                break;
//        }
//    }
//
//
//
//    public void restoreActionBar() {
//        ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setTitle(mTitle);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
//            return rootView;
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((ScheduleDaySignedIn) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//            Log.d("NaV: ", String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));
//        }
//    }

}
