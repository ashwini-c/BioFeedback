package nz.ac.waikato.isdb.assessment;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import nz.ac.waikato.isdb.R;
import nz.ac.waikato.isdb.ui.LikertScale;

public class SelfAssessmentIntellectualActivity extends Activity {
	
	ArrayList<LikertScale> like = new ArrayList<LikertScale>();
	int ids[] = {R.id.likert1,R.id.likert2,R.id.likert3,R.id.likert4,R.id.likert5,R.id.likert6,R.id.likert7,R.id.likert8};
	int numQues =8;
	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_self_assessment_intellectual);
		// Show the Up button in the action bar.
		//setupActionBar();
		pref = getSharedPreferences("intellectual", MODE_PRIVATE);
		for(int i =0;i<numQues;i++)
		{
			like.add((LikertScale) findViewById(ids[i]));
		}
	}
	@Override
	protected void onResume() {
				super.onResume();
		for(int i=0;i<numQues;i++)
		{
			int val = pref.getInt("like"+i, -1);
			like.get(i).setValue(val);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		int total =0 ;
		for(int i=0;i<numQues;i++)
		{
			pref.edit().putInt("like"+i, like.get(i).getValue()).apply();
			if(like.get(i).getValue()!= -1)
			{	total = total+1;
			}
		}
		pref.edit().putInt("total", total).apply();

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

}
