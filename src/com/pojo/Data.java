package com.pojo;
import java.util.List;
public class Data {
private int id;

private String term;

private String termDesc;

private String courseReferenceNumber;

private String partOfTerm;

private String courseNumber;

private String subject;

private String subjectDescription;

private String sequenceNumber;

private String campusDescription;

private String scheduleTypeDescription;

private String courseTitle;

private String creditHours;

private int maximumEnrollment;

private int enrollment;

private int seatsAvailable;

private int waitCapacity;

private int waitCount;

private int waitAvailable;

private String crossList;

private String crossListCapacity;

private String crossListCount;

private String crossListAvailable;

private String creditHourHigh;

private int creditHourLow;

private String creditHourIndicator;

private boolean openSection;

private String linkIdentifier;

private boolean isSectionLinked;

private String subjectCourse;

private List<Faculty> faculty ;

private List<MeetingsFaculty> meetingsFaculty ;

private Status status;

public void setId(int id){
this.id = id;
}
public int getId(){
return this.id;
}
public void setTerm(String term){
this.term = term;
}
public String getTerm(){
return this.term;
}
public void setTermDesc(String termDesc){
this.termDesc = termDesc;
}
public String getTermDesc(){
return this.termDesc;
}
public void setCourseReferenceNumber(String courseReferenceNumber){
this.courseReferenceNumber = courseReferenceNumber;
}
public String getCourseReferenceNumber(){
return this.courseReferenceNumber;
}
public void setPartOfTerm(String partOfTerm){
this.partOfTerm = partOfTerm;
}
public String getPartOfTerm(){
return this.partOfTerm;
}
public void setCourseNumber(String courseNumber){
this.courseNumber = courseNumber;
}
public String getCourseNumber(){
return this.courseNumber;
}
public void setSubject(String subject){
this.subject = subject;
}
public String getSubject(){
return this.subject;
}
public void setSubjectDescription(String subjectDescription){
this.subjectDescription = subjectDescription;
}
public String getSubjectDescription(){
return this.subjectDescription;
}
public void setSequenceNumber(String sequenceNumber){
this.sequenceNumber = sequenceNumber;
}
public String getSequenceNumber(){
return this.sequenceNumber;
}
public void setCampusDescription(String campusDescription){
this.campusDescription = campusDescription;
}
public String getCampusDescription(){
return this.campusDescription;
}
public void setScheduleTypeDescription(String scheduleTypeDescription){
this.scheduleTypeDescription = scheduleTypeDescription;
}
public String getScheduleTypeDescription(){
return this.scheduleTypeDescription;
}
public void setCourseTitle(String courseTitle){
this.courseTitle = courseTitle;
}
public String getCourseTitle(){
return this.courseTitle;
}
public void setCreditHours(String creditHours){
this.creditHours = creditHours;
}
public String getCreditHours(){
return this.creditHours;
}
public void setMaximumEnrollment(int maximumEnrollment){
this.maximumEnrollment = maximumEnrollment;
}
public int getMaximumEnrollment(){
return this.maximumEnrollment;
}
public void setEnrollment(int enrollment){
this.enrollment = enrollment;
}
public int getEnrollment(){
return this.enrollment;
}
public void setSeatsAvailable(int seatsAvailable){
this.seatsAvailable = seatsAvailable;
}
public int getSeatsAvailable(){
return this.seatsAvailable;
}
public void setWaitCapacity(int waitCapacity){
this.waitCapacity = waitCapacity;
}
public int getWaitCapacity(){
return this.waitCapacity;
}
public void setWaitCount(int waitCount){
this.waitCount = waitCount;
}
public int getWaitCount(){
return this.waitCount;
}
public void setWaitAvailable(int waitAvailable){
this.waitAvailable = waitAvailable;
}
public int getWaitAvailable(){
return this.waitAvailable;
}
public void setCrossList(String crossList){
this.crossList = crossList;
}
public String getCrossList(){
return this.crossList;
}
public void setCrossListCapacity(String crossListCapacity){
this.crossListCapacity = crossListCapacity;
}
public String getCrossListCapacity(){
return this.crossListCapacity;
}
public void setCrossListCount(String crossListCount){
this.crossListCount = crossListCount;
}
public String getCrossListCount(){
return this.crossListCount;
}
public void setCrossListAvailable(String crossListAvailable){
this.crossListAvailable = crossListAvailable;
}
public String getCrossListAvailable(){
return this.crossListAvailable;
}
public void setCreditHourHigh(String creditHourHigh){
this.creditHourHigh = creditHourHigh;
}
public String getCreditHourHigh(){
return this.creditHourHigh;
}
public void setCreditHourLow(int creditHourLow){
this.creditHourLow = creditHourLow;
}
public int getCreditHourLow(){
return this.creditHourLow;
}
public void setCreditHourIndicator(String creditHourIndicator){
this.creditHourIndicator = creditHourIndicator;
}
public String getCreditHourIndicator(){
return this.creditHourIndicator;
}
public void setOpenSection(boolean openSection){
this.openSection = openSection;
}
public boolean getOpenSection(){
return this.openSection;
}
public void setLinkIdentifier(String linkIdentifier){
this.linkIdentifier = linkIdentifier;
}
public String getLinkIdentifier(){
return this.linkIdentifier;
}
public void setIsSectionLinked(boolean isSectionLinked){
this.isSectionLinked = isSectionLinked;
}
public boolean getIsSectionLinked(){
return this.isSectionLinked;
}
public void setSubjectCourse(String subjectCourse){
this.subjectCourse = subjectCourse;
}
public String getSubjectCourse(){
return this.subjectCourse;
}
public void setFaculty(List<Faculty> faculty){
this.faculty = faculty;
}
public List<Faculty> getFaculty(){
return this.faculty;
}
public void setMeetingsFaculty(List<MeetingsFaculty> meetingsFaculty){
this.meetingsFaculty = meetingsFaculty;
}
public List<MeetingsFaculty> getMeetingsFaculty(){
return this.meetingsFaculty;
}
public void setStatus(Status status){
this.status = status;
}
public Status getStatus(){
return this.status;
}

}