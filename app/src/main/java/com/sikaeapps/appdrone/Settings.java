package com.sikaeapps.appdrone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Settings extends Activity {

    Button mSetButton;

    EditText mXEditText, mYEditText, mZEditText;
    float x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Set Button
        mSetButton = (Button)findViewById(R.id.set_button);
        mSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mXEditText = (EditText) findViewById(R.id.kx_edit_text);
                mYEditText = (EditText) findViewById(R.id.ky_edit_text);
                mZEditText = (EditText) findViewById(R.id.kz_edit_text);
                x = Float.parseFloat(mXEditText.getText().toString());
                y = Float.parseFloat(mYEditText.getText().toString());
                z = Float.parseFloat(mZEditText.getText().toString());

                File myFile = new File(Environment
                        .getExternalStorageDirectory()
                        .getAbsolutePath()
                        + File.separator
                        + "coordinates"
                        + File.separator + "control_constants.txt"); //

                try {
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter outputStreamWriterC =
                            new OutputStreamWriter(fOut);

                    outputStreamWriterC.write((mYEditText.getText().toString()) + "\r\n");
                    outputStreamWriterC.write((mZEditText.getText().toString()) + "\r\n");
                    outputStreamWriterC.write((mXEditText.getText().toString()) + "\r\n");

                    outputStreamWriterC.close();

                }
                catch (IOException e) {
                    Log.e("HEY", "File write failed: " + e.toString());
                }

                Toast.makeText(getBaseContext(), "Setting constants...", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
