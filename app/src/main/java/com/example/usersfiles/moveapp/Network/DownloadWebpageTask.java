package com.example.usersfiles.moveapp.Network;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usersfiles.moveapp.JSON.Parsing_Json;
import com.example.usersfiles.moveapp.Models.MainData;
import com.example.usersfiles.moveapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by UsersFiles on 6/8/2016.
 */
public class DownloadWebpageTask extends AsyncTask<String, Void, String> {
    updateUI ui;
    Context con ;
    ArrayList<MainData>arrayList;
    private ProgressBar progressBar;

    public void setterui(updateUI ui  ){
        this.ui=ui;}
    public DownloadWebpageTask(Context context ){
        con=context;

        arrayList=new ArrayList<>();
    }
   // @Override
   // protected void onPreExecute() {
        //textView.setText("Hello !!!");
      //  progressBar = (ProgressBar) ((Activity)con).findViewById(R.id.progress);
     //   progressBar.setVisibility(View.VISIBLE);
       // progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);

    //    super.onPreExecute();
   // }
    @Override
    protected String doInBackground(String... urls) {

        // params comes from the execute() call: params[0] is the url.
        try {
            return downloadUrl(urls[0]);
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(final String result) {
        // textView.setText(result);
        ui.reciveddata(result);
       // Toast.makeText(con,result,Toast.LENGTH_LONG).show();
        Parsing_Json par =new   Parsing_Json(result);
  arrayList=par.getListOfMoves();
        //Toast.makeText(con,String.valueOf(arrayList.size()),Toast.LENGTH_LONG).show();

    }


    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 13171;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = convertStreamToString(is);


            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    public String readIt(InputStream stream, int len)
            throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
