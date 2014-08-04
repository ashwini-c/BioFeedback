package nz.ac.waikato.isdb.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import nz.ac.waikato.isdb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * UI element representing a Likert scale from 1 to 5.
 */
public class LikertScaleStrategy extends LinearLayout implements View.OnClickListener {
	/** return value if no button has been pressed */
	static int NO_SELECTION = -1;

	/** all toggle buttons of this Likert scale */
	List<ToggleButton> buttons;

	public LikertScaleStrategy(Context context) {
		super(context);
		init();
	}

	public LikertScaleStrategy(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LikertScaleStrategy(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	protected void init() {
		Context ctxt = getContext();
		if (ctxt == null)
			return;

		buttons = new ArrayList<ToggleButton>();

		//--A1--
		String label = "A1";
		ToggleButton button = new ToggleButton(ctxt);
		button.setTextColor(Color.TRANSPARENT);
		button.setText(label);
		button.setTextOn(label);
		button.setTextOff(label);
		button.setBackgroundResource(R.drawable.purple_likert_button_a1);
		button.setOnClickListener(this);
		addView(button);
		buttons.add(button);
		//--A2--
		label = "A2";
		button = new ToggleButton(ctxt);
		button.setTextColor(Color.TRANSPARENT);
		button.setText(label);
		button.setTextOn(label);
		button.setTextOff(label);
		button.setBackgroundResource(R.drawable.purple_likert_button_a2);
		button.setOnClickListener(this);
		addView(button);
		buttons.add(button);
		//--A3--
		label = "A3";
		button = new ToggleButton(ctxt);
		button.setTextColor(Color.TRANSPARENT);
		button.setTextOn(label);
		button.setText(label);
		button.setTextOff(label);
		button.setBackgroundResource(R.drawable.purple_likert_button_a3);
		button.setOnClickListener(this);
		addView(button);
		buttons.add(button);
		//--B1--
		label = "B1";
		button = new ToggleButton(ctxt);
		button.setTextColor(Color.TRANSPARENT);
		button.setText(label);
		button.setTextOn(label);
		button.setTextOff(label);
		button.setBackgroundResource(R.drawable.purple_likert_button_b1);
		button.setOnClickListener(this);
		addView(button);
		buttons.add(button);
		//--B2--
		label = "B2";
		button = new ToggleButton(ctxt);
		button.setTextColor(Color.TRANSPARENT);
		button.setText(label);
		button.setTextOn(label);
		button.setTextOff(label);
		button.setBackgroundResource(R.drawable.purple_likert_button_b2);
		button.setOnClickListener(this);
		addView(button);
		buttons.add(button);
		//--B3--
		label = "B3";
		button = new ToggleButton(ctxt);
		button.setTextColor(Color.TRANSPARENT);
		button.setText(label);
		button.setTextOn(label);
		button.setTextOff(label);
		button.setBackgroundResource(R.drawable.purple_likert_button_b3);
		button.setOnClickListener(this);
		addView(button);
		buttons.add(button);
		//--B4--
		label = "B4";
		button = new ToggleButton(ctxt);
		button.setTextColor(Color.TRANSPARENT);
		button.setText(label);
		button.setTextOn(label);
		button.setTextOff(label);
		button.setBackgroundResource(R.drawable.purple_likert_button_b4);
		button.setOnClickListener(this);
		addView(button);
		buttons.add(button);
	}

	/**
	 * Get the selected value. If none has been selected then -1 is returned.
	 *
	 * @return selected value or -1
	 */
	public int getValue() {
		ToggleButton checkedButton = getCheckedButton();
		int val =  checkedButton == null ? NO_SELECTION : Integer.parseInt(checkedButton.getText().toString());
		return val;
	}


	public void setValue(int val)
	{
		for(ToggleButton button : buttons)
		{
			if(Integer.parseInt(button.getText().toString()) == val)
			{
				button.setChecked(true);
				continue;

			}
			button.setChecked(false);
		}
	}

	@Override
	public void onClick(View view) {

		ToggleButton clickedButton = (ToggleButton) view;
		for (ToggleButton button : buttons) {
			if (button.equals(clickedButton))
				continue;
			button.setChecked(false);
		}
	}

	/**
	 * Gets the currently checked button. If none has been checked then null is return.
	 *
	 * @return checked button or null
	 */
	protected ToggleButton getCheckedButton() {
		for (ToggleButton button : buttons) {
			if (button.isChecked())
				return button;
		}
		return null;
	}
}
