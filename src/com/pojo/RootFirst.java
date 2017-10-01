package com.pojo;
import java.util.List;
public class RootFirst {
private boolean success;

private int totalCount;

private List<Data> data ;

private int pageOffset;

private int pageMaxSize;

private int sectionsFetchedCount;

private String pathMode;

private List<SearchResultsConfigs> searchResultsConfigs ;

public void setSuccess(boolean success){
this.success = success;
}
public boolean getSuccess(){
return this.success;
}
public void setTotalCount(int totalCount){
this.totalCount = totalCount;
}
public int getTotalCount(){
return this.totalCount;
}
public void setData(List<Data> data){
this.data = data;
}
public List<Data> getData(){
return this.data;
}
public void setPageOffset(int pageOffset){
this.pageOffset = pageOffset;
}
public int getPageOffset(){
return this.pageOffset;
}
public void setPageMaxSize(int pageMaxSize){
this.pageMaxSize = pageMaxSize;
}
public int getPageMaxSize(){
return this.pageMaxSize;
}
public void setSectionsFetchedCount(int sectionsFetchedCount){
this.sectionsFetchedCount = sectionsFetchedCount;
}
public int getSectionsFetchedCount(){
return this.sectionsFetchedCount;
}
public void setPathMode(String pathMode){
this.pathMode = pathMode;
}
public String getPathMode(){
return this.pathMode;
}
public void setSearchResultsConfigs(List<SearchResultsConfigs> searchResultsConfigs){
this.searchResultsConfigs = searchResultsConfigs;
}
public List<SearchResultsConfigs> getSearchResultsConfigs(){
return this.searchResultsConfigs;
}

}