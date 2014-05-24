package com.Hamp.SmsBackUp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;
import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;

import com.Hamp.SmsBackUp.domain.SmsInfo;

public class SmsBackUpUitl {

	public static void backUp(List<SmsInfo> smsInfos,Context context){
		try {
			XmlSerializer serializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(),"smsBackUp.xml");
			FileOutputStream fos = new FileOutputStream(file);
			serializer.setOutput(fos, "utf-8");
			serializer.startDocument("utf-8", true);
			serializer.startTag(null, "Sms");
			for(SmsInfo smsInfo:smsInfos){
				serializer.startTag(null, "sms");
				
				serializer.startTag(null, "Number");
				serializer.text(smsInfo.getAddress());
				serializer.endTag(null,"Number");
				
				serializer.startTag(null, "Text");
				serializer.text(smsInfo.getBody());
				serializer.endTag(null, "Text");
				
				serializer.startTag(null, "Date");
				serializer.text(smsInfo.getDate()+"");
				serializer.endTag(null, "Date");
				
				serializer.endTag(null, "sms");
			}
			serializer.endTag(null, "Sms");
			serializer.endDocument();
			Toast.makeText(context, "عملیات پشتیبان گیری با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, "عملیات پشتیبان گیری ناموفق بود", Toast.LENGTH_SHORT).show();
		}
		
	}
}