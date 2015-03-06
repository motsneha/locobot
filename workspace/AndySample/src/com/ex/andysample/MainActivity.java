package com.ex.andysample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	private String category;
	private AndyService andy; 
	private String result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onAtm()
	{
		category="atm";
		andy=new AndyService(category);
		result=andy.getJason();
		Bundle bundle=new Bundle();
		bundle.putString("param1", result);		
		Intent intent=new Intent(this,ResultActivity.class);
		intent.putExtras(bundle);
		startActivityForResult(intent,0);
	}

}
