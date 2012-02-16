package biz.wittkemper.jfire.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	static SimpleDateFormat format = new SimpleDateFormat( "dd-MMM-yyyy" );
	
	public static Date getDate(String date){
		try {
			return format.parse(date);
		} catch (ParseException e) {
			System.out.println("Datum falsch: " + e.getMessage());
			return null;
		}
	}

	public static String getCurDateString(){
		SimpleDateFormat form = new SimpleDateFormat("dd.MMM.yyyy HH:mm");
		Date date = new Date();
		return form.format(date);
	}
}
