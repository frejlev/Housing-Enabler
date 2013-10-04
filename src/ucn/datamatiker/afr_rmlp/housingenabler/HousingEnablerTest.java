package ucn.datamatiker.afr_rmlp.housingenabler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ucn.datamatiker.afr_rmlp.housingenabler.helpers.CalculationHelper;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputFilter.LengthFilter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class HousingEnablerTest extends Activity {
	
	List<String> groupList;
    List<String> childList;
    Map<String, List<String>> questionCollection;
    ExpandableListView expListView;
	
	CalculationHelper calHelp;
	private Integer[] testResult;
	private Integer[] testUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_housing_enabler_test);
		
		calHelp = new CalculationHelper(this);
		
		this.createGroupList();
		this.createCollection();
		
        expListView = (ExpandableListView) findViewById(R.id.question_list);
        final ucn.datamatiker.afr_rmlp.housingenabler.adapters.ExpandableListAdapter expListAdapter = new ucn.datamatiker.afr_rmlp.housingenabler.adapters.ExpandableListAdapter(this, groupList, questionCollection);
        expListView.setAdapter(expListAdapter);
        
        expListView.setOnChildClickListener(new OnChildClickListener() {
        	 
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG).show();
 
                return true;
            }
        });
		
		testResult = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 	0, 0, 0, 1, 1, 1, 1, 0, 	0, 0, 1, 0, 0, 0, 0, 0, 	0, 0, 0, 0, 	0, 0, 0, 0, 	0, 0, 1, 1, 1, 0, 1, 0, 	1, 0, 0, 1, 1, 0, 0, 1, 0, 		0, 0, 0, 0, 0, 1, 0, 1, 	1, 0, 0, 0, 0, 0, 1, 0, 0, 		1, 0, 1, 0, 0, 0, 0, 0, 	0, 1, 0, 0, 0, 0, 0, 	0, 0, 0, 1, 0, 0, 1, 1, 	0, 0, 1, 0, 0, 0, 	0, 0, 1, 0, 1, 0, 1, 0, 	0, 1, 0, 1, 1, 0, 1, 0, 	1, 1, 0, 0, 0, 1, 1, 	0, 0, 0, 0, 0, 1, 0, 	1, 1, 0, 0, 0, 1, 1, 1, 0, 		0, 0, 1, 0, 0, 1, 0, 	0, 0, 1, 0, 1, 0, 1, 	1, 0, 0, 0, 1, 1, 0, 1, 1, 		0, 0, 1, 0 };
		testUser = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0 };
		
		this.printTotal();
	}
	

	
	private int printTotal() {
		
		Integer[] total = calHelp.generateTotal(testUser, testResult);
		Integer superTotal = 0;
		
		int index = 0;
		while (index < total.length) {
			if (index == 0) {
				//result.setText(total[index].toString() + " ");
			} else {
				//result.append(total[index].toString() + " ");
			}
			superTotal += total[index];
			index++;
		}
		
		//superTotalView.setText("Total: " + superTotal.toString());
		
		return 0;
	}
	
	private void createGroupList() {
        groupList = calHelp.getAllQuestions();
		//groupList = new ArrayList<String>();
		//groupList.add("Test");
	}
 
    private void createCollection() {
        // preparing question collection(child)
        ArrayList<String> models = new ArrayList<String>(); 
        models.add("Ja");
        models.add("Nej");
        models.add("Ej vurderet");
 
        questionCollection = new LinkedHashMap<String, List<String>>();
 
        for (String question : groupList) {
            questionCollection.put(question, models);
        }
    }
 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.housing_enabler_test, menu);
		return true;
	}

}
