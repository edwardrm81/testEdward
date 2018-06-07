package co.com.gco.tarjetas.conciliador.ejb.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Date Utilities 
 * @author edward.rodriguez@ingeneo.com.co
 *
 */
public class UtilDate {    
    
    /**
     * Method to return Date with additional days
     * @param Date date
     * @param int days
     * @return Date date
     */    
    public static Date addDaysToDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();        
    }
    
    
    /**
     * Method to return Date with additional milliseconds
     * @param Date date
     * @param int milliseconds
     * @return Date date
     */    
    public static Date addMillisecondsToDate(Date date, int milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MILLISECOND, milliseconds);
        return cal.getTime();        
    }    
    
    /**
     * Method to return Date with additional days
     * @param Date date
     * @param int days
     * @return Date date
     */    
    public static Date addMonthsToDate(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();        
    }
    
    /**
     * Method to return number of Two Dates
     * @param Date startDate
     * @param Date endDate
     * @return Integer nu,ber of days
     */
    public static Integer getNumberDaysTwoDates(Date startDate, Date endDate) {
        
        Date startDateNoHours = dateToDateStartDay(startDate);
        Date endDateNoHours = dateToDateStartDay(endDate);
        
        //Difference Milliseconds :
        long startDateNoHoursL = startDateNoHours.getTime();
        long endDateNoHoursL = endDateNoHours.getTime();
        long diff = endDateNoHoursL - startDateNoHoursL; 
        
        //Difference days:
        Long diffDays = diff / (24 * 60 * 60 * 1000);
        
        return diffDays.intValue();
    }
    
    /**
     * Method to return Date without hours
     * @param Date date
     * @return Date date
     */        
    public static Date dateToDateStartDay(Date date) {        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }    
    
    /**
     * Method to return Date at the end of the day
     * @param Date date
     * @return Date date
     */        
    public static Date dateToDateEndDay(Date date) {        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    /**
     * Method to get the number of days between dates 
     * @param start Initial date
     * @param end End date
     * @return Number of days between dates
     */
    public static int getDaysBetweenDates(Date start,Date end){
        return (int)((end.getTime()-start.getTime())/1000/60/60/24);
    }
    
    
    /**
     * Method to get the number of minutes between dates 
     * @param start Initial date
     * @param end End date
     * @return Number of minutes between dates
     */
    public static int getMinutesBetweenDates(Date start,Date end){
        return (int)((end.getTime()-start.getTime())/1000/60);
    }
    
    /**
     * Method to build a date object
     * @param year Year
     * @param month Month
     * @param date Day
     * @return java.util.date
     */
    public static Date getDate(int year,int month,int date){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DATE, date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Method to build a date
     * @param String separator
     * @param String yyyyMMddDate
     * @return java.util.date
     */
    public static Date getDate(String separator, String yyyyMMddDate) {
    	
    	try {
    		
    		Date date = null; //getDate(year, month, dayOfMonth);
    		
    		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy"+separator+"MM"+separator+"dd");
    		formatoFecha.setLenient(false); 
    		date = formatoFecha.parse(yyyyMMddDate);
    		
    		String[] dateArray = yyyyMMddDate.split(separator);
    		
    		String yearStr = dateArray[0];    		
    		String monthStr = dateArray[1];
    		String dayOfMonthStr = dateArray[2];

    		if(yearStr.length()>4) {
    			return null;
    		}
    		
    		if(monthStr.length()>2) {
    			return null;
    		}
    		
    		if(dayOfMonthStr.length()>2) {
    			return null;
    		}
    			
    		return date;
    		
    	} catch(Exception e) {
    		return null;
    	}
    	
    }
    
    /**
     * Method for getting the last date of a month
     * @param date
     * @return the last date of the month
     */
    public static Date getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
    
    /**
     * Method for getting the last date of a week
     * @param date
     * @return the last date of the week
     */
    public static Date getLastDateOfWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        return cal.getTime();
    }
     
}
