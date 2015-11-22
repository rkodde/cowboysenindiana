package nl.cowboysenindiana.app.presencelist;

import android.app.Dialog;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import nl.cowboysenindiana.app.data.ChildToTest;
import nl.cowboysenindiana.app.data.DataProviderToTest;
import nl.cowboysenindiana.app.model.Child;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;
import nl.cowboysenindiana.app.utilities.UIHelper;

import java.util.List;

/**
 * ContentAdapter class
 * adapter for GridView
 * @see SquareImageView
 * @author Sasha Antipin
 * @since 27-10-2015.
 */
public final class PresenceListContentAdapter extends BaseAdapter {

    private Dialog dialog;
    private final List<ChildToTest> children;
//    private final List<Child> children;
    private final LayoutInflater mInflater;
    private Context context = null;
    private ChildToTest child;
//    private Child child;
    private String childName;
    private TextView childStatus;
    private boolean isInside;

    public PresenceListContentAdapter(Context context, List<ChildToTest> children) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.children = children;
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
            view = mInflater.inflate(R.layout.presencelist_grid_item, viewGroup, false);
            view.setTag(R.id.picture, view.findViewById(R.id.picture));
            view.setTag(R.id.text, view.findViewById(R.id.text));
        }

        child = (ChildToTest) getItem(position);
        childName = child.getChildName();
        isInside = child.isInside();

//        child = (Child) getItem(position);
//        childName = child.getFirstName() + " " + child.getLastName();
//        isInside = Boolean.parseBoolean(child.isInside());

        ImageView picture = (ImageView) view.getTag(R.id.picture);
        String imageId = "drawable/child_smile" + (1 + (int)(Math.random() * 8));
        final int currentImage = this.context.getResources().getIdentifier(imageId, null, context.getPackageName());
        picture.setImageResource(currentImage);

        /** Converting color image to grayscale if isInside equal false --------------- */
        if (isInside != true){
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            picture.setColorFilter(filter);
        }

        UIHelper.displayText(view, R.id.text, childName);

//        TextView name = (TextView) view.getTag(R.id.text);
//        name.setText(childName);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //@TODO functionality to check out the person's presence



//                dialog = new Dialog(context);
//                dialog.setTitle(childName);
//                dialog.setContentView(R.layout.presencelist_dialog);
//
//                ImageView picture = (ImageView) dialog.findViewById(R.id.picture);
//                picture.setImageResource(currentImage);
//
//                Switch aanwezigheidSwitch = (Switch) dialog.findViewById(R.id.switchButton);
//                childStatus = (TextView) dialog.findViewById(R.id.switchStatus);
//                childStatus.setText(childName);
//
//                // child status : set the switch to IN/OUT --------------
//                aanwezigheidSwitch.setChecked(false);
//                aanwezigheidSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                        if (isChecked) {
//                            childStatus.setText(childName + " is binnen");
//                        } else {
//                            childStatus.setText(childName + " is er niet");
//                        }
//                    }
//                });
//
//                // close presencelist_dialog ------------------------------------
//                ImageView icon_close = (ImageView) dialog.findViewById(R.id.icon_close);
//                icon_close.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
            }
        });
        return view;
    }
}