package nl.cowboysenindiana.app.frame;

/**
 * Created by Rene on 22-11-2015.
 */
class NavItem {
    String mTitle;
    String mSubtitle;
    Class mNewActivity;
    int mIcon;

    public NavItem(String title, String subtitle, int icon, Class newActivity) {
        mTitle = title;
        mSubtitle = subtitle;
        mIcon = icon;
        mNewActivity = newActivity;
    }
}