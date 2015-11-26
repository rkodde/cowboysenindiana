package nl.cowboysenindiana.app.rooster.cowboysenindiana;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import nl.cowboysenindiana.app.data.ChildDataSource;
import nl.cowboysenindiana.app.data.ChildToTest;
import nl.cowboysenindiana.app.data.DataProviderToTest;
import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.presencelist.PresenceListContextMenuFragment;
import nl.cowboysenindiana.app.utilities.ScreenUtility;
import nl.cowboysenindiana.app.utilities.UIHelper;

/**
 * PresenceListActivity class
 * responsible for presentation of Presence list
 * @uses inner class PresenceListContentAdapter
 * @see BaseActivity, GridView, SquareImageView, BaseAdapter, UIHelper, ColorMatrix, ActionMode.Callback
 *      PresenceListMenuAdapter, PresenceListContextMenuFragment
 * @author Sasha Antipin
 * @since 16-11-2015
 * @version 0.3
 */

public class PresenceListActivity extends BaseActivity {

    private GridView gridView;
    private List<ChildToTest> children;
//    private List<Child> children;

    private ChildToTest child;
    //    private Child child;
    private String childName;
    private boolean isInside;

    private ChildDataSource dataSource;

    public static final String CHILD_BUNDLE = "CHILD_BUNDLE";
    private static final int REQUEST_CODE = 1001;

    @Override
    protected int getContentView() {
        return R.layout.activity_dashboard;
    }


    @Override
    protected void goNext() {

        showToast("get data for PresenceList");

        children = DataProviderToTest.getData();

        refreshDisplay();
    }

    /** refresh content to display ----------------
     * @param
     * */
    public void refreshDisplay(){

        /** Initialise a gridView for PresenceList and Set ContentAdapter to GridView */
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(this.new PresenceListContentAdapter(this));

        /** depend on screen size set columns number ------------*/
        ScreenUtility utility = new ScreenUtility(this);
        if (utility.isHorizontal()) gridView.setNumColumns(5);
        else gridView.setNumColumns(3);
    }




    /** inner class PresenceListContentAdapter ====================================================
     *  to initialise GridView with content
     *  @see BaseAdapter, SquareImageView, UIHelper, ColorMatrix, ActionMode.Callback
     */
    private final class PresenceListContentAdapter extends BaseAdapter {

        private final LayoutInflater inflater;
        private Context context;

        public PresenceListContentAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public int getCount() { return children.size(); }

        @Override
        public Object getItem(int position) { return children.get(position); }

        @Override
        public long getItemId(int position) {
            return children.get(position).getChildNumber();         // getId() for Child
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = inflater.inflate(R.layout.presencelist_grid_item, viewGroup, false);
                view.setTag(R.id.picture, view.findViewById(R.id.picture));
                view.setTag(R.id.text, view.findViewById(R.id.text));
            }

            child = (ChildToTest) getItem(position);
            childName = child.getChildName();
            isInside = child.isInside();

            ImageView picture = (ImageView) view.getTag(R.id.picture);
            String imageId = "drawable/child_smile" + (1 + (int)(Math.random() * 8));
            final int currentImage = this.context.getResources().getIdentifier(imageId, null, context.getPackageName());
            picture.setImageResource(currentImage);
            if (isInside != true) setColor(picture);

            UIHelper.displayText(view, R.id.text, childName + " " + isInside);

            view.isClickable();


            /** set setOnClickListener on item --------------------------------*/
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = child.toBundle();

                    PresenceListContextMenuFragment dialog = new PresenceListContextMenuFragment();
                    dialog.setArguments(bundle);
                    dialog.show(getFragmentManager(), "my dialog");


                }
            });// ---------------------------------------- closing the setOnClickListener method


            /** set setOnLongClickListener on item -----------------------------*/
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (isInside != true) child.setIsInside(true);
                    else child.setIsInside(false);

                    PresenceListActivity.this.refreshDisplay();
                    Toast.makeText(context, "Long Clicked", Toast.LENGTH_SHORT).show();

                    return true;
                }
            }); // ------------------------------------ closing the setOnLongClickListener method

            return view;
        }

        private void setColor(ImageView picture) {
            /** Converting color image to grayscale if isInside equal false --------------- */
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            picture.setColorFilter(filter);
        }
    }
}
