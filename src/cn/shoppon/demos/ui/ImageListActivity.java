package cn.shoppon.demos.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import cn.shoppon.demos.R;
import cn.shoppon.demos.model.ImageAdapter;
import cn.shoppon.demos.utils.ImageDownloader;

public class ImageListActivity extends ListActivity implements RadioGroup.OnCheckedChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.imagelist);

		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(this);

		setListAdapter(new ImageAdapter());
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		ImageDownloader.Mode mode = ImageDownloader.Mode.NO_ASYNC_TASK;

		if (checkedId == R.id.correctButton) {
			mode = ImageDownloader.Mode.CORRECT;
		} else if (checkedId == R.id.randomButton) {
			mode = ImageDownloader.Mode.NO_DOWNLOADED_DRAWABLE;
		}

		((ImageAdapter) getListAdapter()).getImageDownloader().setMode(mode);
	}
}
