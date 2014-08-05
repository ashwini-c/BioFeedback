package nz.ac.waikato.isdb;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DropDownListAdapter extends BaseAdapter{

	int ques;
	Context context;
	LayoutInflater mInflater;

	String[] list;
	int whichList=0;

	String[] excersise = {"Walking","Running","Swimming","Biking","Fill in:"};
	String [] miniBreak = {"I am ok—I have what I need to manage this"
			,"Remember what is important!"
			,"Simplicity, patience, compassion"
			,"Just keep going..."
			,"Breathe.  Breathe again.  Keep breathing"
			,"I am not alone"
			,"Love is growing inside of me"
			,"write your own"};
	String[] music = {"Relaxing music"
			,"Upbeat music"
			,"Music to move the job along…"
			,"Sing!" ,"Play instrument of your choice"};
	String[] food = {"Fruit:  an apple, orange, banana…something convenient and sweet.","Cheese:  a bit of safe (hard cheese, or soft processed/pasteurised cheeses like cottage cheese, cream cheese) cheese, with crackers or bread or fruit","Chocolate:  a reasonable amount of good, dark chocolate, good for body and soul","Nuts:  a small handful of nuts, maybe with some raisins or other dried fruit, can soothe the ravenous beast at times…","Popcorn: crunchy snack for an evening at home…","Your ideas: "};


	public DropDownListAdapter(Context ctx,int ques)
	{
		this.ques = ques;
		context = ctx;

		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		whichList =ques;
		switch(ques)
		{


		case 1:
			list =  excersise;

			break;
		case 2:
			list =  miniBreak;
			break;
		case 3:
			list =  music;
			break;
		case 4:
			list =  food;
			break;

		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//Log.d("ashwini"," "+list.toString() +" " + list.length);
		return list.length;
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return list[pos];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view = null;
		view = mInflater.inflate(R.layout.drop_down_item,null);
		TextView item = (TextView)view.findViewById(R.id.textView1);
		item.setText(list[pos]);
		SharedPreferences pref = context.getSharedPreferences("stress", context.MODE_PRIVATE);

		int val = pref.getInt("list"+whichList, -1);
		Log.d("ashwini", "selected "+ val + " pos "+ pos);
		if(val != -1 && val == pos)
			view.setSelected(true);

		return view;
	}

}
