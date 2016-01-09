package nl.cowboysenindiana.app.model;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Person abstract class
 * provide primary structure for every Person object
 * @author Sasha Antipin
 * @see android.support.annotation, java.util.HashMap
 * @since 6-11-2015.
 */
public abstract class Person {

    private long id;
    private String firstName;
    private String lastName;
    private String type;
    private String gender;
    private String contactInformation;
    //private Map<String, String> contactInformation;
    private Date created;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Define a person's type ------------------------------------------
     */
    public static final String PRIMARY_PROVIDER = "Hoofd verzorger";
    public static final String SECOND_PROVIDER = "Tweede verzorger";
    public static final String THIRD_PROVIDER = "Derde verzorger";
    public static final String LEADER = "Leidster";
    public static final String TRAINEE = "Stagair";
    public static final String MANAGER = "Manager";
    public static final String CHILD = "Kind";

    @StringDef({PRIMARY_PROVIDER, SECOND_PROVIDER, THIRD_PROVIDER, LEADER, TRAINEE, MANAGER, CHILD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {}

    @Type
    public String getPersonType(){ return type; }
    public void setPersonType(String t) {

        @Type String type = null;
        if (t.equals("Hoofd verzorger")) this.type = Person.PRIMARY_PROVIDER;
        else if (t.equals("Tweede verzorger")) this.type = Person.SECOND_PROVIDER;
        else if (t.equals("Derde verzorger")) this.type = Person.THIRD_PROVIDER;
        else if (t.equals("Leidster")) this.type = Person.LEADER;
        else if (t.equals("Stagair")) this.type = Person.TRAINEE;
        else if (t.equals("Manager")) this.type = Person.MANAGER;
        else if (t.equals("Kind")) this.type = Person.CHILD;
    }

    /**
     * Define Contact Information ------------------------------------------
     */
    public static final String ADDRESS = "address";
    public static final String ZIP_CODE = "zip code";
    public static final String CITY = "city";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone number";
    public static final String MOBILE_NUMBER = "mobile number";

    public String getContactInformation() { return contactInformation; }
//    public Map<String, String> getContactInformation() { return contactInformation; }

    public void setContactInformation( String contactInformation ) {
        this.contactInformation = contactInformation;
    }
//    public void setContactInformation( String address, String zip_code, String city, String email,
//                                       String phoneNumber, String mobileNumber ) {
//        contactInformation = new HashMap<>();
//        contactInformation.put(ADDRESS, address);
//        contactInformation.put(ZIP_CODE, zip_code);
//        contactInformation.put(CITY, city);
//        contactInformation.put(EMAIL, email);
//        contactInformation.put(PHONE_NUMBER, phoneNumber);
//        contactInformation.put(MOBILE_NUMBER, mobileNumber);
//    }

    /**
     * Define Gender Information ------------------------------------------
     */
    public static final String MALE = "Man";
    public static final String FEMALE = "Vrouw";

    @StringDef({MALE, FEMALE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gender {}

    @Type
    public  String getGender(){ return gender; }
    public void setGender(String g) {
        @Gender String gender = null;
        if (g.equals("Man")) this.gender = Person.MALE;
        else if (g.equals("Vrouw")) this.gender = Person.FEMALE;
    }

    /**
     * Define Date&Time of creating ---------------------------------------
     */
    public Date getDateCreated() { return created; }
    public void setDateCreated(Date created) { this.created = created; }

    /**
     * Overriding equals() and hashCode() methods to use correctly HashMap and HashSet ----
     */
    @Override
    public boolean equals(Object arg) {
        if (arg == null) return false;
        if (this == arg ) return true;
        if (arg instanceof Person) {
            Person that = (Person) arg;
            //TODO implementation of 'equals' method
//            if () {
//
//            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(id);
        bits ^= java.lang.Double.doubleToLongBits(id)*7;
        bits ^= java.lang.Double.doubleToLongBits(id)*11;
        bits ^= java.lang.Double.doubleToLongBits(id)*53;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    @Override public String toString() {
        return getClass().getName() + "[" +
                "firstName= " + firstName + ", " +
                "lastName= " + lastName + "]";
    }


}
