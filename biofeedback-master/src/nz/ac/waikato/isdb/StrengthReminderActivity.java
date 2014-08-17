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

import nz.ac.waikato.isdb.assessment.SelfAssessmentActivity;
import nz.ac.waikato.isdb.assessment.SelfAssessmentActivityResources;
import nz.ac.waikato.isdb.ui.LikertScale;
import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StrengthReminderActivity extends Activity {

	int numQuesPhy =4;
	int numQuesInt =8;
	int numQuesSoc =13;
	int numQuesInd =16;
	HashMap< Integer, Integer> like = new HashMap<Integer, Integer>();
	HashMap< Integer, Integer> sortedLike = new HashMap<Integer, Integer>();

	SharedPreferences pref,pref1;
	TextView t1,t2,t3;
	Button random,activity;
	String[] reminderText;
	int visibility;
	Set<String> set;
	View v1,v2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_strength_reminders);
		t1 =(TextView)findViewById(R.id.reminderText1);
		t2 =(TextView)findViewById(R.id.reminderText2);
		t3 =(TextView)findViewById(R.id.reminderText3);
		v1 = findViewById(R.id.view1);
		v2 = findViewById(R.id.view2);
		reminderText = getResources().getStringArray(R.array.reminders);
		random = (Button)findViewById(R.id.buttonRandom);
		activity = (Button)findViewById(R.id.buttonActivity);
		


		activity.setOnClickListener(new OnClickListener(
				) {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), SelfAssessmentActivityResources.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

			}
		});


		random.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(sortedLike.size() == 0)
				{
					Toast.makeText(getApplicationContext(), "Answer Questions in Self Assement to get Strength Reminders", Toast.LENGTH_LONG).show();
					activity.setVisibility(View.VISIBLE);
				}
				else if(sortedLike.size() > 3)
					setRandom();
				else if(sortedLike.size()<=3)
				{
					Toast.makeText(getApplicationContext(), "Answer more than 3 Questions in Self Assement to randomize Strength Reminders", Toast.LENGTH_LONG).show();
					activity.setVisibility(View.VISIBLE);
				}
			}
		});

	}



	public void setDefault()
	{
		t1.setText(reminderText[pref1.getInt("key1", -1)]);
		t2.setText(reminderText[pref1.getInt("key2", -1)]);
		t3.setText(reminderText[pref1.getInt("key3", -1)]);
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
			t1.setText("Answer Questions in Self Assement to get Strength Reminders");
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
					Map.Entry me2 = (Map.Entry)iterator2.next();

					//t1.setText(t1.getText()+ " \n"+k+": "+reminderText[(Integer) me2.getKey()]);



					List<Integer> keys      = new ArrayList<Integer>(sortedLike.keySet());
					if(k==1)
					{
						randomKey = keys.get( random.nextInt(keys.size()) );
						t1.setText(reminderText[randomKey]);
						pref1.edit().putInt("key1", randomKey).apply();
						numbers.add(randomKey);

					}
					else{
						randomKey = keys.get( random.nextInt(keys.size()) );
						while(true)
						{
							if(numbers.contains(randomKey))
								randomKey = keys.get( random.nextInt(keys.size()) );
							else{
								numbers.add(randomKey);
								break;
							}
						}
					}

					if(k==2)
					{
						t2.setText(reminderText[randomKey]);
						pref1.edit().putInt("key2", randomKey).apply();
					}
					if(k==3)
					{
						t3.setText(reminderText[randomKey]);

						pref1.edit().putInt("key3", randomKey).apply();
					}
					int       value     = sortedLike.get(randomKey);
					Log.d("ashwiniii key",randomKey + ": ");
					//t1.setText(t1.getText()+ " \n"+k+": "+reminderText[randomKey]);
					k=k+1;
					Log.d("ashwiniii value"," "+ value);


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
			if((Integer)(entry.getValue()) != -1)
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


		// testing values
		Log.d("ashwini","on resume:");
		/*Set set = like.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry me = (Map.Entry)iterator.next();
			Log.d("ashwini key",me.getKey() + ": ");
			Log.d("ashwini value"," "+ me.getValue());
		}
		//Map<Integer, String> map = sortByValues(hmap); 
		Log.d("ashwini","After Sorting:");
		Set set2 = sortedLike.entrySet();
		Iterator iterator2 = set2.iterator();
		while(iterator2.hasNext()) {
			Map.Entry me2 = (Map.Entry)iterator2.next();
			Log.d("ashwini key",me2.getKey() + ": ");
			Log.d("ashwini value"," "+ me2.getValue());
		}*/
		pref = getSharedPreferences("physical", MODE_PRIVATE);
		int j=0;
		for(int i=0;i<numQuesPhy;i++)
		{
			int val = pref.getInt("like"+i, -1);

			like.put(j,val);
			j++;
		}
		pref = getSharedPreferences("intellectual", MODE_PRIVATE);
		for(int i=0;i<numQuesInt;i++)
		{
			int val = pref.getInt("like"+i, -1);

			like.put(j,val);
			j++;
		}
		pref = getSharedPreferences("social", MODE_PRIVATE);
		for(int i=0;i<numQuesSoc;i++)
		{
			int val = pref.getInt("like"+i, -1);

			like.put(j,val);
			j++;
		}
		pref = getSharedPreferences("individual", MODE_PRIVATE);
		for(int i=0;i<numQuesInd;i++)
		{
			int val = pref.getInt("like"+i, -1);

			like.put(j,val);
			j++;
		}
		sortedLike = sortByValues(like);
		sortedLike = getHighRatedValues(sortedLike);
		pref1 = getSharedPreferences("randomstrings", MODE_PRIVATE);
		if((pref1.getInt("key1", -1) != -1) && sortedLike.size() > 3)
			setDefault();
		else
			setRandom();

		
		 if(sortedLike.size() > 3)
			activity.setVisibility(View.INVISIBLE);
		

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
		getMenuInflater().inflate(R.menu.information, menu);
		return true;
	}
}
