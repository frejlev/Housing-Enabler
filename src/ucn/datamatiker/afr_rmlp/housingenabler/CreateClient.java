package ucn.datamatiker.afr_rmlp.housingenabler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreateClient extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_client);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_client, menu);
		return true;
	}

}
