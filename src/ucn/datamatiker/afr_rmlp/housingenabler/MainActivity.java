package ucn.datamatiker.afr_rmlp.housingenabler;

// Import Statements
import ucn.datamatiker.afr_rmlp.housingenabler.datasources.UsersDataSource;
import ucn.datamatiker.afr_rmlp.housingenabler.models.User;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private UsersDataSource userDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		userDataSource = new UsersDataSource(this);
		userDataSource.open();
		
		// Add eventlistener to login btn
		Button loginBtn = (Button)findViewById(R.id.button1);
		loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
            	doOnLoginPress();
            }
        });
		
		//FragmentManager fragmentManager = getFragmentManager();
		//FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		//fragmentTransaction.commit();
	}
	
	private void doOnLoginPress() {
    	
    	EditText usernameField = (EditText)findViewById(R.id.editText1);
		EditText passwordField = (EditText)findViewById(R.id.editText2);
		
		User user = new User(usernameField.getText().toString(), passwordField.getText().toString());
		
		TextView testing = (TextView) findViewById(R.id.testingText);
		
		Log.d("MainActivity", "Got into the check method...");
		
		if (userDataSource.checkLogin(user)) {
			testing.setText("Correct login");
			gotoMainMenu();
		} else {
			testing.setText("Login faild");
		}
		
	}
	
	public void gotoMainMenu() {
		Intent intent = new Intent(this, MainMenu.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		userDataSource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		userDataSource.close();
		super.onPause();
	}

}