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
import nz.ac.waikato.isdb.ui.LikertScaleStrategy;
import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class StressActivity extends Activity {


	ListView l1,l2,l3,l4;
	DropDownListAdapter adapter1,adapter2,adapter3,adapter4;
	LikertScaleStrategy like1,like2,like3,like4;

	ArrayList<LikertScaleStrategy> like = new ArrayList<LikertScaleStrategy>();
	int ids[] = {R.id.likert1,R.id.likert2,R.id.likert3,R.id.likert4,R.id.likert5,R.id.likert6,R.id.likert7,R.id.likert8,R.id.likert9,R.id.likert10,R.id.likert11,R.id.likert12,R.id.likert13,R.id.likert14,R.id.likert15,R.id.likert16,R.id.likert17,R.id.likert18,R.id.likert19,R.id.likert20,R.id.likert21,R.id.likert22,R.id.likert23};
	int numQues =23;
	SharedPreferences pref;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stress_assessment);

		pref = getSharedPreferences("stress", MODE_PRIVATE);
		for(int i =0;i<numQues;i++)
		{
			like.add((LikertScaleStrategy) findViewById(ids[i]));
		}
		l1 = (ListView)findViewById(R.id.listView1);
		l2 = (ListView)findViewById(R.id.listView2);
		l3 = (ListView)findViewById(R.id.listView3);
		l4 = (ListView)findViewById(R.id.listView4);
		like1 = (LikertScaleStrategy)findViewById(R.id.likert1);
		adapter1 = new DropDownListAdapter(getApplicationContext(), 1);
		adapter2 = new DropDownListAdapter(getApplicationContext(), 2);
		adapter3 = new DropDownListAdapter(getApplicationContext(), 3);
		adapter4 = new DropDownListAdapter(getApplicationContext(), 4);
		l1.setAdapter(adapter1);
		l2.setAdapter(adapter2);
		l3.setAdapter(adapter3);
		l4.setAdapter(adapter4);

		setListViewHeightBasedOnChildren(l1);
		setListViewHeightBasedOnChildren(l2);
		setListViewHeightBasedOnChildren(l3);
		setListViewHeightBasedOnChildren(l4);
		l1.setOnTouchListener(new OnTouchListener() {
			// Setting on Touch Listener for handling the touch inside ScrollView
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Disallow the touch request for parent scroll on touch of child view
				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});
		l2.setOnTouchListener(new OnTouchListener() {
			// Setting on Touch Listener for handling the touch inside ScrollView
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Disallow the touch request for parent scroll on touch of child view
				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});
		l3.setOnTouchListener(new OnTouchListener() {
			// Setting on Touch Listener for handling the touch inside ScrollView
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Disallow the touch request for parent scroll on touch of child view
				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});
		l4.setOnTouchListener(new OnTouchListener() {
			// Setting on Touch Listener for handling the touch inside ScrollView
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Disallow the touch request for parent scroll on touch of child view
				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});

		l1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {

				if(view.isSelected())
				{
					view.setSelected(false);
					pref.edit().putInt("list1", -1).apply();
				}
				else
				{
					view.setSelected(true);
					pref.edit().putInt("list1", pos).apply();
				}

			}
		});
		l2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				if(view.isSelected())
				{
					view.setSelected(false);
					pref.edit().putInt("list2", -1).apply();
				}
				else
				{
					view.setSelected(true);
					pref.edit().putInt("list2", pos).apply();
				}

			}
		});
		l3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				if(view.isSelected())
				{
					view.setSelected(false);
					pref.edit().putInt("list3", -1).apply();
				}
				else
				{
					view.setSelected(true);
					pref.edit().putInt("list3", pos).apply();
				}

			}
		});
		l4.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				if(view.isSelected())
				{
					view.setSelected(false);
					pref.edit().putInt("list4", -1).apply();
				}
				else
				{
					view.setSelected(true);
					pref.edit().putInt("list4", pos).apply();
				}

			}
		});



	}


	@Override
	protected void onResume() {
		super.onResume();
		for(int i=0;i<numQues;i++)
		{
			int val = pref.getInt("like"+i, -1);
			like.get(i).setValue(val);
		}
		/*for(int i =1;i<=4;i++)
		{
			int val = pref.getInt("list"+i, -1);
			Log.d("ashwini", "selected "+ val);
			if(val != -1)
				l1.getAdapter().getView(val, null, null).setSelected(true);
			adapter1.notifyDataSetChanged();



		}*/
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
		pref.edit().putInt("totalStress", total).apply();
	}

	/**** Method for Setting the Height of the ListView dynamically.
	 **** Hack to fix the issue of not showing all the items of the ListView
	 **** when placed inside a ScrollView  ****/
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;

		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, null, listView);
			//if (i == 0)
			//view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			Log.d("ashwini"," height "+ view.getMeasuredHeight());
			totalHeight += view.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.information, menu);
		return true;
	}
}
