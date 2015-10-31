package nl.cowboysenindiana.app.presencelist;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    private final List<Child> objects;
    private final LayoutInflater mInflater;
    private Context context = null;
    private Child item;

    public ContentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        objects = DataProvider.getData();
    }

    @Override
    public int getCount() { return objects.size(); }

    @Override
    public Object getItem(int position) { return objects.get(position); }

    @Override
    public long getItemId(int position) {
        return objects.get(position).getChildNumber();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            view.setTag(R.id.picture, view.findViewById(R.id.picture));
            view.setTag(R.id.text, view.findViewById(R.id.text));
        }

        item = (Child) getItem(position);

        ImageView picture = (ImageView) view.getTag(R.id.picture);
        picture.setImageResource(R.drawable.dummy_image);

        TextView name = (TextView) view.getTag(R.id.text);
        name.setText(item.getChildName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //@TODO functionality to check out the person's presence
                dialog = new Dialog(context);
                dialog.setTitle(item.getChildName());
                dialog.setContentView(R.layout.dialog);
                Button b1 = (Button) dialog.findViewById(R.id.add);
                Button b2 = (Button) dialog.findViewById(R.id.absent);

                ImageView picture = (ImageView) dialog.findViewById(R.id.picture);
                picture.setImageResource(R.drawable.dummy_image);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //@TODO add to presence list functionality
                        dialog.dismiss();
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //@TODO absent from presence list functionality
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return view;
    }
}