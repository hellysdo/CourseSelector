package com.pojo;

public class Faculty {
private String bannerId;

private String category;

//private String class;

private String courseReferenceNumber;

private String displayName;

private String emailAddress;

private boolean primaryIndicator;

private String term;

public void setBannerId(String bannerId){
this.bannerId = bannerId;
}
public String getBannerId(){
return this.bannerId;
}
public void setCategory(String category){
this.category = category;
}
public String getCategory(){
return this.category;
}
public void setCourseReferenceNumber(String courseReferenceNumber){
this.courseReferenceNumber = courseReferenceNumber;
}
public String getCourseReferenceNumber(){
return this.courseReferenceNumber;
}
public void setDisplayName(String displayName){
this.displayName = displayName;
}
public String getDisplayName(){
return this.displayName;
}
public void setEmailAddress(String emailAddress){
this.emailAddress = emailAddress;
}
public String getEmailAddress(){
return this.emailAddress;
}
public void setPrimaryIndicator(boolean primaryIndicator){
this.primaryIndicator = primaryIndicator;
}
public boolean getPrimaryIndicator(){
return this.primaryIndicator;
}
public void setTerm(String term){
this.term = term;
}
public String getTerm(){
return this.term;
}

}