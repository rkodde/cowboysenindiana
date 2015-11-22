package nl.cowboysenindiana.app.data;

import android.os.Bundle;

/**
 * ChildToTest
 * to manage children's details
 * @author Sasha Antipin
 * @since 18-10-2015.
 */
public class ChildToTest {

    //	constants for field references
    public static final String CHILD_NUMBER= "childNumber";
    public static final String CHILD_NAME = "childName";
    public static final String IMAGE_RESOURCE = "imageResource";
    public static final String CHILD_NOTE = "childNote";
    public static final String IS_INSIDE = "isInside";

    //	private fields
    private int childNumber;
    private String childName;
    private int imageResource;
    private String childNote;
    private boolean isInside;

    // Getters and Setters --------------------------------------
    public int getChildNumber() { return childNumber; }
    public void setChildNumber(int childNumber) { this.childNumber = childNumber; }

    public String getChildName() { return childName; }
    public void setChildName(String childName) { this.childName = childName; }

    public String getChildNote() { return childNote; }
    public void setChildNote(String childNote) { this.childNote = childNote;  }

    public int getImageResource() { return imageResource; }
    public void setImageResource(int imageResource) { this.imageResource = imageResource; }

    public boolean isInside() {
        return isInside;
    }
    public void setIsInside(boolean isInside) {this.isInside = isInside; }

    // Used when creating the data object -------------------------
    public ChildToTest(int childNumber, String childName, String childNote, boolean isInside) {
        this.childNumber = childNumber;
        // this.imageResource = imageResource;
        this.childName = childName;
        this.childNote = childNote;
        this.isInside = isInside;
    }

    // Create from a bundle --------------------------------------
    public ChildToTest(Bundle b){
        if (b != null){
            this.childNumber = b.getInt(CHILD_NUMBER);
            this.childName = b.getString(CHILD_NAME);
            // this.imageResource = b.getInt(IMAGE_RESOURCE);
            this.childNote = b.getString(CHILD_NOTE);
            this.isInside = b.getBoolean(IS_INSIDE);
        }
    }

    //	Package data for transfer between activities -------------
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putDouble(CHILD_NUMBER, this.childNumber);
        b.putString(CHILD_NAME, this.childName);
        b.putInt(IMAGE_RESOURCE, this.imageResource);
        b.putString(CHILD_NOTE, this.childNote);
        b.putBoolean(IS_INSIDE, this.isInside);
        return b;
    }

    @Override
    public String toString() {
        return childName;
    }
}
