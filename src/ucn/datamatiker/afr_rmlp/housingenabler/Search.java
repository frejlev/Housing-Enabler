package ucn.datamatiker.afr_rmlp.housingenabler;

import java.util.List;

import ucn.datamatiker.afr_rmlp.housingenabler.datasources.ResultDataSource;
import ucn.datamatiker.afr_rmlp.housingenabler.models.Result;
import android.os.Bundle;
import android.app.ListActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class Search extends ListActivity {
	
	private ResultDataSource resultDataSource;
	private EditText searchText;
	private List<Result> results;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_search);
		
		// Get views
		//EditText editTextNewToDo = (EditText)findViewById(R.id.search);
		//ListView listViewSearchResults = (ListView)findViewById(R.id.android.R.id.list);
		
		resultDataSource = new ResultDataSource(this);
		resultDataSource.open();
		
		results = resultDataSource.searchCaseInfo("");
		
		final ArrayAdapter<Result> resultAdapter = new ArrayAdapter<Result>(this, android.R.layout.simple_list_item_1, results);
		//listViewSearchResults.setAdapter(resultAdapter);
		
		searchText = (EditText)findViewById(R.id.search);
		searchText.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				results = resultDataSource.searchCaseInfo(searchText.getText().toString());
				
				resultAdapter.clear();
				
				resultAdapter.addAll(results);
				resultAdapter.notifyDataSetChanged();
				
				Log.d("search", searchText.getText().toString());
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    });
		
		setListAdapter(resultAdapter);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	
	@Override
	protected void onResume() {
		resultDataSource.open();
		super.onResume();
	}
	

	@Override
	protected void onPause() {
		resultDataSource.close();
		super.onPause();
	}

}
