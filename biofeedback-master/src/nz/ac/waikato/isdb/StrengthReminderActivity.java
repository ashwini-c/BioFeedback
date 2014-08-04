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
import java.util.Set;

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

public class StrengthReminderActivity extends Activity {

	int numQuesPhy =4;
	int numQuesInt =8;
	int numQuesSoc =14;
	int numQuesInd =16;
	HashMap< Integer, Integer> like = new HashMap<Integer, Integer>();
	HashMap< Integer, Integer> sortedLike = new HashMap<Integer, Integer>();

	SharedPreferences pref,pref1;
	TextView t1;
	Button manual,random;
	String[] reminderText;
	int visibility;
	Set<String> set;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_strength_reminders);
		t1 =(TextView)findViewById(R.id.reminderText);
		reminderText = getResources().getStringArray(R.array.reminders);
		manual = (Button)findViewById(R.id.buttonManual);
		random = (Button)findViewById(R.id.buttonRandom);
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
		pref = getSharedPreferences("mode", MODE_PRIVATE);
		visibility = pref.getInt("mode", 0);

		if(visibility == 0)
		{
			manual.setVisibility(View.VISIBLE);
			random.setVisibility(View.GONE);
			setRandom();
		}
		else
		{
			manual.setVisibility(View.GONE);
			random.setVisibility(View.VISIBLE);
			setManual();

		}
		manual.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				random.setVisibility(View.VISIBLE);
				manual.setVisibility(View.GONE);
				pref.edit().putInt("mode", 1).commit();
				Intent intent = new Intent(getApplicationContext(), StrengthReminders.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
		random.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				manual.setVisibility(View.VISIBLE);
				random.setVisibility(View.GONE);
				pref.edit().putInt("mode", 0).commit();
				setRandom();
			}
		});

	}

	public void setManual()
	{
		t1.setText("");
		pref1 = getSharedPreferences("reminderarray", MODE_PRIVATE);
		set = pref1.getStringSet("reminderarray", null);
		if(set == null)
			return;
		Iterator iterator2 = set.iterator();
		int i=1;
		while(iterator2.hasNext()) {
			String me2 = (String)iterator2.next();
			t1.setText(t1.getText()+ " \n"+i+": "+ me2);
			i++;
		}

	}

	public void setRandom()
	{

		int k =1;
		t1.setText("");
		Set set = sortedLike.entrySet();
		Iterator iterator2 = set.iterator();
		while(iterator2.hasNext()) {
			if(k<=3)
			{
				Map.Entry me2 = (Map.Entry)iterator2.next();
				if((Integer)me2.getValue()==-1)
				{
					if(k==1)
					{
						t1.setText("Answere Questons in Self Assement to get Strength Reminders");
						break;
					}
				}
				else
				{
					t1.setText(t1.getText()+ " \n"+k+": "+reminderText[(Integer) me2.getKey()]);
					Log.d("ashwini key",me2.getKey() + ": ");
					Log.d("ashwini value"," "+ me2.getValue());
					k=k+1;
				}
			}
			else{
				Log.d("ashwini key","break");
				break;
			}

		}
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(visibility == 0)
		{
			manual.setVisibility(View.VISIBLE);
			random.setVisibility(View.GONE);
			setRandom();
		}
		else
		{
			manual.setVisibility(View.GONE);
			random.setVisibility(View.VISIBLE);
			setManual();

		}

		// testing values
		/*Log.d("ashwini","Before Sorting:");
		Set set = like.entrySet();
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
