package com.pojo;
public class Root_Get_Course {
private boolean success;

private String courseReferenceNumber;

private Model model;

public void setSuccess(boolean success){
this.success = success;
}
public boolean getSuccess(){
return this.success;
}
public void setCourseReferenceNumber(String courseReferenceNumber){
this.courseReferenceNumber = courseReferenceNumber;
}
public String getCourseReferenceNumber(){
return this.courseReferenceNumber;
}
public void setModel(Model model){
this.model = model;
}
public Model getModel(){
return this.model;
}

}