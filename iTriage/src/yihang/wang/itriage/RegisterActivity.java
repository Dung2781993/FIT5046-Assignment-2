package yihang.wang.itriage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private Spinner register_position_spinner;
	String userName;
	String password;
	String userFirstName;
	String userLastName;
	String userOrganization;
	String userPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		register_position_spinner = (Spinner) findViewById(R.id.register_position);
		ArrayList<String> positionInitial = new ArrayList<String>();
		positionInitial.add("Paramedics");
		positionInitial.add("Nurse");
		positionInitial.add("Physician");
		ArrayAdapter<String> positionDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, positionInitial);
		positionDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		register_position_spinner.setAdapter(positionDataAdapter);
	}

	public void register(View view) {
		userName = ((TextView) findViewById(R.id.register_userName)).getText()
				.toString();
		password = ((TextView) findViewById(R.id.register_password)).getText()
				.toString();
		userFirstName = ((TextView) findViewById(R.id.register_firstName))
				.getText().toString();
		userLastName = ((TextView) findViewById(R.id.register_lastName))
				.getText().toString();
		userOrganization = ((TextView) findViewById(R.id.register_organisation))
				.getText().toString();
		userPosition = ((Spinner) findViewById(R.id.register_position))
				.getSelectedItem().toString();
		new registerTask(this).execute(MainActivity.JSON_DOWNLOAD_SERVICE
				+ MainActivity.JSON_USER + "/count",
				MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_USER
						+ "/create");
	}

	private class registerTask extends AsyncTask<String, Void, String> {
		private ProgressDialog dialog;
		private RegisterActivity activity;

		public registerTask(RegisterActivity activity) {
			this.activity = activity;
			dialog = new ProgressDialog(activity);
		}

		protected void onPreExecute() {
			this.dialog.setMessage("Register... Please Wait!");
			this.dialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			String result = "";
			String count = "";
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
					count = count + s;
				}
				System.out.println("There Are " + count
						+ " Records In The Users Table!");
			} catch (Exception e) {
				System.out.println("Getting Count Failed!");
			}
			String userId = String.valueOf((Integer.parseInt(count) + 1));
			JSONObject userJson = new JSONObject();
			try {
				userJson.accumulate("userFirstName", userFirstName);
				userJson.accumulate("userId", userId);
				userJson.accumulate("userLastName", userLastName);
				userJson.accumulate("userName", userName);
				userJson.accumulate("userOrganization", userOrganization);
				userJson.accumulate("userPassword", password);
				userJson.accumulate("userPosition", userPosition);
			} catch (Exception e) {
				System.out.println("Converting User To JSON Failed!");
			}
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost request = new HttpPost(args[1]);
				String json = userJson.toString();
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
					result = result + s;
				}
				System.out.println("Welcome! Register Successfully!");
			} catch (Exception e) {
				System.out.println("Sorry, register Failed! Please Try Again!");
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result.equals("Fail")) {
				dialog.cancel();
				Toast.makeText(activity,
						"Sorry, Register Failed! Please Try Again!",
						Toast.LENGTH_SHORT).show();
				this.cancel(true);
			} else {
				dialog.cancel();
				Toast.makeText(activity, "Welcome! Register Successfully!",
						Toast.LENGTH_SHORT).show();
				this.cancel(true);
				Intent intent = new Intent();
				intent.setClass(activity, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}

	}

	public void cancel(View view) {
		Intent intent = new Intent();
		intent.setClass(RegisterActivity.this, MainActivity.class);
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
