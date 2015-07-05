package com.sikaeapps.appdrone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 * 
 * @author Sikae
 * This is the entry point of the app.
 * A simple activity that asks the user to enter the length of the area.
 */
public class InitialDialog extends Activity {
	Button mOkButton;
	
	EditText mHeightEditText;
	int length;
	int width;
	int height;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_initial);
		// OK Button
				mOkButton = (Button)findViewById(R.id.ok_button);
				mOkButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v){
						Intent i = new Intent(InitialDialog.this, MainActivity.class);
						
						mHeightEditText = (EditText)findViewById(R.id.height_edit_text);
						length = 450;
						width = 900;
						height = Integer.parseInt(mHeightEditText.getText().toString());
						i.putExtra(MainActivity.KEY_LENGTH, length);
						i.putExtra(MainActivity.KEY_WIDTH, width);
						i.putExtra(MainActivity.KEY_HEIGHT, height);
						Log.d("SIKAE", "VER height: " + getWindow().getDecorView().getRootView().getHeight());
						Log.d("SIKAE", "VER width: " + getWindow().getDecorView().getRootView().getWidth());
						startActivity(i);
					}
				});
		
	}	
	
}
