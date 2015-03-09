package com.example.jimmy.apijsontest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //*final TextView textView = (TextView)findViewById(R.id.responseTextView);
        Button thebutton = (Button)findViewById(R.id.buttonx);

        thebutton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.example.android.camera2basic");
                        startActivity(LaunchIntent);
                        //*textView.setText("rewer");

                    }
                }
        );





        this.responseTextView = (TextView) this.findViewById(R.id.responseTextView);

        new GetAllCustomerTask().execute(new ApiConnector());
    }

    public void setTextToTextView(JSONArray jsonArray)
    {
        String s ="";
        for(int i=0; i<jsonArray.length(); i++){
            JSONObject json = null;
            try {
                json = jsonArray.getJSONObject(i);
                s = s +
                        "partone : " + json.getString("partone") + "\n" +
                        "parttwo : " + json.getString("parttwo") + "\n\n";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.responseTextView.setText(s);
    }

    private class GetAllCustomerTask extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            return params[0].GetAllCustomers();
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            setTextToTextView(jsonArray);
        }

    }


}
