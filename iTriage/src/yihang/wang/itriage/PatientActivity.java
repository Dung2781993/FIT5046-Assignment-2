package yihang.wang.itriage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import yihang.wang.model.Sieve;
import yihang.wang.model.Sort;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class PatientActivity extends Activity {
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient);
		String para = getIntent().getStringExtra("Id");
		String a[] = para.split(" ");
		type = a[0];
		String id = a[2];
		new downloadPatientTask().execute(type,
				MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_SIEVE
						+ "/findBySieveId/" + id,
				MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_SORT
						+ "/findBySortId/" + id);
		TextView typeTextView = (TextView) findViewById(R.id.patientInfo_type);
		typeTextView.setText(type);
	}

	private class downloadPatientTask extends AsyncTask<String, Void, String> {
		String jsonResult = "";

		@Override
		protected String doInBackground(String... args) {
			String Result = args[0];
			String url = "";
			if (args[0].equals("Sieve")) {
				url = args[1];
			} else {
				url = args[2];
			}
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet request = new HttpGet(url);
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					jsonResult = jsonResult + s;
				}
				// System.out.println("Json Result Is: " + jsonResult);
			} catch (Exception e) {
				System.out
						.println("Sorry, Download JSON Failed! Please Try Again!");
			}
			return Result;
		}

		protected void onPostExecute(String result) {
			String patientLastName = "";
			String patientFirstName = "";
			String patientId = "";
			String rescuerInfo = "";
			String geoInfo = "";
			String priority = "";
			String symptoms = "";
			String injuries = "";
			String date = "";
			String time = "";

			if (result.equals("Sieve")) {
				try {
					JSONObject SieveJSON = new JSONObject(jsonResult);
					JSONObject patientInfoJSON = SieveJSON
							.getJSONObject("patientId");
					patientLastName = patientInfoJSON
							.getString("patientLastName");
					patientFirstName = patientInfoJSON
							.getString("patientFirstName");
					patientId = patientInfoJSON.getString("patientId");
					JSONObject rescuerInfoJSON = SieveJSON
							.getJSONObject("userId");
					rescuerInfo = rescuerInfoJSON.getString("userFirstName")
							+ " " + rescuerInfoJSON.getString("userLastName");
					JSONObject geoInfoJSON = SieveJSON.getJSONObject("geoId");
					geoInfo = geoInfoJSON.getString("geoAddress");
					priority = SieveJSON.getString("priority");
					symptoms = SieveJSON.getString("symptoms");
					injuries = SieveJSON.getString("injuries");
					date = SieveJSON.getString("sieveDate");
					time = SieveJSON.getString("sieveTime");
				} catch (Exception e) {
					System.out.println("JSON Parse Failed!");
				}
			} else {
				try {
					JSONObject SortJSON = new JSONObject(jsonResult);
					JSONObject patientInfoJSON = SortJSON
							.getJSONObject("patientId");
					patientLastName = patientInfoJSON
							.getString("patientLastName");
					patientFirstName = patientInfoJSON
							.getString("patientFirstName");
					patientId = patientInfoJSON.getString("patientId");
					JSONObject rescuerInfoJSON = SortJSON
							.getJSONObject("userId");
					rescuerInfo = rescuerInfoJSON.getString("userFirstName")
							+ " " + rescuerInfoJSON.getString("userLastName");
					JSONObject geoInfoJSON = SortJSON.getJSONObject("geoId");
					geoInfo = geoInfoJSON.getString("geoAddress");
					priority = SortJSON.getString("priority");
					symptoms = SortJSON.getString("symptoms");
					injuries = SortJSON.getString("injuries");
					date = SortJSON.getString("sortDate");
					time = SortJSON.getString("sortTime");
				} catch (Exception e) {
					System.out.println("JSON Parse Failed!");
				}
			}
			TextView patientIdTextView = (TextView) findViewById(R.id.patientInfo_patientId);
			patientIdTextView.setText(patientId);
			TextView patientFirstNameTextView = (TextView) findViewById(R.id.patientInfo_firstName);
			patientFirstNameTextView.setText(patientFirstName);
			TextView patientLastNameTextView = (TextView) findViewById(R.id.patientInfo_lastName);
			patientLastNameTextView.setText(patientLastName);
			TextView rescuerTextView = (TextView) findViewById(R.id.patientInfo_rescuerInfo);
			rescuerTextView.setText(rescuerInfo);
			TextView geoTextView = (TextView) findViewById(R.id.patientInfo_geoInfo);
			geoTextView.setText(geoInfo);
			TextView priorityTextView = (TextView) findViewById(R.id.patientInfo_priority);
			if (priority.equals("1")) {
				priorityTextView.setText("Immediate");
				priorityTextView.setBackgroundColor(Color.RED);
			} else if (priority.equals("2")) {
				priorityTextView.setText("Urgent");
				priorityTextView.setBackgroundColor(Color.YELLOW);
			} else if (priority.equals("3")) {
				priorityTextView.setText("Delayed");
				priorityTextView.setBackgroundColor(Color.GREEN);
			}

			TextView symptomsTextView = (TextView) findViewById(R.id.patientInfo_symptoms);
			symptomsTextView.setText(symptoms);
			TextView injuriesTextView = (TextView) findViewById(R.id.patientInfo_injuries);
			injuriesTextView.setText(injuries);
			TextView dateTextView = (TextView) findViewById(R.id.patientInfo_date);
			dateTextView.setText(date);
			TextView timeTextView = (TextView) findViewById(R.id.patientInfo_time);
			timeTextView.setText(time);
		}
	}

	public void cancel(View view) {
		Intent intent = new Intent();
		intent.setClass(PatientActivity.this, UserInfoActivity.class);
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
