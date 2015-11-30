package nl.cowboysenindiana.app.model;

import java.util.Date;

/**
 * Child class
 * basic class to initialise "Child" object
 * @see Person, ChildProvider, Particularity
 * @author Sasha Antipin
 * @since 6-11-2015.
 */
public class Child extends Person {

    private Date birthDay;
    private String particularities;
//    private List<Particularity> particularities;
    private String providers;
//    private List<ChildProvider> providers;
    private Provider primaryProvider;
    private Date dateOfPlacement;
    private String image;
    private String isInside;

    public Child(){
        super.setPersonType(CHILD);
        super.setDateCreated(new Date());
    }

    public Date getBirthDay() { return birthDay; }
    public void setBirthDay(Date birthDay) { this.birthDay = birthDay; }

    public String getParticularities() { return particularities; }
    public void setParticularities(String particularities) { this.particularities = particularities; }

//    public List<Particularity> getParticularities() { return particularities; }
//    public void setParticularities(ArrayList<Particularity> particularities) { this.particularities = particularities; }
//    public void addParticularity(Particularity particularity) { this.particularities.add(particularity); }

    public String getProviders() { return providers; }
    public void setChildProviders(String providers) { this.providers = providers; }

//    public List<ChildProvider> getProviders() { return providers; }
//    public void setChildProviders(List<ChildProvider> providers) { this.providers = providers; }
//    public void addChildProvider(ChildProvider childProvider) { this.providers.add(childProvider); }

    public Provider getPrimaryChildProvider() { return primaryProvider; }
    public void setPrimaryChildProvider(Provider primaryProvider) { this.primaryProvider = primaryProvider; }

    public Date getDateOfPlacement() { return dateOfPlacement; }
    public void setDateOfPlacement(Date dateOfPlacement) { this.dateOfPlacement = dateOfPlacement; }

    public String isInside() {    return isInside;  }
    public void setInside(String inside) { this.isInside = inside;  }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Kind : " + this.getFirstName() + " " + this.getLastName()
                + "\t" + "| geb. " + this.getBirthDay() ;
    }
}
