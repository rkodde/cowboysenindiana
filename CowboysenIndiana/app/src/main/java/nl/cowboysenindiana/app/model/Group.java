package nl.cowboysenindiana.app.model;

/**
 * Group class
 * basic class to initialise "Group" object
 * @see Child
 * @autor Sasha Antipin
 * @since 7-11-2015.
 */
public class Group {

    private long groupID;

    private String groupName;
    private String color;
    private String description;
    private int minAge, maxAge;

    public Group(){

    }

    public Group(int id, String groupName, String color){
        this.groupID = id;
        this.groupName = groupName;
        this.color = color;
    }


    public Group(String groupName, String color){
        this.groupName = groupName;
        this.color = color;
    }

    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getGroupID() {
        return groupID;
    }
    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinAge() {
        return minAge;
    }
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }
}
