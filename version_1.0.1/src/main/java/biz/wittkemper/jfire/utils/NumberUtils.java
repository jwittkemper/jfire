package biz.wittkemper.jfire.utils;

public class NumberUtils {
	
	public boolean isLongValue(String text){
		
		try{
			long lo = Long.parseLong(text);
		}catch (NumberFormatException e ){
			return false;
		}
		return true;
	}

}
