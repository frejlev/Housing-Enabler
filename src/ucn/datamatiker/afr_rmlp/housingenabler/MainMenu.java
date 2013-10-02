package ucn.datamatiker.afr_rmlp.housingenabler;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		ListView listMenu = (ListView)findViewById(R.id.listMenu);
		
		LayoutParams lp = (LayoutParams)listMenu.getLayoutParams();
		
		final ArrayList<String> menuItems = new ArrayList<String>();
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems);
		listMenu.setAdapter(aa);
		
		menuItems.add("Opret ny sag");
		menuItems.add("Opret ny klient");
		menuItems.add("S¿g i sager");
		menuItems.add("S¿g i klienter");
		menuItems.add("Ikke f¾rdiggjorde sager");
		
		aa.notifyDataSetChanged();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}
