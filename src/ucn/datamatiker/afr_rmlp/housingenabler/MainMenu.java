package ucn.datamatiker.afr_rmlp.housingenabler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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
		opt1Btn.setText(Html.fromHtml(getString(R.string.opt1)));
		opt1Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				// Method Call Here
			}
		});
		
		 
		// Add event listener to op2 btn
		Button opt2Btn = (Button)findViewById(R.id.btn2main);
		opt2Btn.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				// Method Call Here
			}
		});
	
		
		// Add event listener to op3 btn
		Button opt3Btn = (Button)findViewById(R.id.btn3main);
		opt3Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				gotoCreateClient();
			}
		});
		
		Button opt4Btn = (Button)findViewById(R.id.btn4main);
		opt4Btn.setOnClickListener(new OnClickListener() {
			
            @Override
            public void onClick(final View v) {
            	gotoTest();
            }
        });

	}

	
	// Add event listener to op4 btn
	public void gotoTest() {
		Intent intent = new Intent(this, HousingEnablerTest.class);
		startActivity(intent);
	}
	
	public void gotoCreateClient() {
		Intent intent = new Intent(this, CreateClient.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
}
