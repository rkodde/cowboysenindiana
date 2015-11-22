package nl.cowboysenindiana.app.rooster.cowboysenindiana;

import android.widget.GridView;

import java.util.List;

import nl.cowboysenindiana.app.data.ChildDataSource;
import nl.cowboysenindiana.app.data.ChildToTest;
import nl.cowboysenindiana.app.data.DataProviderToTest;
import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.model.Child;
import nl.cowboysenindiana.app.presencelist.PresenceListContentAdapter;
import nl.cowboysenindiana.app.utilities.ChildrenPullParser;
import nl.cowboysenindiana.app.utilities.ScreenUtility;

public class PresenceListActivity extends BaseActivity {

    private GridView gridView;
    private List<ChildToTest> children;
//    private List<Child> children;
    private ChildDataSource dataSource;

    @Override
    protected int getContentView() {
        return R.layout.activity_dashboard;
    }


    @Override
    protected void goNext() {

       // showToast("ScheduleDaySignedIn GO next");


        showToast("get data");

        children = DataProviderToTest.getData();

//        /** get data from data provider -------------------------*/
//        dataSource = new ChildDataSource(this);
//        showToast("Data source is hier");
//
//
//        dataSource.open();
//        showToast("Data source is open ");
//
//        children = dataSource.findAll();
//        if (children.size() == 0) {
//            showToast("Data source is not found");
//            createData();
//            showToast("Data source is crated");
//
//            children = dataSource.findAll();
//        }
        refreshDisplay();
    }

    /** refresh content to display ----------------
     * @param
     * */
    public void refreshDisplay(){

        /** Initialise a gridView for PresenceList and Set ContentAdapter to GridView */
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new PresenceListContentAdapter(this, this.children));

        /** depend on screen size set columns number ------------*/
        ScreenUtility utility = new ScreenUtility(this);
        if (utility.isHorizontal()) gridView.setNumColumns(5);
        else gridView.setNumColumns(3);
    }

    /**
     * Methode opens dataSource
     * is called explicitly as the activity comes to the screen
     * @param
     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        dataSource.open();
//    }
//
//    /**
//     * Methode close the data source connection
//     * whenever the activity pauses (closes down)
//     * @param
//     */
//    @Override
//    protected void onPause() {
//        super.onPause();
//        dataSource.close();
//    }
//
//    /** create data method
//     * @param
//     */
//    private void createData() {
//        /**  import data from an XML file --------------------*/
//        ChildrenPullParser parser = new ChildrenPullParser();
//        List<Child> children = parser.parseXML(this);
//        /**  pass data to local SQL database --------------------*/
//        for (Child child : children ) {
//            dataSource.create(child);
//        }
//    }

}
