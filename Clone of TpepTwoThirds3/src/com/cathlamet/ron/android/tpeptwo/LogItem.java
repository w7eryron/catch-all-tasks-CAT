package com.cathlamet.ron.android.tpeptwo;




import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class LogItem {
	
	private static final String JSON_ID = "id";
	private static final String JSON_TITLE = "title";
	private static final String JSON_DESCRIPTION = "description";
	private static final String JSON_DATE = "date";
	private static final String JSON_CRITERIA = "student learning";
	private static final String JSON_CRITERIA_NUM = "0.1*";
	private static final String JSON_HOURS = "1.1";
	private static final String JSON_DOC_TITLE = "Document title";
	private static final String JSON_DOC_LINK = "Google";
	private static final String JSON_PHOTO = "not yet";
	
private UUID mId;
private String mTitle = "title";
private String mDescription = "None yet";
private String mCriteria = "2.1";
private  double mCriteriaNum = 2.1;
private String mHours = "1.0";
private Date mDate;
private String mDocTitle = "None at all";
private String mDocLink = "not anymore";
private String mPhoto = "not yet"; 

public LogItem(){
	//generate unique identifier
	mId = UUID.randomUUID();
	mDate = new Date();
}

public LogItem(JSONObject json) throws JSONException{
	mId = UUID.fromString(json.getString(JSON_ID));
	if(json.has(JSON_TITLE)){
		mTitle = json.getString(JSON_TITLE);
	}
	mDate = new Date(json.getLong(JSON_DATE));
	mDescription = json.getString(JSON_DESCRIPTION);
	mCriteria = json.getString(JSON_CRITERIA);
	mCriteriaNum = Double.parseDouble(json.getString(JSON_CRITERIA_NUM));
	mHours = json.getString(JSON_HOURS);
	mDocTitle = json.getString(JSON_DOC_TITLE);
	mDocLink = json.getString(JSON_DOC_LINK);
	mPhoto = json.getString(JSON_PHOTO);
}
public JSONObject toJSON() throws JSONException{
	JSONObject json = new JSONObject();
	json.put(JSON_ID, mId.toString());
	json.put(JSON_TITLE, mTitle);
	json.put(JSON_DATE, mDate.getTime());
	json.put(JSON_DESCRIPTION, mDescription);
	json.put(JSON_CRITERIA, mCriteria);
	json.put(JSON_CRITERIA_NUM, mCriteriaNum);
	json.put(JSON_HOURS, mHours);
	json.put(JSON_DOC_TITLE, mDocTitle);
	json.put(JSON_DOC_LINK, mDocLink);
	json.put(JSON_PHOTO, mPhoto);
	return json;
	
}

@Override
public String toString(){
	return mTitle;
}

/**
 * @return the title
 */
public String getTitle() {
	return mTitle;
}

/**
 * @param title the title to set
 */
public void setTitle(String title) {
	mTitle = title;
}

/**
 * @return the description
 */
public String getDescription() {
	return mDescription;
}

/**
 * @param description the description to set
 */
public void setDescription(String description) {
	mDescription = description;
}

/**
 * @return the criteria
 */
public String getCriteria() {
	return mCriteria;
}

/**
 * @param criteria the criteria to set
 */
public void setCriteria(String criteria) {
	mCriteria = criteria;
}

/**
 * @return the hours
 */
public String getHours() {
	return mHours;
}

/**
 * @param hours the hours to set
 */
public void setHours(String hours) {
	mHours = hours;
}

/**
 * @return the date
 */
public Date getDate() {
	return mDate;
}

/**
 * @param date the date to set
 */
public void setDate(Date date) {
	mDate = date;
}

/**
 * @return the supportOne
 */
public String getDocTitle() {
	return mDocTitle;
}

/**
 * @param supportOne the supportOne to set
 */
public void setDocTitle(String supportOne) {
	mDocTitle = supportOne;
}

/**
 * @return the supportTwo
 */
public String getDocLink() {
	return mDocLink;
}

/**
 * @param supportTwo the supportTwo to set
 */
public void setDocLink(String supportTwo) {
	mDocLink = supportTwo;
}

public double getCriteriaNum() {
	return mCriteriaNum;
}

public void setCriteriaNum(double criteriaNum) {
	mCriteriaNum = criteriaNum;
}

public String getPhoto() {
	return mPhoto;
}

public void setPhoto(String photo) {
	mPhoto = photo;
}

/**
 * @return the id
 */
public UUID getId() {
	return mId;
}

}
