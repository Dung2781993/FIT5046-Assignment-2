package yihang.wang.itriage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchResultActivity extends Activity {
	private String url1;
	private String url2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		String a[] = getIntent().getStringExtra("parameters").split(",");
		if (a[0].equals("I")) {
			url1 = MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_SIEVE
					+ "/searchI/" + a[1] + "/" + a[2];
			url2 = MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_SORT
					+ "/searchI/" + a[1] + "/" + a[2];
			new searchISieveTask().execute(url1);
			new searchISortTask().execute(url2);
		} else {
			url1 = MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_SIEVE
					+ "/searchII/" + a[1] + "/" + a[2] + "/" + a[3].replaceAll(" ", "%20");
			url2 = MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_SORT
					+ "/searchII/" + a[1] + "/" + a[2] + "/" + a[3].replaceAll(" ", "%20");
			System.out.println(url1);
			new searchISieveTask().execute(url1);
			new searchISortTask().execute(url2);
		}
	}

	private class searchISieveTask extends AsyncTask<String, Void, String> {

		protected String doInBackground(String... args) {
			String Result = "";
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet request = new HttpGet(args[0]);
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					Result = Result + s;
				}
				System.out.println("Json Result Is: " + Result);
			} catch (Exception e) {
				System.out
						.println("Sorry, Download JSON Failed! Please Try Again!");
			}
			return Result;
		}

		protected void onPostExecute(String result) {
			String geoAddress;
			String userFirstName;
			String userLastName;
			String patientLastName;
			String patientFirstName;
			String sieveId;
			String symptoms;
			String injuries;
			String priority;
			String output = "";
			if (result.equals("[]")) {
				output = "Sorry, There Is No Matched Sieve Records In The Server! Please Try Again!";
			} else {
				try {
					JSONArray sieveJSON = new JSONArray(result);
					for (int i = 0; i < sieveJSON.length(); i++) {
						JSONObject singleSieveJSON = sieveJSON.getJSONObject(i);
						sieveId = singleSieveJSON.getString("sieveId");
						JSONObject patientInfoJSON = singleSieveJSON
								.getJSONObject("patientId");
						patientLastName = patientInfoJSON
								.getString("patientLastName");
						patientFirstName = patientInfoJSON
								.getString("patientFirstName");
						symptoms = singleSieveJSON.getString("symptoms");
						injuries = singleSieveJSON.getString("injuries");
						priority = singleSieveJSON.getString("priority");
						JSONObject userInfoJSON = singleSieveJSON
								.getJSONObject("userId");
						userFirstName = userInfoJSON.getString("userFirstName");
						userLastName = userInfoJSON.getString("userLastName");
						JSONObject geoInfoJSON = singleSieveJSON
								.getJSONObject("geoId");
						geoAddress = geoInfoJSON.getString("geoAddress");
						output = output + "Sieve ID: " + sieveId
								+ "\nPatient Info: " + patientLastName + " "
								+ patientFirstName + "\nSymptoms: " + symptoms
								+ "\nInjuries: " + injuries + "\nPriority: "
								+ priority + "\nRescuer Info: " + userLastName
								+ " " + userFirstName + "\nGeo Info: "
								+ geoAddress + "\n\n";
					}
				} catch (Exception e) {
					System.out.println("JSON Parse Failed!");
				}
			}
			TextView sieveResult = (TextView) findViewById(R.id.searchResult_Sieve);
			sieveResult.setText(output);
		}
	}

	private class searchISortTask extends AsyncTask<String, Void, String> {

		protected String doInBackground(String... args) {
			String Result = "";
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet request = new HttpGet(args[0]);
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					Result = Result + s;
				}
				System.out.println("Json Result Is: " + Result);
			} catch (Exception e) {
				System.out
						.println("Sorry, Download JSON Failed! Please Try Again!");
			}
			return Result;
		}

		protected void onPostExecute(String result) {
			String geoAddress;
			String userFirstName;
			String userLastName;
			String patientLastName;
			String patientFirstName;
			String sieveId;
			String symptoms;
			String injuries;
			String priority;
			String output = "";
			if (result.equals("[]")) {
				output = "Sorry, There Is No Matched Sort Records In The Server! Please Try Again!";
			} else {
				try {
					JSONArray sortJSON = new JSONArray(result);
					for (int i = 0; i < sortJSON.length(); i++) {
						JSONObject singleSortJSON = sortJSON.getJSONObject(i);
						sieveId = singleSortJSON.getString("sortId");
						JSONObject patientInfoJSON = singleSortJSON
								.getJSONObject("patientId");
						patientLastName = patientInfoJSON
								.getString("patientLastName");
						patientFirstName = patientInfoJSON
								.getString("patientFirstName");
						symptoms = singleSortJSON.getString("symptoms");
						injuries = singleSortJSON.getString("injuries");
						priority = singleSortJSON.getString("priority");
						JSONObject userInfoJSON = singleSortJSON
								.getJSONObject("userId");
						userFirstName = userInfoJSON.getString("userFirstName");
						userLastName = userInfoJSON.getString("userLastName");
						JSONObject geoInfoJSON = singleSortJSON
								.getJSONObject("geoId");
						geoAddress = geoInfoJSON.getString("geoAddress");
						output = output + "Sort ID: " + sieveId
								+ "\nPatient Info: " + patientLastName + " "
								+ patientFirstName + "\nSymptoms: " + symptoms
								+ "\nInjuries: " + injuries + "\nPriority: "
								+ priority + "\nRescuer Info: " + userLastName
								+ " " + userFirstName + "\nGeo Info: "
								+ geoAddress + "\n\n";
					}
				} catch (Exception e) {
					System.out.println("JSON Parse Failed!");
				}
			}
			TextView sortResult = (TextView) findViewById(R.id.searchResult_Sort);
			sortResult.setText(output);
		}
	}
	
	public void cancel(View view) {
		Intent intent = new Intent();
		intent.setClass(SearchResultActivity.this, UserInfoActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
