package yihang.wang.itriage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfo);
		TextView userId= (TextView) findViewById(R.id.userInfo_userId);
		userId.setText(MainActivity.user.getUserId());
		TextView userName= (TextView) findViewById(R.id.userInfo_userName);
		userName.setText(MainActivity.user.getUserName());
		TextView password= (TextView) findViewById(R.id.userInfo_password);
		password.setText(MainActivity.user.getUserPassword());
		TextView firstName= (TextView) findViewById(R.id.userInfo_firstName);
		firstName.setText(MainActivity.user.getUserFirstName());
		TextView lastName= (TextView) findViewById(R.id.userInfo_lastName);
		lastName.setText(MainActivity.user.getUserLastName());
		TextView organisation= (TextView) findViewById(R.id.userInfo_organisation);
		organisation.setText(MainActivity.user.getUserOrganization());
		TextView position= (TextView) findViewById(R.id.userInfo_position);
		position.setText(MainActivity.user.getUserPosition());
	}
	
	public void sieve(View view) {
		Intent intent = new Intent();
		intent.setClass(UserInfoActivity.this, SieveActivity.class);
		startActivity(intent);
		finish();
	}

	public void sort(View view) {
		Intent intent = new Intent();
		intent.setClass(UserInfoActivity.this, SortActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void search(View view) {
		Intent intent = new Intent();
		intent.setClass(UserInfoActivity.this, SearchActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void map(View view) {
		Intent intent = new Intent();
		intent.setClass(UserInfoActivity.this, MapActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void cancel(View view) {
		Intent intent = new Intent();
		intent.setClass(UserInfoActivity.this, MainActivity.class);
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