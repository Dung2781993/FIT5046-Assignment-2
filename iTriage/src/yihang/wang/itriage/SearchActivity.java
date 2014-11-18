package yihang.wang.itriage;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {
	private Spinner search_symptoms_spinner;
	private Spinner search_injuries_spinner;
	private Spinner search_geoInfo_spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		search_symptoms_spinner = (Spinner) findViewById(R.id.search_symptoms);
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
		search_symptoms_spinner.setAdapter(symptomsDataAdapter);

		search_injuries_spinner = (Spinner) findViewById(R.id.search_injuries);
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
		search_injuries_spinner.setAdapter(injuriesDataAdapter);

		search_geoInfo_spinner = (Spinner) findViewById(R.id.search_geoInfo);
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
		search_geoInfo_spinner.setAdapter(geoInfoDataAdapter);
	}

	public void searchI(View view) {
		String firstName = ((TextView) findViewById(R.id.search_firstName))
				.getText().toString();
		String lastName = ((TextView) findViewById(R.id.search_lastName))
				.getText().toString();
		if (firstName.equals("") || lastName.equals("")) {
			Toast.makeText(this, "Search Words Shoule Not Be Blank!",
					Toast.LENGTH_SHORT).show();
		} else {
			Intent intent = new Intent();
			intent.setClass(SearchActivity.this, SearchResultActivity.class);
			intent.putExtra("parameters", "I," + firstName + "," + lastName);
			startActivity(intent);
			finish();
		}
	}
	
	public void cancel(View view) {
		Intent intent = new Intent();
		intent.setClass(SearchActivity.this, UserInfoActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void searchII(View view) {
		String symptoms = ((Spinner) findViewById(R.id.search_symptoms))
				.getSelectedItem().toString();
		String injuries = ((Spinner) findViewById(R.id.search_injuries))
				.getSelectedItem().toString();
		String geoInfo = ((Spinner) findViewById(R.id.search_geoInfo))
				.getSelectedItem().toString();
			Intent intent = new Intent();
			intent.setClass(SearchActivity.this, SearchResultActivity.class);
			intent.putExtra("parameters", "II," + symptoms + "," + injuries + "," + geoInfo);
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
