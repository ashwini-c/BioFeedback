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
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StressActivity extends Activity {


	LinearLayout l1,l2,l3,l4;


	ArrayList<LikertScaleStrategy> like = new ArrayList<LikertScaleStrategy>();
	ArrayList<TextView> text = new ArrayList<TextView>();
	ArrayList<LinearLayout> layout = new ArrayList<LinearLayout>();
	ArrayList<EditText> editBtn = new ArrayList<EditText>();
	int ids[] = {R.id.likert1,R.id.likert2,R.id.likert3,R.id.likert4,R.id.likert5,R.id.likert6,R.id.likert7,R.id.likert8,R.id.likert9,R.id.likert10,R.id.likert11,R.id.likert12,R.id.likert13,R.id.likert14,R.id.likert15,R.id.likert16,R.id.likert17,R.id.likert18,R.id.likert19,R.id.likert20,R.id.likert21,R.id.likert22,R.id.likert23};
	int numQues =23,numText=24,numLay=4,numEdit=4;
	int textIds[] ={R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8,R.id.textView9,R.id.textView10,R.id.textView11,R.id.textView12,R.id.textView13,R.id.textView14,R.id.textView15,R.id.textView16,R.id.textView17,R.id.textView18,R.id.textView19,R.id.textView20,R.id.textView21,R.id.textView22,R.id.textView23,R.id.textView24};
	int layIds[]={R.id.edit1,R.id.edit2,R.id.edit3,R.id.edit4};
	int editIds[]={R.id.editText1,R.id.editText2,R.id.editText3,R.id.editText4};
	SharedPreferences pref;
	OnSharedPreferenceChangeListener listener;
	OnClickListener clickListner;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stress_assessment);


		for(int i =0;i<numQues;i++)
		{
			like.add((LikertScaleStrategy) findViewById(ids[i]));
		}
		for(int i=0;i<numText;i++)
			text.add((TextView)findViewById(textIds[i]));
		for(int i=0;i<numLay;i++)
			layout.add((LinearLayout)findViewById(layIds[i]));
		for(int i=0;i<numEdit;i++)
			editBtn.add((EditText)findViewById(editIds[i]));
		l1 = (LinearLayout)findViewById(R.id.options1);
		l2 = (LinearLayout)findViewById(R.id.options2);
		l3 = (LinearLayout)findViewById(R.id.options3);
		l4 = (LinearLayout)findViewById(R.id.options4);
		
		clickListner = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!(v.isSelected()))
					v.setSelected(true);
				else
					v.setSelected(false);

			}
		};

		for (TextView txt : text) {
			txt.setOnClickListener(clickListner);
		}
		for (LinearLayout txt : layout) {
			txt.setOnClickListener(clickListner);
		}

		listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
				Log.d("ashwini "," pref changed");
				if(key.equals("buttonClick"))
				{
					String val = prefs.getString(key, null);

					if(val!=null)
					{
						String[] values =  val.split(",");

						int btnVal = Integer.parseInt(values[0]);
						int btnId = Integer.parseInt(values[1]);
						Log.d("ashwini "," pref "+ btnVal + " id "+ btnId);
						if(btnVal>2)
						{
							switch (btnId) {
							case R.id.likert1:
								if(l1.getVisibility()!=View.VISIBLE)
								{
									l1.setVisibility(View.VISIBLE);
									SlideToDown(l1);
								}

								break;
							case R.id.likert2:
								if(l2.getVisibility()!=View.VISIBLE)
								{
									l2.setVisibility(View.VISIBLE);
									SlideToDown(l2);
								}

								break;
							case R.id.likert5:
								if(l3.getVisibility()!=View.VISIBLE)
								{

									l3.setVisibility(View.VISIBLE);
									SlideToDown(l3);
								}

								break;

							case R.id.likert22:
								if(l4.getVisibility()!=View.VISIBLE)
								{
									l4.setVisibility(View.VISIBLE);
									SlideToDown(l4);
								}

								break;

							default:
								break;
							}
						}
						else
						{

							switch (btnId) {
							case R.id.likert1:
								if(l1.getVisibility()==View.VISIBLE)
								{
									SlideToUp(l1);
									l1.setVisibility(View.GONE);
								}


								break;
							case R.id.likert2:
								if(l2.getVisibility()==View.VISIBLE)
								{
									SlideToUp(l2);
									l2.setVisibility(View.GONE);
								}break;
							case R.id.likert5:
								if(l3.getVisibility()==View.VISIBLE)
								{
									SlideToUp(l3);
									l3.setVisibility(View.GONE);
								}break;
							case R.id.likert22:
								if(l4.getVisibility()==View.VISIBLE)
								{
									SlideToUp(l4);
									l4.setVisibility(View.GONE);
								}
								break;

							default:
								break;
							}

						}
					}
				}
			}
		};

		pref = getSharedPreferences("stress", MODE_PRIVATE);
		pref.registerOnSharedPreferenceChangeListener(listener);

	}

	public void SlideToUp(final LinearLayout rl_footer) {
		Animation hide = AnimationUtils.loadAnimation(this, R.anim.fadeup);
		rl_footer.startAnimation(hide);

	}
	public void SlideToDown(final LinearLayout rl_footer) {
		Animation hide = AnimationUtils.loadAnimation(this, R.anim.fadedown);
		rl_footer.startAnimation(hide);

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
