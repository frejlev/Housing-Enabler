package ucn.datamatiker.afr_rmlp.housingenabler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ucn.datamatiker.afr_rmlp.housingenabler.datasources.ResultDataSource;
import ucn.datamatiker.afr_rmlp.housingenabler.datasources.UsersDataSource;
import ucn.datamatiker.afr_rmlp.housingenabler.helpers.CalculationHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputFilter.LengthFilter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

public class HousingEnablerTest extends Activity {
	
	private int lastExpandedPosition = -1;
	View _lastColored;
	List<String> groupList;
    List<String> childList;
    Map<String, List<String>> questionCollection;
    ExpandableListView expListView;
	
	CalculationHelper calHelp;
	private Integer[] testResult;
	private Integer[] testUser;
	
	int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mPrefs;
    
    ArrayList<Integer> resultArray;
	
	private ResultDataSource resultDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences mPrefs = getSharedPreferences("appPrefs", mode);
		
		setContentView(R.layout.activity_housing_enabler_test);
		
		resultDataSource = new ResultDataSource(this);
		resultDataSource.open();
		
		Log.d("Housing test", String.valueOf(mPrefs.getInt("resultId", 0)));
		
		resultArray = new ArrayList<Integer>();
		
		if (mPrefs.getInt("resultId", 0) == 0) {
			resultArray = resultDataSource.createResult();
			Log.d("In the test class", resultArray.toString());
		} else {
			resultArray = resultDataSource.getResult(mPrefs.getInt("resultId", 0));
		}
		
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
                
                /**
                 * for(int i=0; i<((ViewGroup)v).getChildCount(); ++i) {
                    View nextChild = ((ViewGroup)v).getChildAt(i);
                    ((TextView)nextChild).setText(((TextView)nextChild).getText() + " (Valgt)");
                }
                */
                
                expListAdapter.setChild(groupPosition, childPosition);
                expListAdapter.notifyDataSetChanged();
                
                
                // Update database
                String columnToUpdate = calHelp.getQuestionKeys().get(groupPosition);
                int idToUpdate = resultArray.get(resultArray.size() - 1);
                int theValue = 0;
                
                if (childPosition == 0) {
                	theValue = 1; // Yes
                } else if (childPosition == 1) {
                	theValue = 2; // No
                } else if (childPosition == 2) {
                	theValue = 3; // Not selected
                }
                
                resultDataSource.updateResult(idToUpdate, columnToUpdate, theValue);
                
               // Log.d("something", String.valueOf(((ViewGroup)v).getChildCount()));
                
                /**
                if(_lastColored != null) {
	                _lastColored.setBackgroundColor(Color.TRANSPARENT);
	                _lastColored.invalidate();
                }
                _lastColored = v;
                v.setBackgroundColor(Color.rgb(214, 214, 214));
                */
                
                //Toast.makeText(getBaseContext(), selected, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getBaseContext(), String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
 
                return false;
            }
        });
        
  
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                    if (lastExpandedPosition != -1
                            && groupPosition != lastExpandedPosition) {
                    	expListView.collapseGroup(lastExpandedPosition);
                    }
                    lastExpandedPosition = groupPosition;
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
        questionCollection = new LinkedHashMap<String, List<String>>();
 
        int count = 0;
        for (String question : groupList) {
        	
        	ArrayList<String> models = new ArrayList<String>();
        	
        	String val1 = "Ja";
        	String val2 = "Nej";
        	String val3 = "Ej vurderet";
            
        	if (resultArray.get(count) == 1) {
        		val1 = "Ja (Valgt)";
        	} else if (resultArray.get(count) == 2) {
        		val2 = "Nej (Valgt)";
        	} else if (resultArray.get(count) == 3) {
        		val3 = "Ej vurderet (Valgt)";
        	}
        	
        	models.add(val1);
        	models.add(val2);
        	models.add(val3);

            questionCollection.put(question, models);
           
            count++;
            
        }
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.housing_enabler_test, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		resultDataSource.open();
		super.onResume();
	}
	

	@Override
	protected void onPause() {
		
		mPrefs = getSharedPreferences("appPrefs", mode);
		SharedPreferences.Editor ed = mPrefs.edit();
		
		if (resultArray.size() > 0) {
			ed.putInt("resultId", resultArray.get(resultArray.size() - 1));
			ed.commit();
		}
		
		resultDataSource.close();
		super.onPause();
	}

}
