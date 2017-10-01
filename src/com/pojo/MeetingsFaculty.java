package com.pojo;
import java.util.List;
public class MeetingsFaculty {
private String category;

//private String class;

private String courseReferenceNumber;

private List<Faculty> faculty ;

private MeetingTime meetingTime;

private String term;

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
public void setFaculty(List<Faculty> faculty){
this.faculty = faculty;
}
public List<Faculty> getFaculty(){
return this.faculty;
}
public void setMeetingTime(MeetingTime meetingTime){
this.meetingTime = meetingTime;
}
public MeetingTime getMeetingTime(){
return this.meetingTime;
}
public void setTerm(String term){
this.term = term;
}
public String getTerm(){
return this.term;
}

}