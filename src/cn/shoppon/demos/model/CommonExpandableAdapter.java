/**
 * 
 */
package cn.shoppon.demos.model;

import java.util.List;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * 
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012. All rights reserved.
 * </p>
 * 
 * @author <a href="www.shoppon.cn">Shoppon</a>
 * @version 1.0
 * @see
 */
public class CommonExpandableAdapter extends BaseExpandableListAdapter {
	private LayoutInflater mInflater;

	private List<String> mGroups;
	private List<List<String>> mChildren;

	public CommonExpandableAdapter(Context context, List<String> groups, List<List<String>> children) {
		mInflater = LayoutInflater.from(context);
		mGroups = groups;
		mChildren = children;
	}

	@Override
	public int getGroupCount() {
		return mGroups == null ? 0 : mGroups.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mChildren.get(groupPosition) == null ? 0 : mChildren.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mGroups.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mChildren.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		final String item = mGroups.get(groupPosition);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.simple_expandable_list_item_1, null);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.text1);
		textView.setText(item);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		String item = mChildren.get(groupPosition).get(childPosition);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.simple_expandable_list_item_2, null);
		}

		TextView textView = (TextView) convertView.findViewById(R.id.text1);
		textView.setText(item);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
