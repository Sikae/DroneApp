package com.sikaeapps.appdrone;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.net.Uri;

/**
 * 
 * @author Sikae
 * This class is the View for the Fragment.
 *
 */
public class PathDrawingView extends View {
	ArrayList<PointF> mArrayList= new ArrayList<PointF>();
	ArrayList<PointF> mArrayListXRef= new ArrayList<PointF>();
	ArrayList<PointF> mArrayListYRef= new ArrayList<PointF>();
	Paint paint = new Paint();
	Path path = new Path();
	int canvasLength;
	int canvasWidth;
	int widthArea; // set from DrawFragment.java
	int lengthArea; // set from DrawFragment.java
	int height; // Z coordinate
	// Constructor used when creating layout from code
	public PathDrawingView(Context context){
		this(context, null);
	}
	// Constructor used when creating layout from XML
	public PathDrawingView(Context context, AttributeSet attrs){
		super(context, attrs);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(10);
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		
		canvasLength = 1600;
		canvasWidth = 2560;
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if(lengthArea > widthArea) { // height greater than width
			// height is all the available height on screen. Set width
			setMeasuredDimension((canvasLength * widthArea) / lengthArea, canvasLength);
		} else { //SIEMPRE ES ESTE
			// width is all the available width on screen. Set height
			setMeasuredDimension(canvasWidth, (canvasWidth * lengthArea) / widthArea);
		}
		Log.d("SIKAE", "canvasLength:" + canvasLength + "canvasWidth" + canvasWidth);
		Log.d("SIKAE", "widthMeasureSpec" + widthMeasureSpec + "heightMeasureSpec" + heightMeasureSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		canvas.drawPath(path, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		PointF coord = new PointF(event.getX(), event.getY());
		mArrayList.add(coord);
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			path.moveTo(coord.x, coord.y);
			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(coord.x, coord.y);
			Log.d("SIKAE", "x: " + coord.x + "y: " + coord.y);
		case MotionEvent.ACTION_UP:
			break;
		default:
			return false;
		}
		invalidate();
		return true;
	}
	
	public void deletePath(){
		this.path.reset();
		mArrayList.clear();
		invalidate();
	}
	public void sendArray(int length){
        final double X_MAX_VALUE = 10.0;
        final double Y_MAX_VALUE = 15.0;

		this.widthArea = length;
		this.lengthArea = (canvasLength * length) / canvasWidth;
		
		for(PointF point : mArrayList){
			Log.d("SIKAE", "x:" + point.x + " y:" + point.y);
		}
		Log.d("SIKAE", "NUMBER POINTS: " + mArrayList.size());
		
		
		try {
			// write the x, y and z coordinates EACH to an external .txt file
            int counter = 0;
			new File(Environment.getExternalStorageDirectory().getAbsolutePath()
					+ File.separator + "coordinates").mkdirs();

			//OutputStreamWriter outputStreamWriterX = new OutputStreamWriter(getContext().openFileOutput("x_n_ref.txt", Context.MODE_PRIVATE));
			//OutputStreamWriter outputStreamWriterX2 = new OutputStreamWriter(getContext().openFileOutput("x_np1_ref.txt", Context.MODE_PRIVATE));

			File myFileX = new File(Environment
					.getExternalStorageDirectory()
					.getAbsolutePath()
					+ File.separator
					+ "coordinates"
					+ File.separator + "x_ref_n.txt");
			myFileX.createNewFile();
			FileOutputStream fOutX = new FileOutputStream(myFileX);
			OutputStreamWriter outputStreamWriterX =
					new OutputStreamWriter(fOutX);

			File myFileX2 = new File(Environment
					.getExternalStorageDirectory()
					.getAbsolutePath()
					+ File.separator
					+ "coordinates"
					+ File.separator + "x_ref_np1.txt");
			myFileX2.createNewFile();
			FileOutputStream fOutX2 = new FileOutputStream(myFileX2);
			OutputStreamWriter outputStreamWriterX2 =
					new OutputStreamWriter(fOutX2);

			mArrayListXRef.add(0, mArrayList.get(0));
			for (int i = 1, n = mArrayList.size(); i < n; i++)
			{
				mArrayListXRef.add(i, mArrayList.get(i-1));
			}
			
			for(PointF point : mArrayList){
            	outputStreamWriterX.write((point.x * Y_MAX_VALUE / 2560) + "\r\n");
    		}
			for(PointF point : mArrayListXRef){
            	outputStreamWriterX2.write((point.x * Y_MAX_VALUE / 2560) + "\r\n");
    		}
            outputStreamWriterX.close();
            outputStreamWriterX2.close();
                        
         
            //OutputStreamWriter outputStreamWriterY = new OutputStreamWriter(getContext().openFileOutput("y_n_ref.txt", Context.MODE_PRIVATE));
            //OutputStreamWriter outputStreamWriterY2 = new OutputStreamWriter(getContext().openFileOutput("y_np1_ref.txt", Context.MODE_WORLD_WRITEABLE));

			File myFileY = new File(Environment
					.getExternalStorageDirectory()
					.getAbsolutePath()
					+ File.separator
					+ "coordinates"
					+ File.separator + "y_ref_n.txt");
			myFileY.createNewFile();
			FileOutputStream fOutY = new FileOutputStream(myFileY);
			OutputStreamWriter outputStreamWriterY =
					new OutputStreamWriter(fOutY);

			File myFileY2 = new File(Environment
					.getExternalStorageDirectory()
					.getAbsolutePath()
					+ File.separator
					+ "coordinates"
					+ File.separator + "y_ref_np1.txt");
			myFileY2.createNewFile();
			FileOutputStream fOutY2 = new FileOutputStream(myFileY2);
			OutputStreamWriter outputStreamWriterY2 =
					new OutputStreamWriter(fOutY2);
            
            for(PointF point : mArrayList){
            	outputStreamWriterY.write(((1600 - point.y) * X_MAX_VALUE / 1600) + "\r\n");
            }
            for(PointF point : mArrayListXRef){
            	outputStreamWriterY2.write(((1600 - point.y) * X_MAX_VALUE / 1600) + "\r\n");
    		}
            outputStreamWriterY.close();
            outputStreamWriterY2.close();
            
            //OutputStreamWriter outputStreamWriterZ = new OutputStreamWriter(getContext().openFileOutput("z_n_ref.txt", Context.MODE_WORLD_WRITEABLE));
            //OutputStreamWriter outputStreamWriterZ2 = new OutputStreamWriter(getContext().openFileOutput("z_np1_ref.txt", Context.MODE_WORLD_WRITEABLE));

			File myFileZ = new File(Environment
					.getExternalStorageDirectory()
					.getAbsolutePath()
					+ File.separator
					+ "coordinates"
					+ File.separator + "z_ref_n.txt");
			myFileZ.createNewFile();
			FileOutputStream fOutZ = new FileOutputStream(myFileZ);
			OutputStreamWriter outputStreamWriterZ =
					new OutputStreamWriter(fOutZ);

			File myFileZ2 = new File(Environment
					.getExternalStorageDirectory()
					.getAbsolutePath()
					+ File.separator
					+ "coordinates"
					+ File.separator + "z_ref_np1.txt");
			myFileZ2.createNewFile();
			FileOutputStream fOutZ2 = new FileOutputStream(myFileZ2);
			OutputStreamWriter outputStreamWriterZ2 =
					new OutputStreamWriter(fOutZ2);

            for(PointF point : mArrayList){
            	outputStreamWriterZ.write((height / 100.0) + "\r\n");
            	outputStreamWriterZ2.write((height / 100.0) + "\r\n");
    		}
            outputStreamWriterZ.close();
            outputStreamWriterZ2.close();
            
        }
        catch (IOException e) {
            Log.e("HEY", "File write failed: " + e.toString());
        }

		//*********TEST
		ArrayList<String> list = new ArrayList<String>();
		//list.add("y_np1_ref.txt");
		//list.add("z_n_ref.txt");
		list.add(Environment
				.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "coordinates"
				+ File.separator + "x_n_ref.txt");
		list.add(Environment
				.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "coordinates"
				+ File.separator + "x_np1_ref.txt");
		list.add(Environment
				.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "coordinates"
				+ File.separator + "y_n_ref.txt");
		list.add(Environment
				.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "coordinates"
				+ File.separator + "y_np1_ref.txt");
		list.add(Environment
				.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "coordinates"
				+ File.separator + "z_n_ref.txt");
		list.add(Environment
				.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "coordinates"
				+ File.separator + "z_np1_ref.txt");
		try {
			sendEmailMulipleFiles(getContext(), "lmiguelvargasf@gmail.com", "Coordinates", "Hi, see the attached.\n\nLove,\nSikae bot.", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//******TEST
		//new ConnectAndUploadTask().execute();
		Toast.makeText(this.getContext(), R.string.upload_completed, Toast.LENGTH_LONG).show();
	}

	//
	public static void sendEmailMulipleFiles(Context context, String toAddress, String subject, String body, ArrayList<String> attachmentPath) throws Exception {
		try {
			Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra(Intent.EXTRA_EMAIL, new String[] { toAddress });
			intent.putExtra(Intent.EXTRA_SUBJECT, subject);
			intent.putExtra(Intent.EXTRA_TEXT, body);
			intent.setType("message/rfc822");
			PackageManager pm = context.getPackageManager();
			List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
			ResolveInfo best = null;
			for (final ResolveInfo info : matches) {
				if (info.activityInfo.packageName.contains(".gm.") || info.activityInfo.name.toLowerCase().contains("gmail"))
					best = info;
			}
			ArrayList<Uri> uri = new ArrayList<Uri>();
			for (int i = 0; i < attachmentPath.size(); i++) {
				File file = new File(attachmentPath.get(i));
				uri.add(Uri.fromFile(file));
			}

			intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uri);


			if (best != null)
				intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);

			context.startActivity(Intent.createChooser(intent, "Choose an email application..."));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	//
	// Just calling the method for upload via FTP
	private class ConnectAndUploadTask extends AsyncTask<Void,Void,Void>{
		@Override
		protected Void doInBackground(Void... params) {			
				ConnectAndUpload.connnectingwithFTP();			
			return null;			
		}
	}
	
}
