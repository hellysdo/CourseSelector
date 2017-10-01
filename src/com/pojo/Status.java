package com.pojo;
public class Status {
private boolean select;

private boolean sectionOpen;

private boolean timeConflict;

private boolean restricted;

public void setSelect(boolean select){
this.select = select;
}
public boolean getSelect(){
return this.select;
}
public void setSectionOpen(boolean sectionOpen){
this.sectionOpen = sectionOpen;
}
public boolean getSectionOpen(){
return this.sectionOpen;
}
public void setTimeConflict(boolean timeConflict){
this.timeConflict = timeConflict;
}
public boolean getTimeConflict(){
return this.timeConflict;
}
public void setRestricted(boolean restricted){
this.restricted = restricted;
}
public boolean getRestricted(){
return this.restricted;
}

}