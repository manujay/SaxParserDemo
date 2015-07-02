package com.example.sonitek.saxparserdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends Activity {

    //private List<UpdateInfo> data;
    private String urlString = "http://imenu.offshoreoutsourcecompany.com/chkUpdate.php?action=infoPages&license=0007VR9M&vCode=eqgzahxk&lang=en&pageName=powered_by&ldate";

    private TextView textView;
    private Button button;
    private HandleXML obj;
    private ArrayList<UpdateInfo> parsedData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsedData = new ArrayList<UpdateInfo>();
                obj = new HandleXML(parsedData);
                GetXMLThread task = new GetXMLThread(obj,parsedData);
                task.execute();
                //Log.d("HandleXML", "@Awd::SIZE: " +task.infoArrayList.size());
            }
        });
    }
    /**
     * Created by sonitek on 7/2/2015.
     */
    private class GetXMLThread extends AsyncTask<Void,Void,ArrayList<UpdateInfo>> {

        ArrayList<UpdateInfo> infoArrayList;
        HandleXML handleXML;

        public GetXMLThread(HandleXML obj,ArrayList<UpdateInfo> arrayList) {
            this.infoArrayList = arrayList;
            this.handleXML = obj;
        }

        @Override
        protected ArrayList<UpdateInfo> doInBackground(Void... params) {

            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream stream =  conn.getInputStream();
                conn.setReadTimeout(1000);
                conn.setConnectTimeout(1500);
                conn.setRequestMethod("GET");
                //conn.setDoInput(true);
                conn.connect();
                Log.d("Http Response ->", "" + conn.getResponseMessage());
                SAXParserFactory factory = SAXParserFactory.newInstance();
                try {
                    SAXParser parser = factory.newSAXParser();
                    parser.parse(stream,obj);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<UpdateInfo> arrayList) {
            super.onPostExecute(arrayList);

             this.infoArrayList = arrayList;
             infoArrayList = handleXML.getParsedData();
            Log.d("HandleXML", "@Awd::SIZE: " +infoArrayList.size());
            textView = (TextView)findViewById(R.id.textview);
            for(int i=0;i < infoArrayList.size();i++){
               UpdateInfo item = infoArrayList.get(i);

                textView.setText(new StringBuilder().append(item.getUpdates()).append("\n--->")
                        .append(item.getInfo())
                        .append(item.getInfoUpdate())
                        .append(item.getInfoPage())
                        .append(item.getPageId())
                        .append(item.getPageHeading())
                        .append("\n-------------------->")
                        .append(item.getPageContent()).toString());
            }

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
