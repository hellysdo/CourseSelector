package com.pojo;
import java.util.List;
public class Root_Post_Course {
private List<Create> create ;

private List<Update> update ;

private List<Destroy> destroy ;

public void setCreate(List<Create> create){
this.create = create;
}
public List<Create> getCreate(){
return this.create;
}
public void setUpdate(List<Update> update){
this.update = update;
}
public List<Update> getUpdate(){
return this.update;
}
public void setDestroy(List<Destroy> destroy){
this.destroy = destroy;
}
public List<Destroy> getDestroy(){
return this.destroy;
}

}