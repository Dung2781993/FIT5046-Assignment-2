package yihang.wang.itriage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import yihang.wang.model.User;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String JSON_DOWNLOAD_SERVICE = "http://192.168.200.131:8080/FIT5046-Assignment2-Server/webresources/";
	public static final String JSON_USER = "com.itriageentity.users";
	public static final String JSON_PARIENT = "com.itriageentity.patients";
	public static final String JSON_GEOINFO = "com.itriageentity.geoinfo";
	public static final String JSON_SIEVE = "com.itriageentity.sieve";
	public static final String JSON_SORT = "com.itriageentity.sort";

	public static User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void login(View view) {
		String userName = ((TextView) findViewById(R.id.main_userName))
				.getText().toString();
		String password = ((TextView) findViewById(R.id.main_password))
				.getText().toString();
		if (userName.equals(""))
			Toast.makeText(this, "Username Shoule Not Be Blank!",
					Toast.LENGTH_SHORT).show();
		else if (password.equals(""))
			Toast.makeText(this, "Password Shoule Not Be Blank!",
					Toast.LENGTH_SHORT).show();
		else
			new loginTask(this).execute(JSON_DOWNLOAD_SERVICE + JSON_USER
					+ "/login/" + userName + "/" + password);
	}

	private class loginTask extends AsyncTask<String, Void, String> {
		private ProgressDialog dialog;
		private MainActivity activity;

		public loginTask(MainActivity activity) {
			this.activity = activity;
			dialog = new ProgressDialog(activity);
		}

		protected void onPreExecute() {
			this.dialog.setMessage("Login... Please Wait!");
			this.dialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			String result = "";
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
					result = result + s;
				}
				System.out.println("Welcome Back! Login Successfully!");
			} catch (Exception e) {
				System.out.println("Sorry, Login Failed! Please Try Again!");
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result.equals("")) {
				dialog.cancel();
				Toast.makeText(activity,
						"Sorry, Login Failed! Please Try Again!",
						Toast.LENGTH_SHORT).show();
				this.cancel(true);
			} else {
				dialog.cancel();
				Toast.makeText(activity, "Welcome Back! Login Successfully!",
						Toast.LENGTH_SHORT).show();
				this.cancel(true);
				try {
					JSONObject userJSON = new JSONObject(result);
					String userFirstName = userJSON.getString("userFirstName");
					String userId = userJSON.getString("userId");
					String userLastName = userJSON.getString("userLastName");
					String userName = userJSON.getString("userName");
					String userOrganization = userJSON.getString("userOrganization");
					String userPassword = userJSON.getString("userPassword");
					String userPosition = userJSON.getString("userPosition");
					user = new User(userFirstName, userId, userLastName, userName, userOrganization, userPassword, userPosition);
					Intent intent = new Intent();
					intent.setClass(activity, UserInfoActivity.class);
					startActivity(intent);
				} catch (JSONException e) {
					System.out.println("JSON Parse Failed!");
				}
				finish();
			}
		}
	}

	public void register(View view) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, RegisterActivity.class);
		startActivity(intent);
		finish();
	}

	public void exit(View view) {
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
