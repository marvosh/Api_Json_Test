package com.example.jimmy.apijsontest;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Jimmy on 3/8/2015.
 */
public class ApiConnector
{
    public JSONArray GetAllCustomers()
    {
        String bib = "dope";
        String url = "http://192.168.0.28:8080/index.php?tull="+bib;

        HttpEntity httpEntity = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
            } catch (ClientProtocolException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            }

        JSONArray jsonArray = null;
        if(httpEntity != null){
            try {
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("Entity Response : ", entityResponse);
                jsonArray = new JSONArray(entityResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;
    }

}
