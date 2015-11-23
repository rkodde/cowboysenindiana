package nl.cowboysenindiana.app.profile;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by thijs on 23-11-15.
 */
public class ResizeAnimation extends Animation {
    private View mView;
    private float mToHeight;
    private float mFromHeight;

    private float mToWidth;
    private float mFromWidth;

    private ListAdapter mListAdapter;
    private ListItem mListItem;

    public ResizeAnimation(ListAdapter listAdapter, ListItem listItem,
                           float fromWidth, float fromHeight, float toWidth, float toHeight) {
        mToHeight = toHeight;
        mToWidth = toWidth;
        mFromHeight = fromHeight;
        mFromWidth = fromWidth;
        mView = listItem.getHolder().getTextViewWrap();
        mListAdapter = listAdapter;
        mListItem = listItem;
        setDuration(200);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float height = (mToHeight - mFromHeight) * interpolatedTime
                + mFromHeight;
        float width = (mToWidth - mFromWidth) * interpolatedTime + mFromWidth;
        AbsListView.LayoutParams p = (AbsListView.LayoutParams) mView.getLayoutParams();
        p.height = (int) height;
        p.width = (int) width;
        mListItem.setCurrentHeight(p.height);
        ((BaseAdapter) mListAdapter).notifyDataSetChanged();
    }
}
