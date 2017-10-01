package com.pojo;
public class RegistrationActions {
private String myclass;

private String courseRegistrationStatus;

private String description;

private boolean remove;

private String voiceType;

public void setMyclass(String myclass){
this.myclass = myclass;
}
public String getMyclass(){
return this.myclass;
}
public void setCourseRegistrationStatus(String courseRegistrationStatus){
this.courseRegistrationStatus = courseRegistrationStatus;
}
public String getCourseRegistrationStatus(){
return this.courseRegistrationStatus;
}
public void setDescription(String description){
this.description = description;
}
public String getDescription(){
return this.description;
}
public void setRemove(boolean remove){
this.remove = remove;
}
public boolean getRemove(){
return this.remove;
}
public void setVoiceType(String voiceType){
this.voiceType = voiceType;
}
public String getVoiceType(){
return this.voiceType;
}

}