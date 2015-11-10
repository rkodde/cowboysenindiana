package nl.cowboysenindiana.app.data;

/**
 * Particularity class
 * basic class to initialise "Particularity" object
 * @see Child
 * @author Sasha Antipin
 * @since 6-11-2015.
 */
public class Particularity {

    private int particularityId;
    private String particularityType;
    private String description;

    public int getParticularityId() {
        return particularityId;
    }
    public void setParticularityId(int particularityId) {
        this.particularityId = particularityId;
    }

    public String getParticularityType() {
        return particularityType;
    }
    public void setParticularityType(String particularityType) {
        this.particularityType = particularityType;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


}
