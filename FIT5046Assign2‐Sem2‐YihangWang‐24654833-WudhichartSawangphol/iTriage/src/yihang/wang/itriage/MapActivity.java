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

import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.DefaultItemizedOverlay;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.ItemizedOverlay;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.OverlayItem;

import yihang.wang.model.GeoInfo;
import yihang.wang.model.Patient;
import yihang.wang.model.Sieve;
import yihang.wang.model.Sort;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		new downloadMessageTask().execute(MainActivity.JSON_DOWNLOAD_SERVICE
				+ MainActivity.JSON_SIEVE + "/getJsonByUserId/"
				+ MainActivity.user.getUserId(),
				MainActivity.JSON_DOWNLOAD_SERVICE + MainActivity.JSON_SORT
						+ "/getJsonByUserId/" + MainActivity.user.getUserId());
	}

	private class downloadMessageTask extends AsyncTask<String, Void, String> {
		ArrayList<Sieve> sieveList = new ArrayList<Sieve>();
		ArrayList<Sort> sortList = new ArrayList<Sort>();
		ArrayList<GeoInfo> geoInfoList = new ArrayList<GeoInfo>();
		AnnotationView annotation;

		@Override
		protected String doInBackground(String... args) {
			String sieveJsonResult = "";
			String sortJsonResult = "";
			String Result = "Success";
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
					sieveJsonResult = sieveJsonResult + s;
				}
				// System.out.println("Sieve Json Is: " + Result);
			} catch (Exception e) {
				Result = "Fail";
				System.out
						.println("Sorry, Download JSON Failed! Please Try Again!");
			}
			try {
				JSONArray sieveJSON = new JSONArray(sieveJsonResult);
				for (int i = 0; i < sieveJSON.length(); i++) {
					JSONObject singleSieveJSON = sieveJSON.getJSONObject(i);
					JSONObject geoInfoJSON = singleSieveJSON
							.getJSONObject("geoId");
					String geoId = geoInfoJSON.getString("geoId");
					String longtitude = geoInfoJSON.getString("longtitude");
					String geoAddress = geoInfoJSON.getString("geoAddress");
					String latitude = geoInfoJSON.getString("latitude");
					boolean flag = true;
					GeoInfo geoInfo = new GeoInfo(geoId, geoAddress, latitude,
							longtitude);
					for (int j = 0; j < geoInfoList.size(); j++) {
						if (geoInfoList.get(j).getGeoAddress()
								.equals(geoAddress)) {
							geoInfoList.get(j).setCount();
							flag = false;
							break;
						}
					}
					if (flag) {
						geoInfoList.add(geoInfo);
					}
					JSONObject patientInfoJSON = singleSieveJSON
							.getJSONObject("patientId");
					String patientLastName = patientInfoJSON
							.getString("patientLastName");
					String patientFirstName = patientInfoJSON
							.getString("patientFirstName");
					String patientId = patientInfoJSON.getString("patientId");
					Patient patient = new Patient(patientFirstName, patientId,
							patientLastName);
					String sieveId = singleSieveJSON.getString("sieveId");
					String walkStatus = singleSieveJSON.getString("walkStatus");
					String breathStatus = singleSieveJSON
							.getString("breathStatus");
					String aOBreathing = singleSieveJSON
							.getString("AOBreathing");
					String respiratoryRate = singleSieveJSON
							.getString("respiratoryRate");
					String pulseRate = singleSieveJSON.getString("pulseRate");
					String symptoms = singleSieveJSON.getString("symptoms");
					String injuries = singleSieveJSON.getString("injuries");
					String priority = singleSieveJSON.getString("priority");
					String sieveDate = singleSieveJSON.getString("sieveDate");
					String sieveTime = singleSieveJSON.getString("sieveTime");
					sieveList.add(new Sieve(sieveId, walkStatus, breathStatus,
							aOBreathing, respiratoryRate, pulseRate, symptoms,
							injuries, priority, sieveDate, sieveTime,
							MainActivity.user, patient, geoInfo));
				}
			} catch (Exception e) {
				Result = "Fail";
				System.out.println("JSON Parse Failed!");
			}
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet request = new HttpGet(args[1]);
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					sortJsonResult = sortJsonResult + s;
				}
				// System.out.println("Sort Json Is: " + sortJsonResult);
			} catch (Exception e) {
				Result = "Fail";
				System.out
						.println("Sorry, Download JSON Failed! Please Try Again!");
			}
			try {
				JSONArray sortJSON = new JSONArray(sortJsonResult);
				for (int i = 0; i < sortJSON.length(); i++) {
					JSONObject singleSortJSON = sortJSON.getJSONObject(i);
					JSONObject geoInfoJSON = singleSortJSON
							.getJSONObject("geoId");
					String geoId = geoInfoJSON.getString("geoId");
					String longtitude = geoInfoJSON.getString("longtitude");
					String geoAddress = geoInfoJSON.getString("geoAddress");
					String latitude = geoInfoJSON.getString("latitude");
					boolean flag = true;
					GeoInfo geoInfo = new GeoInfo(geoId, geoAddress, latitude,
							longtitude);
					for (int j = 0; j < geoInfoList.size(); j++) {
						if (geoInfoList.get(j).getGeoAddress()
								.equals(geoAddress)) {
							geoInfoList.get(j).setCount();
							flag = false;
							break;
						}
					}
					if (flag) {
						geoInfoList.add(geoInfo);
					}
					JSONObject patientInfoJSON = singleSortJSON
							.getJSONObject("patientId");
					String patientLastName = patientInfoJSON
							.getString("patientLastName");
					String patientFirstName = patientInfoJSON
							.getString("patientFirstName");
					String patientId = patientInfoJSON.getString("patientId");
					Patient patient = new Patient(patientFirstName, patientId,
							patientLastName);
					String eyeOpen = singleSortJSON.getString("eyeOpen");
					String gcsValue = singleSortJSON.getString("gcsValue");
					String glasgowComaScore = singleSortJSON
							.getString("glasgowComaScore");
					String injuries = singleSortJSON.getString("injuries");
					String motorResponse = singleSortJSON
							.getString("motorResponse");
					String priority = singleSortJSON.getString("priority");
					String respiratoryRate = singleSortJSON
							.getString("respiratoryRate");
					String score = singleSortJSON.getString("score");
					String sortDate = singleSortJSON.getString("sortDate");
					String sortId = singleSortJSON.getString("sortId");
					String sortTime = singleSortJSON.getString("sortTime");
					String symptoms = singleSortJSON.getString("symptoms");
					String systilicBloodPresure = singleSortJSON
							.getString("systilicBloodPresure");
					String verbalResponse = singleSortJSON
							.getString("verbalResponse");

					sortList.add(new Sort(eyeOpen, gcsValue, glasgowComaScore,
							injuries, motorResponse, priority, respiratoryRate,
							score, sortDate, sortId, sortTime, symptoms,
							systilicBloodPresure, verbalResponse, geoInfo,
							patient, MainActivity.user));

				}
			} catch (Exception e) {
				Result = "Fail";
				System.out.println("JSON Parse Failed!");
			}
			return Result;
		}

		@Override
		protected void onPostExecute(String result) {

			if (result.equals("Fail")) {
				Toast.makeText(MapActivity.this, "Sorry, Load Map Fail!",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(MapActivity.this, "Load Map Sucessfully!",
						Toast.LENGTH_SHORT).show();
				MapView map = (MapView) findViewById(R.id.map);
				map.getController().setZoom(9);
				map.getController().setCenter(new GeoPoint(-37.8136, 144.9631));
				map.setBuiltInZoomControls(true);
				Drawable icon = getResources().getDrawable(
						R.drawable.map_marker);
				final DefaultItemizedOverlay poiOverlay = new DefaultItemizedOverlay(
						icon);
				for (int i = 0; i < sieveList.size(); i++) {
					for (int j = 0; j < geoInfoList.size(); j++) {
						if (sieveList.get(i).getGeo().getGeoAddress()
								.equals(geoInfoList.get(j).getGeoAddress())) {
							if (geoInfoList.get(j).getCount() == 1) {
								OverlayItem poi = new OverlayItem(new GeoPoint(
										Float.parseFloat(geoInfoList.get(j)
												.getLatitude()),
										Float.parseFloat(geoInfoList.get(j)
												.getLongtitude())), sieveList
										.get(i).getPatient()
										.getPatientFirstName()
										+ " "
										+ sieveList.get(i).getPatient()
												.getPatientLastName()
										+ " (Priority "
										+ sieveList.get(i).getPriority() + ")",
										"Sieve ID: "
												+ sieveList.get(i).getSieveId());
								poiOverlay.addItem(poi);
								break;
							} else {
								double random = Math.random() * 10;
								OverlayItem poi = new OverlayItem(new GeoPoint(
										Float.parseFloat(geoInfoList.get(j)
												.getLatitude())
												+ random
												/ 4000d,
										Float.parseFloat(geoInfoList.get(j)
												.getLongtitude())
												+ random
												/ 4000d), sieveList.get(i)
										.getPatient().getPatientFirstName()
										+ " "
										+ sieveList.get(i).getPatient()
												.getPatientLastName()
										+ " (Priority "
										+ sieveList.get(i).getPriority() + ")",
										"Sieve ID: "
												+ sieveList.get(i).getSieveId());
								poiOverlay.addItem(poi);
								break;
							}
						}
					}
				}
				for (int i = 0; i < sortList.size(); i++) {
					for (int j = 0; j < geoInfoList.size(); j++) {
						if (sortList.get(i).getGeo().getGeoAddress()
								.equals(geoInfoList.get(j).getGeoAddress())) {
							if (geoInfoList.get(j).getCount() == 1) {
								OverlayItem poi = new OverlayItem(new GeoPoint(
										Float.parseFloat(geoInfoList.get(j)
												.getLatitude()),
										Float.parseFloat(geoInfoList.get(j)
												.getLongtitude())), sortList
										.get(i).getPatient()
										.getPatientFirstName()
										+ " "
										+ sortList.get(i).getPatient()
												.getPatientLastName()
										+ " (Priority "
										+ sortList.get(i).getPriority() + ")",
										"Sort ID: "
												+ sortList.get(i).getSortId());
								poiOverlay.addItem(poi);
								break;
							} else {
								double random = Math.random() * 10;
								OverlayItem poi = new OverlayItem(new GeoPoint(
										Float.parseFloat(geoInfoList.get(j)
												.getLatitude())
												+ random
												/ 4000d,
										Float.parseFloat(geoInfoList.get(j)
												.getLongtitude())
												+ random
												/ 4000d), sortList.get(i)
										.getPatient().getPatientFirstName()
										+ " "
										+ sortList.get(i).getPatient()
												.getPatientLastName()
										+ " (Priority "
										+ sortList.get(i).getPriority() + ")",
										"Sort ID: "
												+ sortList.get(i).getSortId());
								poiOverlay.addItem(poi);
								break;
							}
						}
					}
				}
				annotation = new AnnotationView(map);
				poiOverlay
						.setTapListener(new ItemizedOverlay.OverlayTapListener() {
							@Override
							public void onTap(GeoPoint pt, MapView mapView) {
								// when tapped, show the annotation for the
								// overlayItem
								int lastTouchedIndex = poiOverlay
										.getLastFocusedIndex();
								if (lastTouchedIndex > -1) {
									OverlayItem tapped = poiOverlay
											.getItem(lastTouchedIndex);
									annotation.showAnnotationView(tapped);
								}
								Intent intent = new Intent();
								intent.setClass(MapActivity.this,
										PatientActivity.class);
								intent.putExtra("Id", poiOverlay
										.getItem(lastTouchedIndex).getSnippet());
								startActivity(intent);
								finish();
							}
						});
				map.getOverlays().add(poiOverlay);
			}
		}
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
