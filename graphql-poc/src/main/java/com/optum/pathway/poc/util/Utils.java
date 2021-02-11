package com.optum.pathway.poc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class Utils {
	private static Gson gson = null;
	
	public static boolean isBlankorNull(String str){
		if(str != null && !"".equals(str))
			return false;
		else
			return true;
	}
	
	public static boolean isBlankorNull(StringBuilder str){
		if(str != null && !"".equals(str.toString()))
			return false;
		else
			return true;
	}
	
	public static void safeClose(Closeable os, Logger log){
		 if (os != null) {
		    try {
		      os.close();
		    } catch (IOException e) {
		      log.error(e);
		    }
		  }
		}

	
	public static String formatDate(String date, String inputFormat, String opFormat){
		try{
			if(Utils.isBlankorNull(date))
				return "";
			
			SimpleDateFormat inDateFormat = new SimpleDateFormat(inputFormat) ;
			Date inpDate = inDateFormat.parse(date);
			SimpleDateFormat opDateFormat = new SimpleDateFormat(opFormat) ;
			return opDateFormat.format(inpDate);
		}catch(ParseException e){
			return date;
		}		
	}
	
	public static String getUUID() {
		String uniqueID = UUID.randomUUID().toString();
		long ts = System.currentTimeMillis();
		uniqueID = uniqueID.substring(0,uniqueID.lastIndexOf("-"))+"-"+ts;
		return uniqueID;

	}
	
	public static Gson getGson() {
		if(gson == null)
			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
		
		return gson;
	}
	public static Timestamp roundedTimeStamp(Timestamp timestamp){

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		return new Timestamp(cal.getTimeInMillis());

	}
	public static String getCurrentTimeInUTC(){
		TimeZone timeZone =TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		String time=new Timestamp(cal.getTimeInMillis()).toString();
		TimeZone.setDefault(timeZone);
		return time;
	}

	public static String changeMilliFormat(String date){
		String datePart =date.substring(date.lastIndexOf(".")+1);
		if(datePart.length()==1) {
			date = date + "00";
			return date;
		}
		else if(datePart.length()==2) {
			date = date + "0";
			return date;
		}
		else {
			return date;
		}
	}
}
