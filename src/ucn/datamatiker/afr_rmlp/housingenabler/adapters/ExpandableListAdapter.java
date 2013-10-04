package ucn.datamatiker.afr_rmlp.housingenabler.adapters;

import java.util.List;
import java.util.Map;
 
import ucn.datamatiker.afr_rmlp.housingenabler.R;
 
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter {
 
    private Activity context;
    private Map<String, List<String>> questionCollections;
    private List<String> questions;
 
    public ExpandableListAdapter(Activity context, List<String> questions,
            Map<String, List<String>> laptopCollections) {
        this.context = context;
        this.questionCollections = laptopCollections;
        this.questions = questions;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return questionCollections.get(questions.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String question = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.question);
 
        Log.d("Expand", question);
 
        item.setText(question);
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return questionCollections.get(questions.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return questions.get(groupPosition);
    }
 
    public int getGroupCount() {
        return questions.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String questionText = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.groupitem,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.question);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(questionText);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}