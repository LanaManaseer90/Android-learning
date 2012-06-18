/**
 * 
 */
package cn.shoppon.demos.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import cn.shoppon.demos.R;

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
 * @version
 * @see
 */
public class CheckAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<String> mItems;
	private List<Boolean> mSelects;

	public CheckAdapter(Context context, List<String> items) {
		mItems = items;
		mInflater = LayoutInflater.from(context);
		mSelects = new ArrayList<Boolean>();
		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				mSelects.add(i, false);
			}
		}
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mItems == null ? 0 : mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		String item = mItems.get(position);
		final ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.check_adapter, null);
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView.findViewById(R.id.check_list_tv);
			viewHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.check_list_checkbox);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mTextView.setText(item);
		viewHolder.mCheckBox.setChecked(mSelects.get(position));
		viewHolder.mCheckBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mSelects.set(position, !mSelects.get(position));
			}
		});
		return convertView;
	}

	public List<String> getmItems() {
		return mItems;
	}

	public void setmItems(List<String> mItems) {
		this.mItems = mItems;
	}

	public List<Boolean> getmSelects() {
		return mSelects;
	}

	public void selectAll(boolean checked) {
		for (int i = 0; i < mSelects.size(); i++) {
			mSelects.set(i, checked);
		}
		super.notifyDataSetChanged();
	}

	static class ViewHolder {
		TextView mTextView;
		CheckBox mCheckBox;
	}

}
