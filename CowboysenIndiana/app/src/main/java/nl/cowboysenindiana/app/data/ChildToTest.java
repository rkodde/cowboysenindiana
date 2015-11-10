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

    //	private fields
    private int childNumber;
    private String childName;
    private int imageResource;
    private String childNote;

    // Getters and Setters --------------------------------------
    public int getChildNumber() { return childNumber; }
    public void setChildNumber(int childNumber) { this.childNumber = childNumber; }

    public String getChildName() { return childName; }
    public void setChildName(String childName) { this.childName = childName; }

    public String getChildNote() { return childNote; }
    public void setChildNote(String childNote) { this.childNote = childNote;  }

    public int getImageResource() { return imageResource; }
    public void setImageResource(int imageResource) { this.imageResource = imageResource; }

    // Used when creating the data object -------------------------
    public ChildToTest(int childNumber, String childName, String childNote) {
        this.childNumber = childNumber;
        // this.imageResource = imageResource;
        this.childName = childName;
        this.childNote = childNote;
    }

    // Create from a bundle --------------------------------------
    public ChildToTest(Bundle b){
        if (b != null){
            this.childNumber = b.getInt(CHILD_NUMBER);
            this.childName = b.getString(CHILD_NAME);
            // this.imageResource = b.getInt(IMAGE_RESOURCE);
            this.childNote = b.getString(CHILD_NOTE);
        }
    }

    //	Package data for transfer between activities -------------
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putDouble(CHILD_NUMBER, this.childNumber);
        b.putString(CHILD_NAME, this.childName);
        b.putInt(IMAGE_RESOURCE, this.imageResource);
        b.putString(CHILD_NOTE, this.childNote);
        return b;
    }

    @Override
    public String toString() {
        return childName;
    }
}
