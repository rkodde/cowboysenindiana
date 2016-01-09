package nl.cowboysenindiana.app.model;

import android.os.Bundle;

import java.util.Date;


/**
 * Child class
 * basic class to initialise "Child" object
 *
 * @author Sasha Antipin
 * @see Person, ChildProvider, Particularity
 * @since 6-11-2015.
 */
public class Child extends Person {

    //	constants for field references
    public static final String CHILD_ID = "id";
    public static final String CHILD_FIRST_NAME = "firstName";
    public static final String CHILD_LAST_NAME = "lastName";
    public static final String IMAGE = "image";

    private Date birthDay;
    private String particularities;
    //    private List<Particularity> particularities;
    private String providers;
    //    private List<ChildProvider> providers;
    private Provider primaryProvider;
    private Date dateOfPlacement;
    private String image;
    private boolean isInside;

    public Child() {
        super.setPersonType(CHILD);
        super.setDateCreated(new Date());
    }


    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

//    public String getParticularities() { return particularities; }
//    public void setParticularities(String particularities) { this.particularities = particularities; }

//    public List<Particularity> getParticularities() { return particularities; }
//    public void setParticularities(ArrayList<Particularity> particularities) { this.particularities = particularities; }
//    public void addParticularity(Particularity particularity) { this.particularities.add(particularity); }

//    public String getProviders() { return providers; }
//    public void setChildProviders(String providers) { this.providers = providers; }

//    public List<ChildProvider> getProviders() { return providers; }
//    public void setChildProviders(List<ChildProvider> providers) { this.providers = providers; }
//    public void addChildProvider(ChildProvider childProvider) { this.providers.add(childProvider); }

//    public Provider getPrimaryChildProvider() { return primaryProvider; }
//    public void setPrimaryChildProvider(Provider primaryProvider) { this.primaryProvider = primaryProvider; }

    public Date getDateOfPlacement() {
        return dateOfPlacement;
    }

    public void setDateOfPlacement(Date dateOfPlacement) {
        this.dateOfPlacement = dateOfPlacement;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        this.isInside = inside;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Kind : " + this.getFirstName() + " " + this.getLastName()
                + "\t" + "| geb. " + this.getBirthDay();
    }


    // Create from a bundle --------------------------------------
    public Child(Bundle b) {
        if (b != null) {
            super.setPersonType(CHILD);
            super.setId(b.getLong(CHILD_ID));
            super.setFirstName(b.getString(CHILD_FIRST_NAME));
            super.setLastName(b.getString(CHILD_LAST_NAME));
            image = b.getString(IMAGE);
        }
    }

    //	Package data for transfer between activities -------------
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putLong(CHILD_ID, this.getId());
        b.putString(CHILD_FIRST_NAME, this.getFirstName());
        b.putString(CHILD_LAST_NAME, this.getLastName());
        b.putString(IMAGE, this.image);
        return b;
    }
}
