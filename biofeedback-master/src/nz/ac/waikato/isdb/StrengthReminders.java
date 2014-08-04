package nz.ac.waikato.isdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class StrengthReminders extends Activity {

	int numQuesPhy =4;
	int numQuesInt =8;
	int numQuesSoc =14;
	int numQuesInd =16;
	HashMap< Integer, Integer> like = new HashMap<Integer, Integer>();
	HashMap< Integer, Integer> sortedLike = new HashMap<Integer, Integer>();
	ListView list;
	ArrayList<String> reminders = new ArrayList<String>();
	SharedPreferences pref;
	ArrayAdapter<String> adapter;
	ArrayList<String> str ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.strength_reminder);

		list=(ListView)findViewById(R.id.list);

		str = new ArrayList<String>();
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




		Set set = sortedLike.entrySet();
		Iterator iterator2 = set.iterator();
		while(iterator2.hasNext()) {

			Map.Entry me2 = (Map.Entry)iterator2.next();
			if((Integer)me2.getValue()==-1)
			{
				break;
			}
			else
			{
				reminders.add(getResources().getStringArray(R.array.reminders)[(Integer) me2.getKey()]);

			}
		}

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, reminders);
		list.setAdapter(adapter);
		list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> par, View arg1, int arg2,
					long arg3) {

				str.add((String)par.getItemAtPosition(arg2));
				if(list.getCheckedItemCount() == 3)
				{

					pref = getSharedPreferences("reminderarray", MODE_PRIVATE);
					Set<String> set = new HashSet<String>();
					set.addAll(str);
					pref.edit().putStringSet("reminderarray", set).commit();
					
					finish();
				}

			}
		});




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
		getMenuInflater().inflate(R.menu.strength_reminders, menu);
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
