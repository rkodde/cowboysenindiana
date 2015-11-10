package nl.cowboysenindiana.app.data;

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
    private Map<String, String> contactInformation;
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
    public static final String THIRD_PROVIDER = "Derde verzorgen";
    public static final String LEADER = "Leidster";
    public static final String TRAINEE = "Stagair";
    public static final String MANAGER = "Manager";
    public static final String CHILD = "Kinder";

    @StringDef({PRIMARY_PROVIDER, SECOND_PROVIDER, THIRD_PROVIDER, LEADER, TRAINEE, MANAGER, CHILD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {}

    @Type
    public String getPersonType(){ return type; }
    public void setPersonType(@Type String type) { this.type = type; }

    /**
     * Define Contact Information ------------------------------------------
     */
    public static final String ADDRESS = "address";
    public static final String ZIP_CODE = "zip code";
    public static final String CITY = "city";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone number";
    public static final String MOBILE_NUMBER = "mobile number";

    public Map<String, String> getContactInformation() { return contactInformation; }
    public void setContactInformation( String address, String zip_code, String city, String email,
                                       String phoneNumber, String mobileNumber ) {
        contactInformation = new HashMap<>();
        contactInformation.put(ADDRESS, address);
        contactInformation.put(ZIP_CODE, zip_code);
        contactInformation.put(CITY, city);
        contactInformation.put(EMAIL, email);
        contactInformation.put(PHONE_NUMBER, phoneNumber);
        contactInformation.put(MOBILE_NUMBER, mobileNumber);
    }

    /**
     * Define Gender Information ------------------------------------------
     */
    public static final String MALE = "Man";
    public static final String FEMAILE = "Vrouw";

    @StringDef({MALE, FEMAILE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gender {}

    @Type
    public  String getGender(){ return gender; }
    public void setGender(@Gender String gender) { this.gender = gender; }

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
                "lastName= " + lastName + ", " +
                "contactInformation= " + contactInformation + ", " +
                "date= " + created.toString() + "]";
    }


}
