package com.android.cebletandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.android.cebletandroid.R.id.contact;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    //old url: https://api.androidhive.info/contacts/
    //http://api.myjson.com/bins/ckzzt
    // URL to get contacts JSON
    private static String url;//"http://api.myjson.com/bins/ckzzt";//*"https://api.github.com/users/hadley/orgs";//*/"http://api.androidhive.info/contacts/";

    ArrayList<HashMap<String, String>> contactList;
    ArrayList<String> description;

    //ids
    private String ARRAY_NAME = "jobs";
    private String FIRST = "id";
    private String SECOND = "title";
    private String THIRD = "email";
    private String FOURTH = "location";
    private String FIFTH = "description";
    private String SIXTH = "experiance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialise
        description = new ArrayList<String>();

        // to read url from config file
        url = ConfigReader.getConfigValues(this, "url");

        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new GetData().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                String des = description.get(pos);
                Intent i = new Intent(MainActivity.this, JobDescription.class).putExtra("description",des);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    Log.e(TAG, jsonStr);
                    // Getting JSON Array node
                    //JSONArray contacts = jsonObj.getJSONArray("contacts");
                    JSONArray contacts = jsonObj.getJSONArray(ARRAY_NAME);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        /*String id = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String address = c.getString("address");
                        String gender = c.getString("gender");*/

                        String id = c.getString(FIRST);
                        String name = c.getString(SECOND);
                        String email = c.getString(THIRD);
                        String address = c.getString(FOURTH);
                        String desc = c.getString(FIFTH);
                        description.add(desc);

                        // Phone node is JSON Object
                       /* JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");
                            */
                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                       /* contact.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);*/
                        contact.put(FIRST, id);
                        contact.put(SECOND, name);
                        contact.put(THIRD, email);
                        contact.put(FOURTH, address);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_row, new String[]{/*"name", "email",
                    "mobile"*/ SECOND, THIRD, FOURTH}, new int[]{R.id.jobTitle,
                    R.id.email, contact});

            lv.setAdapter(adapter);
        }

    }
}
