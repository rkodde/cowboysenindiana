package nl.cowboysenindiana.app.nl.cowboysenindiana.app.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Child class
 * basic class to initialise "Child" object
 * @see Person, ChildProvider, Particularity
 * @author Sasha Antipin
 * @since 6-11-2015.
 */
public class Child extends Person {

    private Date birthDay;
    private List<Particularity> particularities;
    private List<ChildProvider> providers;
    private ChildProvider primaryProvider;
    private Date dateOfPlacement;

    private Child(){
        super.setPersonType(CHILD);
        super.setDateCreated(new Date());
    }

    public Date getBirthDay() { return birthDay; }
    public void setBirthDay(Date birthDay) { this.birthDay = birthDay; }

    public List<Particularity> getParticularities() { return particularities; }
    public void setParticularities(ArrayList<Particularity> particularities) { this.particularities = particularities; }
    public void addParticularity(Particularity particularity) { this.particularities.add(particularity); }

    public List<ChildProvider> getProviders() { return providers; }
    public void setChildProviders(List<ChildProvider>providers) { this.providers = providers; }
    public void addChildProvider(ChildProvider childProvider) { this.providers.add(childProvider); }

    public ChildProvider getPrimaryChildProvider() { return primaryProvider; }
    public void setPrimaryChildProvider(ChildProvider primaryProvider) { this.primaryProvider = primaryProvider; }

    public Date getDateOfPlacement() { return dateOfPlacement; }
    public void setDateOfPlacement(Date dateOfPlacement) { this.dateOfPlacement = dateOfPlacement; }
}
