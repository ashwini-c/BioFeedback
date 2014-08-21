package nz.ac.waikato.isdb.assessment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import nz.ac.waikato.isdb.AboutUsActivity;
import nz.ac.waikato.isdb.R;
import nz.ac.waikato.isdb.StressActivity;

public class SelfAssessmentActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_self_assessment);
		// Show the Up button in the action bar.
		//setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
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
		SharedPreferences pref;

		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_settings:
			pref = getSharedPreferences("stress", MODE_PRIVATE);
			pref.edit().clear().commit();
			pref = getSharedPreferences("randomstringsstress", MODE_PRIVATE);
			pref.edit().clear().commit();
			pref = getSharedPreferences("physical", MODE_PRIVATE);
			pref.edit().clear().commit();
			pref = getSharedPreferences("intellectual", MODE_PRIVATE);
			pref.edit().clear().commit();
			pref = getSharedPreferences("social", MODE_PRIVATE);
			pref.edit().clear().commit();
			pref = getSharedPreferences("individual", MODE_PRIVATE);
			pref.edit().clear().commit();
			pref = getSharedPreferences("randomstrings", MODE_PRIVATE);
			pref.edit().clear().commit();
			return true;
		case R.id.action_about:
			Intent intent = new Intent(getApplicationContext(),AboutUsActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	public void startAssessment1(View view) {
		startActivity(new Intent(this, SelfAssessmentActivityResources.class));
	}
	public void startAssessment2(View view) {
		startActivity(new Intent(this, StressActivity.class));
	}
	public void startAssessment3(View view) {
		//startActivity(new Intent(this, SelfAssessmentSocialActivity.class));
	}
	public void startAssessment4(View view) {
		//startActivity(new Intent(this, SelfAssessmentIndividualActivity.class));
	}
	public void startAssessment5(View view) {
		Intent nextActivity = new Intent(this, StressActivity.class);
		//startActivity(nextActivity);
	}

}
