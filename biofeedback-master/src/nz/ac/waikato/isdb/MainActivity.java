package nz.ac.waikato.isdb;

import android.widget.TextView;
import nz.ac.waikato.isdb.assessment.SelfAssessmentActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import nz.ac.waikato.isdb.assessment.SelfAssessmentPhysicalActivity;
import nz.ac.waikato.isdb.profile.Profile;

public class MainActivity extends Activity {

	/* thresholds for messages */
	static final int ASSESSMENT_THRESHOLD_MIDDLE= 33; // change to encouraging message
	static final int ASSESSMENT_THRESHOLD_TOP = 70; // change to maintenance message

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*TextView handleWelcomeText = (TextView)findViewById(R.id.welcome_text);
		TextView handleWelcomeTextPart2 = (TextView)findViewById(R.id.welcome_text_part2);
		Profile aProfile = new Profile();

		double percentageAnsweredAssessment = aProfile.getPercentageAnsweredAssessment();
		if (percentageAnsweredAssessment < ASSESSMENT_THRESHOLD_MIDDLE) {
			handleWelcomeText.setText(R.string.welcome_text);
			handleWelcomeTextPart2.setText(R.string.welcome_text_part2);
		} else if (percentageAnsweredAssessment > ASSESSMENT_THRESHOLD_TOP) {
			handleWelcomeText.setText(R.string.welcome_text_top);
			handleWelcomeTextPart2.setText(R.string.welcome_text_part2_top);
		} else {
			handleWelcomeText.setText(R.string.welcome_text_middle);
			handleWelcomeTextPart2.setText(R.string.welcome_text_part2_middle);
		}*/

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
		switch(item.getItemId())
		{
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
		default:
			break;
		}

		return super.onOptionsItemSelected(item);

	}

	public void startSelfAssessment(View view) {
		startActivity(new Intent(this, SelfAssessmentActivity.class));
	}


	public void startDoing(View view) {
		Intent nextActivity = new Intent(this, DoingActivity.class);
		startActivity(nextActivity);
	}


	public void startInformation(View view) {
		Intent nextActivity = new Intent(this, InfoActivity.class);
		startActivity(nextActivity);
	}

}
