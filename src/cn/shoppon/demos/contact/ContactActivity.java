/**
 * 
 */
package cn.shoppon.demos.contact;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.ListView;
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
public class ContactActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);

		Cursor cursor = getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
		ContactAdapter contactAdapter = new ContactAdapter(this, cursor);

		ListView listView = (ListView) findViewById(R.id.contact_list);
		listView.setAdapter(contactAdapter);
	}
}
