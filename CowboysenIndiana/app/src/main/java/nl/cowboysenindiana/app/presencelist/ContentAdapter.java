package nl.cowboysenindiana.app.presencelist;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import nl.cowboysenindiana.app.nl.cowboysenindiana.app.data.Child;
import nl.cowboysenindiana.app.nl.cowboysenindiana.app.data.DataProvider;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

import java.util.List;

/**
 * ContentAdapter class
 * adapter for GridView
 * @see SquareImageView,
 * Created by Sasha Antipin on 27-10-2015.
 */
public final class ContentAdapter extends BaseAdapter {

    private Dialog dialog;
    private final List<Child> mItems;
    private final LayoutInflater mInflater;
    private Context context = null;
    private Child item;

    public ContentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems = DataProvider.getData();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Child getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).getChildNumber();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView = view;
        ImageView picture;
        TextView name;

        if (myView == null) {
            myView = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            myView.setTag(R.id.picture, myView.findViewById(R.id.picture));
            myView.setTag(R.id.text, myView.findViewById(R.id.text));
        }

        picture = (ImageView) myView.getTag(R.id.picture);
        name = (TextView) myView.getTag(R.id.text);

        item = getItem(i);

        picture.setImageResource(R.drawable.dummy_image);
        name.setText(item.getChildName());

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //@TODO functionality to check out the person's presence
//                dialog = new Dialog(myViewx);
//                dialog.setTitle(item.getChildName());
//                dialog.setContentView(R.layout.dialog);
//                Button b1 = (Button) dialog.findViewById(R.id.add);
//                Button b2 = (Button) dialog.findViewById(R.id.absent);
//
//                ImageView picture = (ImageView) dialog.findViewById(R.id.picture);
//                picture.setImageResource(R.drawable.dummy_image);
//
//                b1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //@TODO add to presence list functionality
//                    }
//                });
//                b2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //@TODO absent from presence list functionality
//                    }
//                });
//                dialog.show();
            }
        });
        return myView;
    }
}
