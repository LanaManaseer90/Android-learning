package cn.shoppon.demos.model;

import java.util.ArrayList;

import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.shoppon.demos.bean.DetailProcess;
import cn.shoppon.demos.bean.ProcessInfo.PsRow;
import cn.shoppon.demos.ui.TaskManager;
import cn.shoppon.demos.utils.MiscUtil;
import cn.shoppon.demos.R;

public class TaskListAdapters {

	public final static class ProcessListAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private ArrayList<DetailProcess> list;
		private TaskManager ctx;
		private PackageManager pm;

		public ProcessListAdapter(TaskManager context, ArrayList<DetailProcess> list) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);

			this.list = list;
			this.ctx = context;
			this.pm = ctx.getPackageManager();
		}

		public int getCount() {
			return list.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.taskmanager_list_main, null);

				holder = new ViewHolder();
				holder.icon = (ImageView) convertView.findViewById(R.id.list_icon);
				holder.text_name = (TextView) convertView.findViewById(R.id.list_name);
				holder.text_size = (TextView) convertView.findViewById(R.id.list_size);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			final DetailProcess dp = list.get(position);
			convertView.setVisibility(View.VISIBLE);
			// String cmd = dp.getRuninfo().processName;

			holder.icon.setImageDrawable(dp.getAppinfo().loadIcon(pm));
			holder.text_name.setText(dp.getTitle());
			PsRow row = dp.getPsrow();
			if (row == null) {
				holder.text_size.setText(R.string.memory_unknown);
			} else {
				holder.text_size.setText((int) Math.ceil(row.mem / 1024) + "K");
			}

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View view) {
					MiscUtil.getTaskMenuDialog(ctx, dp).show();
				}

			});

			return convertView;
		}

	}

	private static class ViewHolder {
		ImageView icon;
		TextView text_name;
		TextView text_size;
	}

}
