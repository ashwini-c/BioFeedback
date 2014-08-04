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
import nz.ac.waikato.isdb.R;
import nz.ac.waikato.isdb.StressActivity;

public class SelfAssessmentActivityResources extends Activity {
	int totalQues = 42;
	SharedPreferences pref;
	int total = 0;
	ProgressBar progressBar ;
	TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_self_assessment_resources);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		txt = (TextView)findViewById(R.id.textPercent);
		// Show the Up button in the action bar.
		//setupActionBar();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		total = 0;
		pref = getSharedPreferences("physical", MODE_PRIVATE);
		total = total + pref.getInt("total", 0);
		pref = getSharedPreferences("intellectual", MODE_PRIVATE);
		total = total + pref.getInt("total", 0);
		pref = getSharedPreferences("individual", MODE_PRIVATE);
		total = total + pref.getInt("total", 0);
		pref = getSharedPreferences("social", MODE_PRIVATE);
		total = total + pref.getInt("total", 0);
		progressBar.setProgress(total);
		txt.setText(total+"/"+totalQues);
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
		getMenuInflater().inflate(R.menu.self_assessment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
		}
		return super.onOptionsItemSelected(item);
	}

	public void startSelfAssessmentPhysical(View view) {
		startActivity(new Intent(this, SelfAssessmentPhysicalActivity.class));
	}
	public void startSelfAssessmentIntellectual(View view) {
		startActivity(new Intent(this, SelfAssessmentIntellectualActivity.class));
	}
	public void startSelfAssessmentSocial(View view) {
		startActivity(new Intent(this, SelfAssessmentSocialActivity.class));
	}
	public void startSelfAssessmentIndividual(View view) {
		startActivity(new Intent(this, SelfAssessmentIndividualActivity.class));
	}
	

}
