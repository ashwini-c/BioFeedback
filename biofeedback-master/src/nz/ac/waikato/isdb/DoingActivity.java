package nz.ac.waikato.isdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import nz.ac.waikato.isdb.breathing.BreathingRateActivity;
import nz.ac.waikato.isdb.heart.HeartRateActivity;
import nz.ac.waikato.isdb.music.MusicActivity;
import nz.ac.waikato.isdb.skin.SkinTempActivity;

public class DoingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doing);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doing, menu);
		return true;
	}



	public void startOthers(View view) {
		Intent nextActivity = new Intent(this, DoingActivityOthers.class);
		startActivity(nextActivity);
	}

	public void startStressReminders(View view) {
		Intent nextActivity = new Intent(this, StressReminderActivity.class);
		startActivity(nextActivity);
	}

	public void startStrengthReminders(View view) {
		Intent nextActivity = new Intent(this, StrengthReminderActivity.class);
		startActivity(nextActivity);
	}
	public void startInformation(View view) {
		Intent nextActivity = new Intent(this, InformationActivity.class);
		startActivity(nextActivity);
	}
}
