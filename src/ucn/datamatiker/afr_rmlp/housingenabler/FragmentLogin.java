package ucn.datamatiker.afr_rmlp.housingenabler;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentLogin extends Fragment {
	/**OnLoginPressListener mCallback;
	
	// Container Activity must implement this interface
    public interface OnLoginPressListener {
        public void onLoginClick(String username, String password);
    }*/
	
	// Fragment Code here
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_login, container, false);
		
		// Add eventlistener to login btn
		/**Button loginBtn = (Button) view.findViewById(R.id.button1);
		loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
            	
            	Log.d("FragmentLogin", "test login");
            	
            	EditText usernameField = (EditText)getView().findViewById(R.id.editText1);
    			EditText passwordField = (EditText)getView().findViewById(R.id.editText2);
    			
    			mCallback.onLoginClick(usernameField.getText().toString(), passwordField.getText().toString());
            }
        });
        
        * Add to mainActivity:  implements FragmentLogin.OnLoginPressListener
        * Implement methods in main activity...
        */
		
		return view;
	}
	
	/**@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        try {
            mCallback = (OnLoginPressListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }*/
	
}