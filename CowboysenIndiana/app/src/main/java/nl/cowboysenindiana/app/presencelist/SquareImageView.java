package nl.cowboysenindiana.app.presencelist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * SquareImageView class
 * Make a custom ImageView that maintains its aspect ratio
 * @see PresenceListContentAdapter
 * @author Sasha Antipin
 * @since 27-10-2015.
 */
public class SquareImageView extends ImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }
}
