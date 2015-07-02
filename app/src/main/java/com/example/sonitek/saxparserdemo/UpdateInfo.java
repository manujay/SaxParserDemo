package com.example.sonitek.saxparserdemo;

/**
 * Created by sonitek on 7/1/2015.
 */
public class UpdateInfo {
    String updates;
    String info;
    String infoUpdate;
    String infoPage;
    String pageId;
    String pageHeading;
    String pageContent;

    public void setInfo(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfoUpdate(String infoUpdate){
        this.infoUpdate = infoUpdate;
    }

    public String getInfoUpdate(){
        return infoUpdate;
    }

    public void setInfoPage(String infoPage){
        this.infoPage = infoPage;
    }

    public String getInfoPage() {
        return infoPage;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageHeading() {
        return pageHeading;
    }

    public void setPageHeading(String pageHeading) {
        this.pageHeading = pageHeading;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }


    public String getUpdates() {

        return updates;
    }

    public void setUpdates(String updates) {
        this.updates = updates;
    }
}
