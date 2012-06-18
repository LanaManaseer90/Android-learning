/**
 * 
 */
package cn.shoppon.demos.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
public class Pull extends ListActivity {
	private LinearLayout mLoadLayout;
	private ListView mListView;
	private final LayoutParams mProgressBarLayoutParams = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	private final LayoutParams mTipContentLayoutParams = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	private ListViewAdapter mListViewAdapter;
	private TextView overlay;
	private boolean mGetMore = false; // 用于监听是否到最后
	private boolean visible = false; // 用于监听是否滑动已经停止
	private static int ALLCOUNT = 41;
	private List<String> list;
	private Handler mHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		list = new ArrayList<String>();
		overlay = (TextView) View.inflate(this, R.layout.overlay, null);
		getWindowManager().addView(
				overlay,
				new WindowManager.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						WindowManager.LayoutParams.TYPE_APPLICATION, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
								| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, PixelFormat.TRANSLUCENT));
		loadData();
		mListViewAdapter = new ListViewAdapter(list);
		/*
		 * 加载项布局，此布局被添加到ListVIew的Footer中
		 */
		mLoadLayout = new LinearLayout(this);
		mLoadLayout.setMinimumHeight(60);
		mLoadLayout.setGravity(Gravity.CENTER);
		mLoadLayout.setOrientation(LinearLayout.HORIZONTAL);
		/*
		 * 向加载项中加载一个圆形进度条
		 */
		ProgressBar mProgressBar = new ProgressBar(this);
		mProgressBar.setPadding(0, 0, 15, 0);
		mLoadLayout.addView(mProgressBar, mProgressBarLayoutParams);
		/*
		 * 向加载项中添加提示信息
		 */
		TextView mTipContent = new TextView(this);
		mTipContent.setText("Loading...");
		mLoadLayout.addView(mTipContent, mTipContentLayoutParams);
		/*
		 * 获取ListView组件，并将加载项加入到组件中的Footer中
		 */
		mListView = getListView();
		mListView.addFooterView(mLoadLayout);
		/*
		 * 设置list组件的adapter,并设置滑动监听事件
		 */
		mListView.setAdapter(mListViewAdapter);
		mListView.setOnScrollListener(new onScroolListener());

		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					if (list.size() <= ALLCOUNT) {
						loadData();
					} else {
						mListView.removeFooterView(mLoadLayout);
					}
					mListViewAdapter.notifyDataSetChanged();

					break;
				}
			}
		};

	}

	private void loadData() {
		int start = list.size();
		for (int i = 0; i < 10; i++) {
			list.add(start + i + " Item");
		}
	}

	class ListViewAdapter extends BaseAdapter {
		public List<String> list;
		int count = 10;

		public ListViewAdapter(List<String> data) {
			list = data;
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

		public View getView(int position, View convertView, ViewGroup parent) {
			TextView mTextView;
			if (convertView == null) {
				mTextView = new TextView(Pull.this);
			} else {
				mTextView = (TextView) convertView;
			}
			mTextView.setText(list.get(position));
			mTextView.setTextSize(20f);
			mTextView.setHeight(60);
			return mTextView;
		}
	}

	class onScroolListener implements OnScrollListener {

		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			mGetMore = false;

			if (firstVisibleItem + visibleItemCount == totalItemCount) {
				mGetMore = true;
			}
			if (visible) {
				overlay.setText(list.get(firstVisibleItem + visibleItemCount / 2).substring(0, 1));
				overlay.setVisibility(View.VISIBLE);
			}
		}

		public void onScrollStateChanged(AbsListView view, int scrollState) {
			visible = true;
			if (mGetMore && scrollState == OnScrollListener.SCROLL_STATE_IDLE)
				mHandler.sendEmptyMessageDelayed(0, 1000);
			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
				overlay.setVisibility(View.INVISIBLE);
				visible = false;
			}
		}

	}

}
