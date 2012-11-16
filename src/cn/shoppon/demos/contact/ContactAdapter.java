package cn.shoppon.demos.contact;

import java.io.InputStream;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
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
public class ContactAdapter extends CursorAdapter {
	private LayoutInflater mLayoutInflater;

	public ContactAdapter(Context context, Cursor c) {
		super(context, c);
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return mLayoutInflater.inflate(R.layout.contact_item, parent, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		String number = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
		TextView phone = (TextView) view.findViewById(R.id.contact_phone);
		phone.setText(number);
		String displayName = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
		TextView name = (TextView) view.findViewById(R.id.contact_name);
		name.setText(displayName);

		Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, cursor.getLong(cursor.getColumnIndex(Contacts._ID)));
		InputStream inputStream = Contacts.openContactPhotoInputStream(context.getContentResolver(), uri);
		Bitmap contactImage = BitmapFactory.decodeStream(inputStream);
		ImageView image = (ImageView) view.findViewById(R.id.contact_image);
		image.setImageBitmap(contactImage);
	}
}
