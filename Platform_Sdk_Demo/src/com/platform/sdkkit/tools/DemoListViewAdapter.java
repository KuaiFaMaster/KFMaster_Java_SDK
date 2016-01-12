package com.platform.sdkkit.tools;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


/**
 * <li>File Name: DemoListViewAdapter.java</li>
 * <li>File Description: </li>
 * <li>Company: </li>
 * <li>Create Time: 2015-8-19 上午11:14:27</li>
 * @version 1.0.0
 * @author  HooRang
 */
public class DemoListViewAdapter extends BaseExpandableListAdapter {

	 SparseArray<String> sParentObj = new SparseArray<String>();
	 List<List<String>> sChildrenObj = new ArrayList<List<String>>();
	
	 Activity sContext ;
	 
	public DemoListViewAdapter(Activity activity, SparseArray<String> sParentObj ,List<List<String>> sChildrenObj ){
		this.sContext = activity;
		this.sParentObj = sParentObj;
		this.sChildrenObj = sChildrenObj;
		
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return sChildrenObj.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		String content = sChildrenObj.get(groupPosition).get(childPosition);
		return sGenerateView(content);
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return sChildrenObj.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return sParentObj.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return sParentObj.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,	View convertView, ViewGroup parent) {
		String content = sParentObj.get(groupPosition);
		return sGenerateView(content);
	}

	@Override
	public boolean hasStableIds() {return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	
	
	private TextView sGenerateView(String content){
		
		AbsListView.LayoutParams layoutParams = new  AbsListView.LayoutParams(  
                ViewGroup.LayoutParams.MATCH_PARENT, 100 );  
        TextView text = new  TextView(sContext);  
        text.setLayoutParams(layoutParams);  
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);  
        text.setPadding(70 , 0 , 0 , 0 );  
        text.setText(content);  
        return  text;  
	}

}


