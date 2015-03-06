package com.ex.andysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity{
	private String result;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.andyresult);
		
		
		 Intent receivedIntent=this.getIntent(); 
	        Bundle bundle= receivedIntent.getExtras();
	         result=bundle.getString("param1");
	         
	         TextView tw=(TextView)findViewById(R.id.resultView);
	         tw.setText(result);
	}
}
