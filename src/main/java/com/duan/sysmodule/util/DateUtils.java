package com.duan.sysmodule.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtils{

	public DateUtils(){}

	public static Date strToDate(String time)throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = sdf.parse(time);
		return d;
	}
	
	public static Date getCurrentDate() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		return sdf.parse(dateStr);
	}
	
	public static Date getCurrentDateByFormat(String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(new Date());
		return sdf.parse(dateStr);
	}

	public static Date strToDate(String time, String format)throws ParseException{
		if(null == time || "".equals(time.trim())){
			return null;
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d = sdf.parse(time);
			return d;
		}
	}

	public static Date strToTime(String time)throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd:HH");
		Date d = sdf.parse(time);
		return d;
	}

	public static String getHourStr()throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		return sdf.format(new Date());
	}

	public static Date getDate(int year, int month, int day){
		GregorianCalendar d = new GregorianCalendar(year, month - 1, day);
		return d.getTime();
	}

	public static Date getDate(int yyyyMMdd){
		int dd = yyyyMMdd % 100;
		int yyyyMM = yyyyMMdd / 100;
		int mm = yyyyMM % 100;
		int yyyy = yyyyMM / 100;
		GregorianCalendar d = new GregorianCalendar(yyyy, mm - 1, dd);
		return d.getTime();
	}

	public static Date getDate(int year, int month, int day, int hour){
		GregorianCalendar d = new GregorianCalendar(year, month - 1, day, hour, 0);
		return d.getTime();
	}

	public static Date getRoundedHourCurDate(){
		Calendar cal = GregorianCalendar.getInstance();
		cal.clear(12);
		cal.clear(13);
		cal.clear(14);
		return cal.getTime();
	}

	public static Date getRoundedDayCurDate(){
		Calendar cal = new GregorianCalendar();
		return (new GregorianCalendar(cal.get(1), cal.get(2), cal.get(5))).getTime();
	}

	public static Date getRoundedHourDate(Date dt){
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		cal.clear(12);
		cal.clear(13);
		cal.clear(14);
		return cal.getTime();
	}

	public static Date getNextDay(Date dt){
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return (new GregorianCalendar(cal.get(1), cal.get(2), cal.get(5) + 1)).getTime();
	}

	public static Date getWeekDay(Date dt, int weekDay)
	{
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		if (weekDay == 7)
			weekDay = 1;
		else
			weekDay++;
		cal.set(7, weekDay);
		return cal.getTime();
	}

	public static Date getNextDay(Date dt, Long n)
	{
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return (new GregorianCalendar(cal.get(1), cal.get(2), cal.get(5) + n.intValue())).getTime();
	}

	public static Date getNextMonth(Date dt, Long n)
	{
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		Calendar firstCal = new GregorianCalendar(cal.get(1), cal.get(2) + n.intValue(), 1);
		if (firstCal.getActualMaximum(5) < cal.get(5))
			return (new GregorianCalendar(cal.get(1), cal.get(2) + n.intValue(), firstCal.getActualMaximum(5))).getTime();
		else
			return (new GregorianCalendar(cal.get(1), cal.get(2) + n.intValue(), cal.get(5))).getTime();
	}

	public static long getBetweenDate(Date startDate, Date endDate)
	{
		long startDateTime = startDate.getTime();
		long endDateTime = endDate.getTime();
		long dayTime = 0x5265c00L;
		long days = (endDateTime - startDateTime) / dayTime;
		return days;
	}

	public static long getMonthLength(String countDate)
		throws ParseException
	{
		String firstDay = (new StringBuilder(String.valueOf(countDate.substring(0, countDate.length() - 2)))).append("01").toString();
		Date startDate = strToDate(firstDay);
		Date endDate = getNextMonth(startDate, new Long(1L));
		long startDateTime = startDate.getTime();
		long endDateTime = endDate.getTime();
		long dayTime = 0x5265c00L;
		long days = (endDateTime - startDateTime) / dayTime;
		return days;
	}

	public static Date getNextDay()
	{
		Calendar cal = GregorianCalendar.getInstance();
		return (new GregorianCalendar(cal.get(1), cal.get(2), cal.get(5) + 1)).getTime();
	}

	public static Timestamp convertSqlDate(Date dt)
	{
		if (dt == null)
			return new Timestamp(0L);
		else
			return new Timestamp(dt.getTime());
	}

	public static String formatCurrrentDate()
	{
		Date pdate = new Date();
		return formatDate(pdate, "yyyyMMdd");
	}
	
	public static String dateStrToDateStr(String timeStr) throws ParseException{
		Date date = strToDate(timeStr,"yyyy-MM-dd");
		return formatDate(date, "yyyyMMdd");
	}

	public static String formatDate(Date pDate, String format)
	{
		if (pDate == null)
		{
			return "";
		} else
		{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(pDate);
		}
	}

	public static String getHour(Date pDate)
	{
		return formatDate(pDate, "HH");
	}

	/**
	 * 获取小时(24小时制)
	 * @param date
	 * @return
	 */
	public static Integer getHourVal(Date date){
		if(null == date){
			return null;
		}else{
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.HOUR_OF_DAY);
		}
	}
	
	/**
	 * 获取小时(24小时制)
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Integer getHour(String dateStr,String format){
		if(!isDate(dateStr, format)){
			return null;
		}else{
			Date date = getDateFromStr(dateStr, format);
			return getHourVal(date);
		}
	}
	
	public static Calendar getTheLastDayOfTheMonth(int year, int month){
		Calendar cal = new GregorianCalendar();
		cal.set(year, month, 1);
		return new GregorianCalendar(cal.get(1), cal.get(2), cal.get(5) - 1);
	}

	public static boolean validateDateString(String dateString){
		if (dateString == null || dateString.equals("")){
			return false;
		} else{
			String regDate = "^(((([02468][048])|([13579][26]))[0]{2})(02)(([0][1-9])|([1-2][0-9])))|(((([02468][1235679])|([13579][01345789]))[0]{2})(02)(([0][1-9])|([1][0-9])|([2][0-8])))|(([0-9]{2}(([0][48])|([2468][048])|([13579][26])))(02)(([0][1-9])|([1-2][0-9])))|(([0-9]{2}(([02468][1235679])|([13579][01345789])))(02)(([0][1-9])|([1][0-9])|([2][0-8])))|(([0-9]{4})(([0]{1}(1|3|5|7|8))|10|12)(([0][1-9])|([1-2][0-9])|30|31))|(([0-9]{4})(([0]{1}(4|6|9))|11)(([0][1-9])|([1-2][0-9])|30))$";
			return dateString.matches(regDate);
		}
	}

	public static boolean validateDateString(String dateString, String format)
	{
		if (dateString == null || dateString.equals(""))
			return false;
		if (format == null || format.equals(""))
			return false;
		//Date date = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		try{
			df.parse(dateString);
		}catch (Exception e){
			return false;
		}
		return true;
	}

	public static int compareDate(Date date1, Date date2, int precision)
	{
		Calendar c = Calendar.getInstance();
		List<Integer> fields = new ArrayList<Integer>();
		fields.add(new Integer(1));
		fields.add(new Integer(2));
		fields.add(new Integer(5));
		fields.add(new Integer(12));
		fields.add(new Integer(13));
		fields.add(new Integer(14));
		Date d1 = date1;
		Date d2 = date2;
		if (fields.contains(new Integer(precision)))
		{
			c.setTime(date1);
			for (int i = 0; i < fields.size(); i++)
			{
				int field = ((Integer)fields.get(i)).intValue();
				if (field > precision)
					c.set(field, 0);
			}

			d1 = c.getTime();
			c.setTime(date2);
			for (int i = 0; i < fields.size(); i++)
			{
				int field = ((Integer)fields.get(i)).intValue();
				if (field > precision)
					c.set(field, 0);
			}

			d2 = c.getTime();
		}
		return d1.compareTo(d2);
	}

	public static int compareDateByDay(Date date1, Date date2)
	{
		return BigDecimal.valueOf(date2.getTime() - date1.getTime()).divide(BigDecimal.valueOf(0x5265c00L)).intValue();
	}

	public static Date getLastNDay(Date dt, Long n)
	{
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return (new GregorianCalendar(cal.get(1), cal.get(2), cal.get(5) - n.intValue())).getTime();
	}

	/**
	 * 判断是否为历史时间
	 * == now 不为历史时间
	 * @param date
	 * @param format
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean isHistoryDate(Date date,String format)throws IllegalArgumentException{
		if(null == date || null == format || "".equals(format.trim())){
			throw new IllegalArgumentException();
		}
		Date now = getDateFromStr(formatDate(new Date(), format), format);
		if(now.getTime() > date.getTime()){
			return true;
		}
		return false;
	}
	
	/**
	 * 系统日期是否在指定区间内</br>
	 * if beginDate is null or endDate is null or format is null return false;</br>
	 * if beginDate.getTime() gt endDate.getTime() return false;</br>
	 * if now.getTime() < beginDate.getTime() || now.getTime() > endDate.getTime() return false;</br>
	 * if beginDate.getTime() = endDate.getTime() and now.getTime() != beginDate.getTime() return false;
	 * @param beginDate  开始日期
	 * @param endDate    结束日期
	 * @param format     对比日期格式
	 * @return
	 */
	public static boolean isSpecifiedInterval(Date beginDate,Date endDate,String format){
		if(null == beginDate || null == endDate || null == format || "".equals(format.trim())){
			return false;
		}
		beginDate = getDateFromStr(formatDate(beginDate, format), format);
		endDate = getDateFromStr(formatDate(endDate, format), format);
		if(beginDate.getTime() > endDate.getTime()){
			return false;
		}
		Date now = getDateFromStr(formatDate(new Date(), format), format);
		if(beginDate.getTime() == endDate.getTime()){
			if(now.getTime() != beginDate.getTime()){
				return false;
			}
		}else{
			if(now.getTime() < beginDate.getTime() || now.getTime() > endDate.getTime()){
				return false;
			}
		}
		return true;
	}
	
	public static Date getDateFromStr(String str, String format)
	{
		DateFormat df;
		if (null == format || "".equals(format.trim()))
			format = "yyyy-MM-dd HH:mm:ss";
		if (null == str || "".equals(str.trim()))
			return null;
		df = new SimpleDateFormat(format);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			throw new IllegalArgumentException("日期转换失败!");
		}
	}

	public static boolean isDate(String str, String format)
	{
		if (null == format || "".equals(format.trim()))
			format = "yyyy-MM-dd HH:mm:ss";
		if (null == str || "".equals(str.trim()))
			return false;
		DateFormat df = new SimpleDateFormat(format);
		try{
			df.parse(str);
		}catch (ParseException e){
			return false;
		}
		return true;
	}
	
	public static Date getDateForEighteenHour(Date dt){
		if (dt == null)
			dt = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		cal.set(11, 18);
		cal.set(12, 0);
		cal.set(13, 0);
		return cal.getTime();
	}

	public static Date getChangeDateAddTime(Date date, Date time, String type, int iQuantity)
	{
		int year = Integer.parseInt(formatDate(date, "yyyy"));
		int month = Integer.parseInt(formatDate(date, "MM"));
		month--;
		int day = Integer.parseInt(formatDate(date, "dd"));
		int hour = Integer.parseInt(formatDate(time, "HH"));
		int mi = Integer.parseInt(formatDate(time, "mm"));
		int ss = Integer.parseInt(formatDate(time, "ss"));
		GregorianCalendar gc = new GregorianCalendar(year, month, day, hour, mi, ss);
		if (iQuantity == 0 || type == null)
			return gc.getTime();
		if (type.equalsIgnoreCase("y"))
			gc.add(1, iQuantity);
		else
		if (type.equalsIgnoreCase("m"))
			gc.add(2, iQuantity);
		else
		if (type.equalsIgnoreCase("d"))
			gc.add(5, iQuantity);
		else
		if (type.equalsIgnoreCase("h"))
			gc.add(10, iQuantity);
		else
		if (type.equalsIgnoreCase("mi"))
			gc.add(12, iQuantity);
		else
		if (type.equalsIgnoreCase("s"))
			gc.add(13, iQuantity);
		return gc.getTime();
	}

	public static String getLastMonday(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(2);
		c.set(7, 2);
		c.add(5, -7);
		String result = sdf.format(c.getTime());
		return result;
	}

	public static String getTwoMonday(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(2);
		c.set(7, 2);
		c.add(5, -14);
		String result = sdf.format(c.getTime());
		return result;
	}

	public static String getThrMonday(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(2);
		c.set(7, 2);
		c.add(5, -21);
		String result = sdf.format(c.getTime());
		return result;
	}

	public static String getLastSunday(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(2);
		c.set(7, 1);
		c.add(5, -7);
		String result = sdf.format(c.getTime());
		return result;
	}

	public static String getPreMonthFirst(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(5, 1);
		c.add(2, -1);
		String result = sdf.format(c.getTime());
		return result;
	}

	public static String getPreMonthLast(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(5, 1);
		c.add(5, -1);
		String result = sdf.format(c.getTime());
		return result;
	}

	public static String getThrMonthFirst(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(2);
		if (month == 0 || month == 1 || month == 2)
		{
			c.set(2, 9);
			c.add(1, -1);
		} else
		if (month == 3 || month == 4 || month == 5)
			c.set(2, 0);
		else
		if (month == 6 || month == 7 || month == 8)
			c.set(2, 3);
		else
			c.set(2, 6);
		c.set(5, 1);
		String result = sdf.format(c.getTime());
		return result;
	}

	public static String getThrMonthLast(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(2);
		if (month == 0 || month == 1 || month == 2)
			c.set(2, 0);
		else
		if (month == 3 || month == 4 || month == 5)
			c.set(2, 3);
		else
		if (month == 6 || month == 7 || month == 8)
			c.set(2, 6);
		else
			c.set(2, 9);
		c.set(5, 1);
		c.add(5, -1);
		String result = sdf.format(c.getTime());
		return result;
	}
	
	/**
	 * 时间加减
	 * @param date   指定的时间
	 * @param field  需要处理的时间类型
	 * @param val    需要加减的值
	 * @return
	 */
	public static Date addDate(Date date,int field,int val){
		if(null == date){
			return null;
		}
		if(field < 0){
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int item = c.get(field);
		c.set(field, item + val);
		return c.getTime();
	}
	
	
	public static String format(String dateStr,String format){
		try {
			Date itemDate = strToDate(dateStr,"yyyyMMdd");
			if(null == itemDate){
				return "";
			}
			return formatDate(itemDate, format);
		} catch (ParseException e) {
			return "";
		}
	}
	
	public static String formatDate(String dateStr,String format){
		try{
			Date itemDate = strToDate(dateStr, "yyyyMMddHHmmss");
			if(null == itemDate)
				return "";
			return formatDate(itemDate, format);
		}catch(Exception e){
			return "";
		}
	}
	
	/**
	 * dateStr先根据sTdFormat转换为日期
	 * 在根据dTsFormat转换为日期字符串
	 * @param dateStr
	 * @param sTdFormat
	 * @param dTsFormat
	 * @return
	 */
	public static String formatDate(String dateStr,String sTdFormat,String dTsFormat){
		if(null == dateStr || "".equals(dateStr.trim())){
			return "";
		}
		if(null == sTdFormat || "".equals(sTdFormat.trim())){
			return "";
		}
		if(null == dTsFormat || "".equals(dTsFormat.trim())){
			return "";
		}
		try{
			Date itemDate = strToDate(dateStr, sTdFormat);
			if(null == itemDate)
				return "";
			return formatDate(itemDate, dTsFormat);
		}catch(Exception e){
			return "";
		}
	}
	
	/*public static Date getAllNameTime(String requestdate,String requesttime) throws Exception{
		if(null == requestdate || "".equals(requestdate.trim()) || null == requestdate || "".equals(requesttime.trim())){
			return null;
		}else{
			String dateStr = requestdate + requesttime;
			Date date = DateUtils.strToDate(dateStr,Constants.DATE_TIME_FORMAT_OTHER);
			return date;
		}
	}*/
	
	public static String getDateStr(String dateStr) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtils.strToDate(dateStr,"yyyy-MM-dd"));
		Integer day = c.get(Calendar.DAY_OF_MONTH);
		Integer month = c.get(Calendar.MONTH)+1;
		String date,monthStr,dayStr;
		if(month > 9){
			 monthStr = String.valueOf(month);
		}else{
			 monthStr = "0" + String.valueOf(month);
		}
		if(day >9){
			 dayStr = String.valueOf(day);
		}else{
			 dayStr = "0" + String.valueOf(day);
		}
		date = monthStr +"."+ dayStr;
		return date;
	}
}