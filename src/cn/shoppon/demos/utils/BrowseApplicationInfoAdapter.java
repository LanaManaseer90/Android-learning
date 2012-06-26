package cn.shoppon.demos.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.shoppon.demos.R;
import cn.shoppon.demos.bean.AppInfo;

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
public class BrowseApplicationInfoAdapter extends BaseAdapter {

	private List<AppInfo> mlistAppInfo;

	LayoutInflater mInflater;

	public BrowseApplicationInfoAdapter(Context context, List<AppInfo> apps) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mlistAppInfo = apps;
	}

	@Override
	public int getCount() {
		return mlistAppInfo.size();
	}

	@Override
	public Object getItem(int position) {
		return mlistAppInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		View view = null;
		ViewHolder holder = null;
		if (convertview == null || convertview.getTag() == null) {
			view = mInflater.inflate(R.layout.browse_app_item, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		} else {
			view = convertview;
			holder = (ViewHolder) convertview.getTag();
		}
		AppInfo appInfo = (AppInfo) getItem(position);
		holder.appIcon.setImageDrawable(appInfo.getAppIcon());
		holder.tvAppLabel.setText(appInfo.getAppLabel());
		holder.tvPkgName.setText(appInfo.getPkgName());
		return view;
	}

	class ViewHolder {
		ImageView appIcon;
		TextView tvAppLabel;
		TextView tvPkgName;

		public ViewHolder(View view) {
			this.appIcon = (ImageView) view.findViewById(R.id.imgApp);
			this.tvAppLabel = (TextView) view.findViewById(R.id.tvAppLabel);
			this.tvPkgName = (TextView) view.findViewById(R.id.tvPkgName);
		}
	}
}