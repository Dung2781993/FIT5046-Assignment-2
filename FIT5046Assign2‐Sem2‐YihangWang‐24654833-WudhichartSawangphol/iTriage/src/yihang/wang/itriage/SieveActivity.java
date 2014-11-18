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
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SieveActivity extends Activity {
	RadioGroup walkingRadio;
	RadioGroup breathingRadio;
	RadioGroup breathingRateRadio;
	TextView priorityDecision;
	private Spinner sieve_symptoms_spinner;
	private Spinner sieve_injuries_spinner;
	private Spinner sieve_geoInfo_spinner;
	private String walking;
	private String breathing;
	private String AOBreathing;
	private String breathingRate;
	private String circulation;
	private String priority;
	private String symptoms;
	private String injuries;
	private String geoInfo;
	private String date;
	private String time;
	private String firstName;
	private String lastName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sieve);
		walkingRadio = (RadioGroup) findViewById(R.id.sieve_walking);
		walkingRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.sieve_walkingYes) {
					LinearLayout breathingLO = (LinearLayout) findViewById(R.id.sieve_breathingLayout);
					breathingLO.setVisibility(View.GONE);
				} else {
					LinearLayout breathingLO = (LinearLayout) findViewById(R.id.sieve_breathingLayout);
					breathingLO.setVisibility(View.VISIBLE);
				}
			}
		});
		breathingRadio = (RadioGroup) findViewById(R.id.sieve_breathing);
		breathingRadio
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == R.id.sieve_breathingYes) {
							LinearLayout AOBreathingLO = (LinearLayout) findViewById(R.id.sieve_AOBreathingLayout);
							AOBreathingLO.setVisibility(View.GONE);
							LinearLayout BreathingRateLO = (LinearLayout) findViewById(R.id.sieve_BreathingRateLayout);
							BreathingRateLO.setVisibility(View.VISIBLE);
						} else {
							LinearLayout AOBreathingLO = (LinearLayout) findViewById(R.id.sieve_AOBreathingLayout);
							AOBreathingLO.setVisibility(View.VISIBLE);
							LinearLayout BreathingRateLO = (LinearLayout) findViewById(R.id.sieve_BreathingRateLayout);
							BreathingRateLO.setVisibility(View.GONE);
						}
					}
				});
		breathingRateRadio = (RadioGroup) findViewById(R.id.sieve_BreathingRate);
		breathingRateRadio
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == R.id.sieve_BreathingRateYes) {
							LinearLayout circulationLO = (LinearLayout) findViewById(R.id.sieve_circulationLayout);
							circulationLO.setVisibility(View.VISIBLE);
						} else {
							LinearLayout circulationLO = (LinearLayout) findViewById(R.id.sieve_circulationLayout);
							circulationLO.setVisibility(View.GONE);
						}
					}
				});

		sieve_symptoms_spinner = (Spinner) findViewById(R.id.sieve_symptoms);
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
		sieve_symptoms_spinner.setAdapter(symptomsDataAdapter);

		sieve_injuries_spinner = (Spinner) findViewById(R.id.sieve_injuries);
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
		sieve_injuries_spinner.setAdapter(injuriesDataAdapter);

		sieve_geoInfo_spinner = (Spinner) findViewById(R.id.sieve_geoInfo);
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
		sieve_geoInfo_spinner.setAdapter(geoInfoDataAdapter);
	}

	public void priority(View view) {
		TextView priorityDecision = (TextView) findViewById(R.id.sieve_priority);
		if (walkingRadio.getCheckedRadioButtonId() == R.id.sieve_walkingYes) {
			priorityDecision.setText("Delayed");
			priorityDecision.setBackgroundColor(Color.GREEN);
			walking = "YES";
			breathing = "";
			AOBreathing = "";
			breathingRate = "";
			circulation = "";
			priority = "3";
		} else {
			walking = "NO";
			if (breathingRadio.getCheckedRadioButtonId() == R.id.sieve_breathingNo) {
				breathing = "NO";
				if (((RadioGroup) findViewById(R.id.sieve_AOBreathing))
						.getCheckedRadioButtonId() == R.id.sieve_AOBreathingNo) {
					priorityDecision.setText("Dead");
					priorityDecision.setBackgroundColor(Color.WHITE);
					AOBreathing = "NO";
					breathingRate = "";
					circulation = "";
					priority = "4";
				} else {
					priorityDecision.setText("Immediate");
					priorityDecision.setBackgroundColor(Color.RED);
					AOBreathing = "YES";
					breathingRate = "";
					circulation = "";
					priority = "1";
				}
			} else {
				breathing = "YES";
				AOBreathing = "";
				if (breathingRateRadio.getCheckedRadioButtonId() == R.id.sieve_BreathingRateNo) {
					priorityDecision.setText("Immediate");
					priorityDecision.setBackgroundColor(Color.RED);
					breathingRate = "<10 Or >29";
					circulation = "";
					priority = "1";
				} else {
					breathingRate = "10-29";
					if (((RadioGroup) findViewById(R.id.sieve_circulation))
							.getCheckedRadioButtonId() == R.id.sieve_circulationYes) {
						priorityDecision.setText("Immediate");
						priorityDecision.setBackgroundColor(Color.RED);
						circulation = ">120";
						priority = "1";
					} else {
						priorityDecision.setText("Urgent");
						priorityDecision.setBackgroundColor(Color.YELLOW);
						circulation = "<120 Or =120";
						priority = "2";
					}
				}
			}
		}

	}

	public void save(View view) {
		firstName = ((TextView) findViewById(R.id.sieve_firstName)).getText()
				.toString();
		lastName = ((TextView) findViewById(R.id.sieve_lastName)).getText()
				.toString();
		if (firstName.equals("") || lastName.equals("")) {
			Toast.makeText(this, "Patient Info Shoule Not Be Blank!",
					Toast.LENGTH_SHORT).show();
		} else {

			priority(view);
			symptoms = ((Spinner) findViewById(R.id.sieve_symptoms))
					.getSelectedItem().toString();
			injuries = ((Spinner) findViewById(R.id.sieve_injuries))
					.getSelectedItem().toString();
			geoInfo = ((Spinner) findViewById(R.id.sieve_geoInfo))
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
							+ MainActivity.JSON_SIEVE + "/count",
					MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_USER
							+ "/getJsonByUserId/"
							+ MainActivity.user.getUserId(),
					MainActivity.JSON_DOWNLOAD_SERVICE
							+ MainActivity.JSON_SIEVE + "/create");
		}
	}

	private class saveTask extends AsyncTask<String, Void, String> {
		private ProgressDialog dialog;
		private SieveActivity activity;

		public saveTask(SieveActivity activity) {
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
			String sieveCount = "";
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
					sieveCount = sieveCount + s;
				}
				System.out.println("There Are " + patientCount
						+ " Records In The Sieve Table!");
			} catch (Exception e) {
				System.out.println("Getting Count Failed!");
			}
			String sieveId = String.valueOf((Integer.parseInt(sieveCount) + 1));
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
			JSONObject sieveJson = new JSONObject();
			try {
				sieveJson.accumulate("AOBreathing", AOBreathing);
				sieveJson.accumulate("breathStatus", breathing);
				sieveJson.accumulate("patientLastName", lastName);
				sieveJson.accumulate("injuries", injuries);
				sieveJson.accumulate("priority", priority);
				sieveJson.accumulate("pulseRate", circulation);
				sieveJson.accumulate("respiratoryRate", breathingRate);
				sieveJson.accumulate("sieveDate", date);
				sieveJson.accumulate("sieveId", sieveId);
				sieveJson.accumulate("sieveTime", time);
				sieveJson.accumulate("symptoms", symptoms);
				sieveJson.accumulate("walkStatus", walking);
			} catch (Exception e) {
				System.out.println("Converting User To JSON Failed!");
			}
			String sieveJsonString = (sieveJson.toString()).substring(0,
					(sieveJson.toString().length() - 1))
					+ ",\"geoId\":"
					+ GeoJsonInServer
					+ ",\"patientId\":"
					+ patientJsonInServer
					+ ",\"userId\":" + userJsonInServer + "}";
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost request = new HttpPost(args[5]);
				StringEntity se = new StringEntity(sieveJsonString);
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
				System.out.println("Create Sieve Successfully! ");
			} catch (Exception e) {
				System.out
						.println("Sorry, Create Sieve Failed! Please Try Again!");
			}
			return result;
		}

		protected void onPostExecute(String result) {
			if (result.equals("Sucess")) {
				dialog.cancel();
				Toast.makeText(activity, "Sieve Has Been Stored Successfully!",
						Toast.LENGTH_SHORT).show();
				this.cancel(true);
				Intent intent = new Intent();
				intent.setClass(activity, UserInfoActivity.class);
				startActivity(intent);
				finish();
			} else {
				dialog.cancel();
				Toast.makeText(activity,
						"Sorry, Store Sieve Failed! Please Try Again!",
						Toast.LENGTH_SHORT).show();
				this.cancel(true);
			}
		}
	}

	public void cancel(View view) {
		Intent intent = new Intent();
		intent.setClass(SieveActivity.this, UserInfoActivity.class);
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
