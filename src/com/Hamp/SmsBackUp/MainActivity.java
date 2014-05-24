package com.Hamp.SmsBackUp;

import java.util.ArrayList;
import java.util.List;

import com.Hamp.SmsBackUp.domain.SmsInfo;
import com.Hamp.SmsBackUp.util.DateUtil;
import com.Hamp.SmsBackUp.util.SmsBackUpUitl;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button backup = null;
	SmsInfo smsInfo;
	List<SmsInfo> list = new ArrayList<SmsInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		backup = (Button) findViewById(R.id.backup);

		backup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				ContentResolver resolver = getContentResolver();
				Cursor cursor = resolver.query(Uri.parse("content://sms/"),
						new String[] { "address", "body", "date" }, null, null,
						"date");

				while (cursor.moveToNext()) {

					String address = cursor.getString(0);
					String body = cursor.getString(1);
					long date = cursor.getLong(2);
					String d = DateUtil.dateUtil(date);
					smsInfo = new SmsInfo(address, d, body);
					list.add(smsInfo);

				}
				cursor.close();
				SmsBackUpUitl.backUp(list, MainActivity.this);
			}
		});

	}

}
