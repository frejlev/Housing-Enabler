package ucn.datamatiker.afr_rmlp.housingenabler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		// Add event listener to op1 btn
		Button opt1Btn = (Button)findViewById(R.id.btn1main);
		opt1Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				// Method Call Here
			}
		});
		
		 
		// Add event listener to op2 btn
		Button opt2Btn = (Button)findViewById(R.id.btn2main);
		opt1Btn.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				// Method Call Here
			}
		});
	
		
		// Add event listener to op3 btn
		Button opt3Btn = (Button)findViewById(R.id.btn3main);
		opt1Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				// Method Call Here
			}
		});
				
					
		// Add event listener to op4 btn
		Button opt4Btn = (Button)findViewById(R.id.btn4main);
		opt1Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				// Method Call Here
			}
		});
				
					
		// Add event listener to op5 btn
		Button opt5Btn = (Button)findViewById(R.id.btn5main);
		opt1Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				// Method Call Here
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
}