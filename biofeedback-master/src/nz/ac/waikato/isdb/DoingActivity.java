package nz.ac.waikato.isdb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	
}
