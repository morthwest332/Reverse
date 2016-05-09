package liu.time_back;

import java.util.Date;

import liu.view.TDView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	//private TimeView view;
	private TDView view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		view=(TimeView) findViewById(R.id.time);
	
		view.setTime(new Date());*/
		
		/*new Thread(view).start();*/
	view=(TDView) findViewById(R.id.back);
	new Thread(view).start();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
