package nl.cowboysenindiana.app.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import nl.cowboysenindiana.app.model.Child;
import nl.cowboysenindiana.app.model.Person;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * ChildrenJDOMParser
 * uses XMLPull to get data from .xml source
 * @author Sasha Antipin
 * @see org.xmlpull.v1, InputStream, XmlPullParser, XmlPullParserFactory
 * @since 20-11-2015
 */
public class ChildrenPullParser {

	private static final String LOGTAG = "COWBOYSENINDIANA";

	public static final String CHILD_ID = "child_id";
	public static final String CHILD_FIRST = "first_name";
	public static final String CHILD_LAST = "last_name";
	public static final String CHILD_TYPE = "type";
	public static final String CHILD_GENDER = "gender";
	public static final String CHILD_CONTACTINFORMATION = "contact_information";
	public static final String CHILD_BIRTHDAY = "birthday";
	public static final String CHILD_PARTICULARITY = "particularity";
	public static final String CHILD_PROVIDERS = "providers";
	public static final String CHILD_PRIMARYPROVIDER = "primary_provider";
	public static final String CHILD_CREATED = "date_created";
	public static final String CHILD_PLACEMENT = "date_placement";
	public static final String CHILD_IMAGE = "image";
	public static final String CHILD_IS_INSIDE = "is_inside";

	private Child currentChild = null;
	private String currentTag = null;
	List<Child> children = new ArrayList<Child>();

	/**
	 * Method to get data from .xml file
	 * @param context
	 * @return
	 */
	public List<Child> parseXML(Context context) {

		try {
			Log.i("parseXML", "inside Parser");

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();

			InputStream stream = context.getResources().openRawResource(R.raw.children);
			xpp.setInput(stream, null);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					handleStartTag(xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					currentTag = null;
				} else if (eventType == XmlPullParser.TEXT) {
					handleText(xpp.getText());
				}
				eventType = xpp.next();
			}

		} catch (Resources.NotFoundException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (XmlPullParserException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (IOException e) {
			Log.d(LOGTAG, e.getMessage());
		}

		return children;
	}

	private void handleText(String text) {
		String xmlText = text;
		if (currentChild != null && currentTag != null) {
			if (currentTag.equals(CHILD_ID)) {
				Integer id = Integer.parseInt(xmlText);
				currentChild.setId(id);
			}
			else if (currentTag.equals(CHILD_FIRST)) {
				currentChild.setFirstName(xmlText);
			}
			else if (currentTag.equals(CHILD_LAST)) {
				currentChild.setLastName(xmlText);
			}
			else if (currentTag.equals(CHILD_TYPE)) {
				if ( xmlText == "Kind" ) currentChild.setPersonType(Person.CHILD);
			}
			else if (currentTag.equals(CHILD_GENDER)) {
				if(xmlText == "Male" ) currentChild.setGender(Person.MALE);
				else currentChild.setGender(Person.FEMALE);
			}
			else if (currentTag.equals(CHILD_CONTACTINFORMATION)) {
				currentChild.setContactInformation(xmlText);
//				currentChild.setContactInformation(null, null, null, null, null, null);
			}
			else if (currentTag.equals(CHILD_BIRTHDAY)) {
				try {
					currentChild.setBirthDay(new SimpleDateFormat("yyyy-MM-dd").parse(xmlText));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (currentTag.equals(CHILD_PARTICULARITY)) {
				currentChild.setParticularities(xmlText);
			}
			else if (currentTag.equals(CHILD_PROVIDERS)) {
				currentChild.setChildProviders(null);
			}
			else if (currentTag.equals(CHILD_PRIMARYPROVIDER)) {
				currentChild.setPrimaryChildProvider(null);
			}
			else if (currentTag.equals(CHILD_CREATED)) {
				try {
					currentChild.setDateCreated(new SimpleDateFormat("yyyy-MM-dd").parse(xmlText));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (currentTag.equals(CHILD_PLACEMENT)) {
				try {
					currentChild.setDateOfPlacement(new SimpleDateFormat("yyyy-MM-dd").parse(xmlText));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (currentTag.equals(CHILD_IS_INSIDE)) {
				currentChild.setInside(xmlText);
			}
			else if (currentTag.equals(CHILD_IMAGE)) {
				currentChild.setImage(xmlText);
			}
		}
	}

	private void handleStartTag(String name) {
		if (name.equals("child")) {
			currentChild = new Child();
			children.add(currentChild);
		}
		else {
			currentTag = name;
		}
	}
}
