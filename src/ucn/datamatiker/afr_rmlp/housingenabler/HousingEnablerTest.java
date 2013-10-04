package ucn.datamatiker.afr_rmlp.housingenabler;

import ucn.datamatiker.afr_rmlp.housingenabler.helpers.CalculationHelper;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class HousingEnablerTest extends Activity {
	
	CalculationHelper calHelp;
	private Integer[] testResult;
	private Integer[] testUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_housing_enabler_test);
		
		calHelp = new CalculationHelper();
		testResult = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 	0, 0, 0, 1, 1, 1, 1, 0, 	0, 0, 1, 0, 0, 0, 0, 0, 	0, 0, 0, 0, 	0, 0, 0, 0, 	0, 0, 1, 1, 1, 0, 1, 0, 	1, 0, 0, 1, 1, 0, 0, 1, 0, 		0, 0, 0, 0, 0, 1, 0, 1, 	1, 0, 0, 0, 0, 0, 1, 0, 0, 		1, 0, 1, 0, 0, 0, 0, 0, 	0, 1, 0, 0, 0, 0, 0, 	0, 0, 0, 1, 0, 0, 1, 1, 	0, 0, 1, 0, 0, 0, 	0, 0, 1, 0, 1, 0, 1, 0, 	0, 1, 0, 1, 1, 0, 1, 0, 	1, 1, 0, 0, 0, 1, 1, 	0, 0, 0, 0, 0, 1, 0, 	1, 1, 0, 0, 0, 1, 1, 1, 0, 		0, 0, 1, 0, 0, 1, 0, 	0, 0, 1, 0, 1, 0, 1, 	1, 0, 0, 0, 1, 1, 0, 1, 1, 		0, 0, 1, 0 };
		testUser = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0 };
		
		
		this.printTotal();
	}
	

	
	private int printTotal() {
		
		TextView result = (TextView)findViewById(R.id.textView1);
		TextView superTotalView = (TextView)findViewById(R.id.textView2);
		
		Integer[] total = calHelp.generateTotal(testUser, testResult);
		Integer superTotal = 0;
		
		int index = 0;
		while (index < total.length) {
			if (index == 0)
				result.setText(total[index].toString() + " ");
			else
				result.append(total[index].toString() + " ");
			
			superTotal += total[index];
			index++;
		}
		
		superTotalView.setText("Total: " + superTotal.toString());
		
		return 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.housing_enabler_test, menu);
		return true;
	}

}
