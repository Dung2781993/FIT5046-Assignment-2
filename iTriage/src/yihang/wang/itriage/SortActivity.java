package yihang.wang.itriage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SortActivity extends Activity {
	private Spinner sort_eyeOpen_spinner;
	private Spinner sort_verbalResponse_spinner;
	private Spinner sort_motorResponse_spinner;
	private Spinner sort_respiratoryRate_spinner;
	private Spinner sort_bloodPressure_spinner;
	private Spinner sort_symptoms_spinner;
	private Spinner sort_injuries_spinner;
	private Spinner sort_geoInfo_spinner;
	private String eyeOpen;
	private String verbalResponse;
	private String motorResponse;
	private String glasgowComaScoreValue;
	private String respiratoryRate;
	private String bloodPressure;
	private String priority;
	private String symptoms;
	private String injuries;
	private String geoInfo;
	private String date;
	private String time;
	private String firstName;
	private String lastName;
	private String gcs;
	private String finalScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sort);
		sort_eyeOpen_spinner = (Spinner) findViewById(R.id.sort_eyeOpen);
		ArrayList<String> eyeOpenInitial = new ArrayList<String>();
		eyeOpenInitial.add("Spontaneous");
		eyeOpenInitial.add("To Voice");
		eyeOpenInitial.add("To Pain");
		eyeOpenInitial.add("None");
		ArrayAdapter<String> eyeOpenDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, eyeOpenInitial);
		eyeOpenDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sort_eyeOpen_spinner.setAdapter(eyeOpenDataAdapter);

		sort_verbalResponse_spinner = (Spinner) findViewById(R.id.sort_verbalResponse);
		ArrayList<String> verbalResponseInitial = new ArrayList<String>();
		verbalResponseInitial.add("Oriented");
		verbalResponseInitial.add("Confused");
		verbalResponseInitial.add("Inappropriate Words");
		verbalResponseInitial.add("Incomprehensible");
		verbalResponseInitial.add("None");
		ArrayAdapter<String> verbalResponseDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item,
				verbalResponseInitial);
		verbalResponseDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sort_verbalResponse_spinner.setAdapter(verbalResponseDataAdapter);

		sort_motorResponse_spinner = (Spinner) findViewById(R.id.sort_motorResponse);
		ArrayList<String> motorResponseInitial = new ArrayList<String>();
		motorResponseInitial.add("Obeys Commands");
		motorResponseInitial.add("Localises To Pain");
		motorResponseInitial.add("Withdraws To Pain");
		motorResponseInitial.add("Flexes To Pain");
		motorResponseInitial.add("Extends To Pain");
		motorResponseInitial.add("None");
		ArrayAdapter<String> motorResponseDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item,
				motorResponseInitial);
		motorResponseDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sort_motorResponse_spinner.setAdapter(motorResponseDataAdapter);

		sort_respiratoryRate_spinner = (Spinner) findViewById(R.id.sort_respiratoryRate);
		ArrayList<String> respiratoryRateInitial = new ArrayList<String>();
		respiratoryRateInitial.add("10-29");
		respiratoryRateInitial.add(">29");
		respiratoryRateInitial.add("6-9");
		respiratoryRateInitial.add("1-5");
		respiratoryRateInitial.add("0");
		ArrayAdapter<String> respiratoryRateDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item,
				respiratoryRateInitial);
		respiratoryRateDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sort_respiratoryRate_spinner.setAdapter(respiratoryRateDataAdapter);

		sort_bloodPressure_spinner = (Spinner) findViewById(R.id.sort_bloodPressure);
		ArrayList<String> bloodPressureInitial = new ArrayList<String>();
		bloodPressureInitial.add(">89");
		bloodPressureInitial.add("76-89");
		bloodPressureInitial.add("50-75");
		bloodPressureInitial.add("1-49");
		bloodPressureInitial.add("0");
		ArrayAdapter<String> bloodPressureDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item,
				bloodPressureInitial);
		bloodPressureDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sort_bloodPressure_spinner.setAdapter(bloodPressureDataAdapter);

		sort_symptoms_spinner = (Spinner) findViewById(R.id.sort_symptoms);
		ArrayList<String> symptomsInitial = new ArrayList<String>();
		symptomsInitial.add("Diarrhoea");
		symptomsInitial.add("Ferver");
		symptomsInitial.add("Headache");
		symptomsInitial.add("Joint/MuscleAche");
		symptomsInitial.add("Rash");
		symptomsInitial.add("Seizure");
		symptomsInitial.add("Unconsciousness");
		symptomsInitial.add("Vomiting");
		symptomsInitial.add("Not Classified");
		ArrayAdapter<String> symptomsDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, symptomsInitial);
		symptomsDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sort_symptoms_spinner.setAdapter(symptomsDataAdapter);

		sort_injuries_spinner = (Spinner) findViewById(R.id.sort_injuries);
		ArrayList<String> injuriesInitial = new ArrayList<String>();
		injuriesInitial.add("Bites");
		injuriesInitial.add("Burns");
		injuriesInitial.add("Cardiovascular Problems");
		injuriesInitial.add("Fracture/Sprain/Dislocation");
		injuriesInitial.add("Heart Related Condition");
		injuriesInitial.add("Hypothemia");
		injuriesInitial.add("Laceration");
		injuriesInitial.add("Wounds");
		injuriesInitial.add("Not Classified");
		ArrayAdapter<String> injuriesDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, injuriesInitial);
		injuriesDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sort_injuries_spinner.setAdapter(injuriesDataAdapter);

		sort_geoInfo_spinner = (Spinner) findViewById(R.id.sort_geoInfo);
		ArrayList<String> geoInfoInitial = new ArrayList<String>();
		geoInfoInitial.add("Monash Medical Centre Clayton");
		geoInfoInitial.add("Dandenong Hospital");
		geoInfoInitial.add("Caulfield Hospital");
		geoInfoInitial.add("Blackburn Road Medical Centre");
		geoInfoInitial.add("Knox Private Hospital");
		geoInfoInitial.add("Waverley Private Hospital");
		geoInfoInitial.add("Wells Road Medical Centre");
		geoInfoInitial.add("Lynbrook Village Medical Centre");
		geoInfoInitial.add("Sandringham Hospital");
		geoInfoInitial.add("Box Hill Hospital");
		ArrayAdapter<String> geoInfoDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, geoInfoInitial);
		geoInfoDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sort_geoInfo_spinner.setAdapter(geoInfoDataAdapter);
	}

	public void priority(View view) {
		switch (sort_eyeOpen_spinner.getSelectedItem().toString()) {
		case "Spontaneous": {
			eyeOpen = "4";
			break;
		}
		case "To Voice": {
			eyeOpen = "3";
			break;
		}
		case "To Pain": {
			eyeOpen = "2";
			break;
		}
		case "None": {
			eyeOpen = "1";
			break;
		}
		}

		switch (sort_verbalResponse_spinner.getSelectedItem().toString()) {
		case "Oriented": {
			verbalResponse = "5";
			break;
		}
		case "Confused": {
			verbalResponse = "4";
			break;
		}
		case "Inappropriate Words": {
			verbalResponse = "3";
			break;
		}
		case "Incomprehensible": {
			verbalResponse = "2";
			break;
		}
		case "None": {
			verbalResponse = "1";
			break;
		}
		}

		switch (sort_motorResponse_spinner.getSelectedItem().toString()) {
		case "Obeys Commands": {
			motorResponse = "6";
			break;
		}
		case "Localises To Pain": {
			motorResponse = "5";
			break;
		}
		case "Withdraws To Pain": {
			motorResponse = "4";
			break;
		}
		case "Flexes To Pain": {
			motorResponse = "3";
			break;
		}
		case "Extends To Pain": {
			motorResponse = "2";
			break;
		}
		case "None": {
			motorResponse = "1";
			break;
		}
		}
		int glasgowComaScore = Integer.parseInt(eyeOpen)
				+ Integer.parseInt(verbalResponse)
				+ Integer.parseInt(motorResponse);
		gcs = String.valueOf(glasgowComaScore);
		if (glasgowComaScore > 12 && glasgowComaScore < 16) {
			glasgowComaScoreValue = "4";
		} else if (glasgowComaScore > 8 && glasgowComaScore < 13) {
			glasgowComaScoreValue = "3";
		} else if (glasgowComaScore > 5 && glasgowComaScore < 9) {
			glasgowComaScoreValue = "2";
		} else if (glasgowComaScore > 3 && glasgowComaScore < 6) {
			glasgowComaScoreValue = "1";
		} else if (glasgowComaScore == 3) {
			glasgowComaScoreValue = "0";
		}

		switch (sort_respiratoryRate_spinner.getSelectedItem().toString()) {
		case "10-29": {
			respiratoryRate = "4";
			break;
		}
		case ">29": {
			respiratoryRate = "3";
			break;
		}
		case "6-9": {
			respiratoryRate = "2";
			break;
		}
		case "1-5": {
			respiratoryRate = "1";
			break;
		}
		case "0": {
			respiratoryRate = "0";
			break;
		}
		}

		switch (sort_bloodPressure_spinner.getSelectedItem().toString()) {
		case ">89": {
			bloodPressure = "4";
			break;
		}
		case "76-89": {
			bloodPressure = "3";
			break;
		}
		case "50-75": {
			bloodPressure = "2";
			break;
		}
		case "1-49": {
			bloodPressure = "1";
			break;
		}
		case "0": {
			bloodPressure = "0";
			break;
		}
		}
		int score = Integer.parseInt(glasgowComaScoreValue)
				+ Integer.parseInt(respiratoryRate)
				+ Integer.parseInt(bloodPressure);
		finalScore = String.valueOf(score);
		TextView priorityDecision = (TextView) findViewById(R.id.sort_priority);
		if (score == 12) {
			priorityDecision.setText("Delayed");
			priorityDecision.setBackgroundColor(Color.GREEN);
			priority = "3";
		} else if (score == 11) {
			priorityDecision.setText("Urgent");
			priorityDecision.setBackgroundColor(Color.YELLOW);
			priority = "2";
		} else {
			priorityDecision.setText("Immediate");
			priorityDecision.setBackgroundColor(Color.RED);
			priority = "1";
		}
	}
	
	public void save(View view) {
		firstName = ((TextView) findViewById(R.id.sort_firstName)).getText()
				.toString();
		lastName = ((TextView) findViewById(R.id.sort_lastName)).getText()
				.toString();
		if (firstName.equals("") || lastName.equals("")) {
			Toast.makeText(this, "Patient Info Shoule Not Be Blank!",
					Toast.LENGTH_SHORT).show();
		} else {

			priority(view);
			symptoms = ((Spinner) findViewById(R.id.sort_symptoms))
					.getSelectedItem().toString();
			injuries = ((Spinner) findViewById(R.id.sort_injuries))
					.getSelectedItem().toString();
			geoInfo = ((Spinner) findViewById(R.id.sort_geoInfo))
					.getSelectedItem().toString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			date = simpleDateFormat.format(new java.util.Date());
			SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm:ss");
			time = simpleTimeFormat.format(new java.util.Date());
			new saveTask(this).execute(
					MainActivity.JSON_DOWNLOAD_SERVICE
							+ MainActivity.JSON_PARIENT + "/count",
					MainActivity.JSON_DOWNLOAD_SERVICE
							+ MainActivity.JSON_PARIENT + "/create",
					MainActivity.JSON_DOWNLOAD_SERVICE
							+ MainActivity.JSON_GEOINFO + "/findGeoId/"
							+ geoInfo.replaceAll(" ", "%20"),
					MainActivity.JSON_DOWNLOAD_SERVICE
							+ MainActivity.JSON_SORT + "/count",
					MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_USER
							+ "/getJsonByUserId/"
							+ MainActivity.user.getUserId(),
					MainActivity.JSON_DOWNLOAD_SERVICE
							+ MainActivity.JSON_SORT + "/create");
		}
	}
	
	private class saveTask extends AsyncTask<String, Void, String> {
		private ProgressDialog dialog;
		private SortActivity activity;

		public saveTask(SortActivity activity) {
			this.activity = activity;
			dialog = new ProgressDialog(activity);
		}

		protected void onPreExecute() {
			this.dialog.setMessage("Save... Please Wait!");
			this.dialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			String result = "";
			String patientCount = "";
			String patientJsonInServer = "";
			String GeoJsonInServer = "";
			String sortCount = "";
			String userJsonInServer = "";
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
					patientCount = patientCount + s;
				}
				System.out.println("There Are " + patientCount
						+ " Records In The Patients Table!");
			} catch (Exception e) {
				System.out.println("Getting Count Failed!");
			}
			String patientIdPatient = String.valueOf((Integer
					.parseInt(patientCount) + 1));
			JSONObject patientJson = new JSONObject();
			try {
				patientJson.accumulate("patientFirstName", firstName);
				patientJson.accumulate("patientId", patientIdPatient);
				patientJson.accumulate("patientLastName", lastName);
			} catch (Exception e) {
				System.out.println("Converting User To JSON Failed!");
			}
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost request = new HttpPost(args[1]);
				String json = patientJson.toString();
				StringEntity se = new StringEntity(json);
				request.setEntity(se);
				request.setHeader("Accept", "application/json");
				request.setHeader("Content-type", "application/json");
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					patientJsonInServer = patientJsonInServer + s;
				}
				System.out.println("The PatientJsonInServer Is: "
						+ patientJsonInServer);
			} catch (Exception e) {
				System.out
						.println("Sorry, Create Patient Failed! Please Try Again!");
			}
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet request = new HttpGet(args[2]);
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					GeoJsonInServer = GeoJsonInServer + s;
				}
				System.out
						.println("The GeoJsonInServer Is: " + GeoJsonInServer);
			} catch (Exception e) {
				System.out.println("Getting GeoId Failed!");
			}
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet request = new HttpGet(args[3]);
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					sortCount = sortCount + s;
				}
				System.out.println("There Are " + patientCount
						+ " Records In The Sort Table!");
			} catch (Exception e) {
				System.out.println("Getting Count Failed!");
			}
			String sortId = String.valueOf((Integer.parseInt(sortCount) + 1));
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet request = new HttpGet(args[4]);
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					userJsonInServer = userJsonInServer + s;
				}
				System.out.println("The UserJsonInServer Is: "
						+ userJsonInServer);
			} catch (Exception e) {
				System.out.println("Getting GeoId Failed!");
			}
			JSONObject sortJson = new JSONObject();
			try {
				sortJson.accumulate("eyeOpen", eyeOpen);
				sortJson.accumulate("gcsValue", glasgowComaScoreValue);
				sortJson.accumulate("glasgowComaScore", gcs);
				sortJson.accumulate("injuries", injuries);
				sortJson.accumulate("motorResponse", motorResponse);
				sortJson.accumulate("priority", priority);
				sortJson.accumulate("respiratoryRate", respiratoryRate);
				sortJson.accumulate("score", finalScore);
				sortJson.accumulate("sortDate", date);
				sortJson.accumulate("sortId", sortId);
				sortJson.accumulate("sortTime", time);
				sortJson.accumulate("symptoms", symptoms);
				sortJson.accumulate("systilicBloodPresure", bloodPressure);
				sortJson.accumulate("verbalResponse", verbalResponse);
			} catch (Exception e) {
				System.out.println("Converting User To JSON Failed!");
			}
			String sortJsonString = (sortJson.toString()).substring(0,
					(sortJson.toString().length() - 1))
					+ ",\"geoId\":"
					+ GeoJsonInServer
					+ ",\"patientId\":"
					+ patientJsonInServer
					+ ",\"userId\":" + userJsonInServer + "}";
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost request = new HttpPost(args[5]);
				StringEntity se = new StringEntity(sortJsonString);
				request.setEntity(se);
				request.setHeader("Accept", "application/json");
				request.setHeader("Content-type", "application/json");
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					result = result + s;
				}
				System.out.println("Create Sort Successfully! ");
			} catch (Exception e) {
				System.out
						.println("Sorry, Create Sort Failed! Please Try Again!");
			}
			return result;
		}
		
		protected void onPostExecute(String result) {
			if (result.equals("Sucess")) {
				dialog.cancel();
				Toast.makeText(activity, "Sort Has Been Stored Successfully!",
						Toast.LENGTH_SHORT).show();
				this.cancel(true);
				Intent intent = new Intent();
				intent.setClass(activity, UserInfoActivity.class);
				startActivity(intent);
				finish();
			} else {
				dialog.cancel();
				Toast.makeText(activity,
						"Sorry, Store Sort Failed! Please Try Again!",
						Toast.LENGTH_SHORT).show();
				this.cancel(true);
			}
		}
	}

	public void cancel(View view) {
		Intent intent = new Intent();
		intent.setClass(SortActivity.this, UserInfoActivity.class);
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
