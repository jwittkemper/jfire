package biz.wittkemper.jfire.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	static SimpleDateFormat format = new SimpleDateFormat( "dd-MMM-yyyy" );
	static SimpleDateFormat formatlong = new SimpleDateFormat("dd.MMM.yyyy HH:mm");
	
	public static Date getDate(String date) throws ParseException{
			return format.parse(date);
	}

	public static String getCurDateString(){
		Date date = new Date();
		return formatlong.format(date);
	}
	public static String getCurDateString(Date date){
		return format.format(date);
	}
}
