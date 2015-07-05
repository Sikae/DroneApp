package com.sikaeapps.appdrone;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.MeasureSpec;
/**
 * 
 * @author Sikae
 * This class serves as the container for the drawing fragment.
 *
 */
public class MainActivity extends SingleFragmentActivity {
	static String KEY_LENGTH = "com.sikaeapps.appdrone/MainActivity.KEY_length";
	static String KEY_WIDTH = "com.sikaeapps.appdrone/MainActivity.KEY_width";
	static String KEY_HEIGHT = "com.sikaeapps.appdrone/MainActivity.KEY_height";
	int length;
	int width;
	int height;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public Fragment createFragment(){
		DrawFragment mDrawFragment = new DrawFragment();
		length = getIntent().getIntExtra(MainActivity.KEY_LENGTH, 0);
		width = getIntent().getIntExtra(MainActivity.KEY_WIDTH, 0);
		height = getIntent().getIntExtra(MainActivity.KEY_HEIGHT, 0);
		mDrawFragment.setLengthWidthHeight(length, width, height);
		return mDrawFragment;
	}

}
