package com.sikaeapps.appdrone;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * This is the fragment for drawing the path.
 * @author Sikae
 *
 */
public class DrawFragment extends Fragment {
	PathDrawingView mPathDrawingView;
	View v;
	int length;
	int width;
	int height;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().setTitle(R.string.draw_title);
		
	}
	
	public void setLengthWidthHeight(int length, int width, int height){
		this.length = length;
		this.width  = width;
		this.height = height;
		
		//mPathDrawingView.setLayoutParams(new LayoutParams(100, 100)); // PRUEBA PRUEBA PRUEBA
		//mPathDrawingView.measure(100, 100); PRUEBA Override
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		//Log.d("SIKAE", "VER heightPATH: " + getHeight());
		v = inflater.inflate(R.layout.fragment_draw, parent, false);
		mPathDrawingView = (PathDrawingView)v.findViewById(R.id.pathdrawingview);
		mPathDrawingView.lengthArea = length;
		mPathDrawingView.widthArea = width;
		mPathDrawingView.height = height;
		
		return v;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main_action_bar, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.menu_item_clear_path:
				mPathDrawingView.deletePath();
				return true;
			case R.id.menu_item_send_path:
				mPathDrawingView.sendArray(length);
				return true;
			case R.id.menu_item_settings:
				Intent i = new Intent(getActivity(), Settings.class);
				startActivity(i);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	

}
