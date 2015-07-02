package com.example.sonitek.saxparserdemo;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by sonitek on 7/1/2015.
 */
public class HandleXML extends DefaultHandler {

    boolean updates = false;
    boolean info = false;
    boolean infoUpdate = false;
    boolean infoPage = false;
    boolean PageId = false;
    boolean pageHeading = false;
    boolean pageContent = false;
    //private String parsedData = null;
    //private boolean parseComplete = true;
    UpdateInfo infoItem;
    ArrayList<UpdateInfo> updateInfoArrayList;
    StringBuilder builder = new StringBuilder();
    //private String urlString = null;
    private InputStream stream;

    //private List<UpdateInfo> infolist = new ArrayList<>();;

    public HandleXML(ArrayList<UpdateInfo> updateInfoArrayList) {
        this.updateInfoArrayList = updateInfoArrayList;
    }

    public ArrayList<UpdateInfo> getParsedData() {
        Log.d("HandleXML", "@Awd::updateInfoArrayList: " + updateInfoArrayList.size());
        return updateInfoArrayList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equalsIgnoreCase("updates")) {
            infoItem = new UpdateInfo();
            updates = true;
        } else if (qName.equalsIgnoreCase("info")) {

            info = true;
        } else if (qName.equalsIgnoreCase("infoUpdate"))
            infoUpdate = true;
        else if (qName.equalsIgnoreCase("infoPage"))
            infoPage = true;
        else if (qName.equalsIgnoreCase("PageId"))
            PageId = true;
        else if (qName.equalsIgnoreCase("pageHeading"))
            pageHeading = true;
        else if (qName.equalsIgnoreCase("pageContent"))
            pageContent = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (updates) {
            infoItem.setUpdates(builder.toString());
//            System.out.println(infoItem.getUpdates());
            Log.d("HandleXML", "@Awd::infoItem Updates: " + infoItem.getUpdates());
            updateInfoArrayList.add(infoItem);
            updates = false;
        } else if (info) {
            infoItem.setInfo(builder.toString());
            Log.d("HandleXML", "@Awd::infoItem Info: " + infoItem.getInfo());
            updateInfoArrayList.add(infoItem);
            info = false;
        } else if (infoUpdate) {
            infoItem.setInfoUpdate(builder.toString());
            Log.d("HandleXML", "@Awd::infoItem info Updates: " + infoItem.getInfoUpdate());
            updateInfoArrayList.add(infoItem);
            infoUpdate = false;
        } else if (infoPage) {
            infoItem.setInfoPage(builder.toString());
            Log.d("HandleXML", "@Awd::infoItem Page: " + infoItem.getInfoPage());
            updateInfoArrayList.add(infoItem);
            infoPage = false;
        } else if (PageId) {
            infoItem.setPageId(builder.toString());
            Log.d("HandleXML", "@Awd::infoItem Page Id: " + infoItem.getPageId());
            updateInfoArrayList.add(infoItem);
            PageId = false;
        } else if (pageHeading) {
            infoItem.setPageHeading(builder.toString());
            Log.d("HandleXML", "@Awd::infoItem Page Heading: " + infoItem.getPageHeading());
            updateInfoArrayList.add(infoItem);
            pageHeading = false;
        } else if (pageContent) {
            infoItem.setPageContent(builder.toString());
            Log.d("HandleXML", "@Awd::infoItem Page Content: " + infoItem.getPageContent());
            updateInfoArrayList.add(infoItem);
            pageContent = false;
        }
        //infolist.add(infoItem);
//        Log.d("LIST SIZE ->", "" + infolist.size());
        // parseComplete = true;
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);

    }

   /* public void fetchXML(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();
    }

    private void parseAndStore(InputStream stream){

    }*/


}
