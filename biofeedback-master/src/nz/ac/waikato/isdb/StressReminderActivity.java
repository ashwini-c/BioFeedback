package nz.ac.waikato.isdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import nz.ac.waikato.isdb.assessment.SelfAssessmentActivityResources;
import nz.ac.waikato.isdb.ui.LikertScale;
import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StressReminderActivity extends Activity {

	int numQues =23;
	HashMap< Integer, Integer> like = new HashMap<Integer, Integer>();
	HashMap< Integer, Integer> sortedLike = new HashMap<Integer, Integer>();

	SharedPreferences pref,pref1;
	TextView t1,t2,t3;
	Button random,activity,back;
	String[] reminderText;
	int visibility;
	Set<String> set;
	View v1,v2;
	int key=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stress_reminders);
		pref1 = getSharedPreferences("randomstringsstress", MODE_PRIVATE);
		t1 =(TextView)findViewById(R.id.reminderText1);
		t2 =(TextView)findViewById(R.id.reminderText2);
		t3 =(TextView)findViewById(R.id.reminderText3);
		v1 = findViewById(R.id.view1);
		v2 = findViewById(R.id.view2);
		reminderText = getResources().getStringArray(R.array.stressreminders);
		random = (Button)findViewById(R.id.buttonRandom);
		back = (Button)findViewById(R.id.buttonBack);

		activity = (Button)findViewById(R.id.buttonActivity);
		activity.setOnClickListener(new OnClickListener(
				) {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), StressActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

			}
		});

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(sortedLike.size() > 3)
				{

					Log.d("ashwini.next","k value "+key);
					key--;
					pref1.edit().putInt("keys", key).apply();
					setRandom();
					if(key == 0)
					{
						back.setVisibility(View.GONE);
					}
					if(key < sortedLike.size()-3)
					{
						random.setVisibility(View.VISIBLE);
					}

				}
				else if(sortedLike.size()<=3)
				{
					Toast.makeText(getApplicationContext(), "Answer more than 3 Questions in Self Assessment to get more Strategy Suggestions", Toast.LENGTH_LONG).show();
					activity.setVisibility(View.VISIBLE);
				}
			}
		});


		random.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(sortedLike.size() > 3)
				{

					Log.d("ashwini.next","k value "+key);
					key++;
					pref1.edit().putInt("keys", key).apply();
					setRandom();
					if(key == sortedLike.size()-3)
					{
						random.setVisibility(View.GONE);
					}
					if(key > 0)
					{
						back.setVisibility(View.VISIBLE);
					}


				}
				else if(sortedLike.size()<=3)
				{
					Toast.makeText(getApplicationContext(), "Answer more than 3 Questions in Self Assessment to get more Strategy Suggestions", Toast.LENGTH_LONG).show();
					activity.setVisibility(View.VISIBLE);
				}
			}
		});



	}

	public void setDefault()
	{

		int k = pref1.getInt("keys", -1);
		List<Integer> keys      = new ArrayList<Integer>(sortedLike.keySet());
		t1.setText(reminderText[keys.get( k )]);
		t2.setText(reminderText[keys.get( k+1)]);
		t3.setText(reminderText[keys.get( k+2)]);
	}

	public void setRandom()
	{

		Random       random    = new Random();
		ArrayList numbers = new ArrayList(); 
		int k =1;
		t1.setText("");
		t3.setText("");
		t2.setText("");
		t2.setVisibility(View.VISIBLE);
		t3.setVisibility(View.VISIBLE);
		v1.setVisibility(View.VISIBLE);
		v2.setVisibility(View.VISIBLE);
		int       randomKey =-1;
		Set set = sortedLike.entrySet();
		Iterator iterator2 = set.iterator();
		if(sortedLike.size() == 0)
		{
			t1.setText("Answer Questions in Self Assessment to get Strategy Suggestions");
			activity.setVisibility(View.VISIBLE);
			t2.setVisibility(View.GONE);
			t3.setVisibility(View.GONE);
			v1.setVisibility(View.GONE);
			v2.setVisibility(View.GONE);
			Log.d("ashwini", "con 1 ");

		}

		else if(sortedLike.size() > 3)
		{
			Log.d("ashwini", "con 2");
			while(iterator2.hasNext()) {
				if(k<=3)
				{
					Log.d("ashwini.loop","k value "+key);
					List<Integer> keys      = new ArrayList<Integer>(sortedLike.keySet());
					if(k==1)
						t1.setText(reminderText[keys.get( key )]);
					if(k==2)
						t2.setText(reminderText[keys.get( key+1 )]);
					if(k==3)
						t3.setText(reminderText[keys.get( key+2 )]);
					k=k+1;
				}
				else{
					Log.d("ashwini key","break");
					break;
				}

			}
		}
		else if(sortedLike.size() <= 3)
		{

			Log.d("ashwini", "con 3 ");
			while(iterator2.hasNext())
			{
				Map.Entry me2 = (Map.Entry)iterator2.next();
				if (k == 1)
				{
					t1.setText(reminderText[(Integer) me2.getKey()]);
					t2.setVisibility(View.GONE);
					t3.setVisibility(View.GONE);
					v1.setVisibility(View.GONE);
					v2.setVisibility(View.GONE);
				}
				else if (k == 2)
				{

					t2.setVisibility(View.VISIBLE);
					t3.setVisibility(View.GONE);
					v1.setVisibility(View.VISIBLE);
					v2.setVisibility(View.GONE);
					t2.setText(reminderText[(Integer) me2.getKey()]);
				}
				else if (k == 3)
				{
					t2.setVisibility(View.VISIBLE);
					t3.setVisibility(View.VISIBLE);
					v1.setVisibility(View.VISIBLE);
					v2.setVisibility(View.VISIBLE);
					t3.setText(reminderText[(Integer) me2.getKey()]);
				}
				k++;
			}
		}


	}
	private HashMap getHighRatedValues(HashMap map)
	{
		HashMap rated = new LinkedHashMap();
		Set set = sortedLike.entrySet();
		for (Iterator it = set.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			if((Integer)(entry.getValue()) >2)
				rated.put(entry.getKey(), entry.getValue());
			else
				break;
		} 
		return rated;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();



		pref = getSharedPreferences("stress", MODE_PRIVATE);
		int j=0;
		for(int i=0;i<numQues;i++)
		{
			int val = pref.getInt("like"+i, -1);

			like.put(j,val);
			j++;
		}

		sortedLike = sortByValues(like);
		sortedLike = getHighRatedValues(sortedLike);
		if((pref1.getInt("keys", -1) == -1) &&(sortedLike.size() != pref1.getInt("sortedarraylen", -1)) )
			key =0;
		else
			key = pref1.getInt("keys", -1);
		random.setVisibility(View.VISIBLE);
		back.setVisibility(View.VISIBLE);
		if(sortedLike.size() == 0)
		{
			//Toast.makeText(getApplicationContext(), "Answer Questions in Self Assessment to get Strategy Suggestions", Toast.LENGTH_LONG).show();
			activity.setVisibility(View.VISIBLE);
			random.setVisibility(View.GONE);
			back.setVisibility(View.GONE);
		}
		else if(sortedLike.size() > 3)
			activity.setVisibility(View.INVISIBLE);

		int k = pref1.getInt("keys", -1);
		Log.d("ashwini.next","k value "+k + " sorted size "+sortedLike.size());
		if(k == sortedLike.size()-3)
		{
			random.setVisibility(View.GONE);
		}
		if(k == 0)
		{
			back.setVisibility(View.GONE);
		}
		if((pref1.getInt("keys", -1) != -1) && sortedLike.size() > 3 && (sortedLike.size() == pref1.getInt("sortedarraylen", -1)))
			setDefault();
		else
			setRandom();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		pref1.edit().putInt("sortedarraylen", sortedLike.size()).apply();
	}

	@SuppressWarnings("unchecked")
	private static HashMap sortByValues(HashMap map) { 
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		} 
		return sortedHashMap;
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

			ResetAll();
			return true;
		case R.id.action_about:
			Intent intent = new Intent(getApplicationContext(),AboutUsActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private void ResetAll()
	{
		finish();
		Intent intent = getIntent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(intent);
	}
}
