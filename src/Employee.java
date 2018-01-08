import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

//Mark Fitzgerald Student ID 15456198

// Abstract base class Employee.

public abstract class Employee {

    private String firstName;
    private String lastName;
    private Date joinDate;
    //create a LocalDate variable to store the joinDate
    private int joinYear;
    private int joinMonth;
    private int joinDay;
    private int joinHour;
    private int joinMinute;
    //these values are stored so that we can print the wrong date for the user
    private int dayOfWeek;
    //used to check if it's a weekend
    private static long IDNumber = 1;
    //start IDNumber at the value 1 for the first employee
    private long myID = 0; 
    //create a variable to return an employee's specific ID
    
    // constructor
    public Employee(String first, String last, 
    		int year, int month, int day, int hour, int minute) throws InvalidDateException {
    	
    	//note that the joinDate is passed in through the constructor
        firstName = first;
        lastName = last;
        
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        //create two calendars
        //One for the current date and time 
        //The other for the join date and time
        
        cal2.set(Calendar.YEAR, year);
        cal2.set(Calendar.MONTH, (month-1));
        //Note calendar uses a range of [0,11] for months
    	//this is why there is a minus 1 comes from
        cal2.set(Calendar.DAY_OF_MONTH, day);
        cal2.set(Calendar.HOUR_OF_DAY, hour);
        cal2.set(Calendar.MINUTE, minute);
        //set the join date and time in calendar two
        
        joinDate = cal2.getTime();
        //stored the join date
        joinYear = year;
        joinMonth = month;
        joinDay = day;
        joinHour = hour;
        joinMinute = minute;
        //store specific values so we can print the incorrect dates
        //this is used because Calendar has its own way of dealing with incorrect dates
        //For example if you enter 1-13-2017 it will return 1-1-2018
        
        dayOfWeek = cal2.get(Calendar.DAY_OF_WEEK);
        //store what day of the week it is
        
        if/*1*/(cal1.get(Calendar.YEAR) < year 
        		//if the current year is less than the join year
        		//or check if the current year is equal to the join year
        		|| /*2*/(year == cal1.get(Calendar.YEAR) && /*3*/(month > (cal1.get(Calendar.MONTH)+1) ||
        				//then check is the join month greater than the current month
        				//else check if these are equal
        				/*4*/( (month == cal1.get(Calendar.MONTH)+1) 
        						//is the join day greater than the current day
        						&& day > cal1.get(Calendar.DAY_OF_MONTH) /*4*/) /*3*/) /*2*/) /*1*/) {
        	//Note the numbers at brackets are to show which brackets link up
			//When there is /*1*/ ( this is an opening bracket
			//for a closed bracket it is /*1*/ )
        	//Note calendar uses a range of [0,11] for months
        	//this is why there is a plus 1 in the line cal1.get(Calendar.MONTH)+1
        	
        	//if these requirements are met throw a future exception
        	throw new InvalidDateException(getFirstName() + " " 
        			+ getLastName() + "'s join date " 
        			+ getErrorJoinDate() + " is invalid."
        			+ "\n\nReason: join year is in the future\n");
        } else if (year < 1990) {
        	//is the year before 1990? 
        	//1-1-1990 is the first date accepted as the join date
        	//31-12-1989 or anything before this is not accepted
        	
        	//throw an exception if this is true
        	throw new InvalidDateException(getFirstName() + " " 
        			+ getLastName() + "'s join date " 
        			+ getErrorJoinDate() + " is invalid."
        			+ "\n\nReason: join year is before 1990\n");
        } else if (month < 1 || month > 12) {
        	//if the month is not in the range [1,12] throw exception
        	throw new InvalidDateException(getFirstName() + " " 
        			+ getLastName() + "'s join date " 
        			+ getErrorJoinDate() + " is invalid."
        			+ "\n\nReason: Month is out of range\n");
        } else if(day < 1 || day > 28) {
        	//if the day is greater than 28 make sure that it applies to the current month
        	if(month == 2 && day > 28) {
        		//if the month is February it only has 28 days
        		if(!isLeapYear(year)) {
        			//check if leap  year in case it has 29 days
        			//if it is not a leap year and the day is greater than 28 throw an exception
        			throw new InvalidDateException(getFirstName() + " " 
                			+ getLastName() + "'s join date " 
                			+ getErrorJoinDate() + " is invalid."
                			+ "\n\nReason: February only has 28 days. This is not a leap year.\n");
        		} else if (day>29 && isLeapYear(year)) {
        			//the day is greater than 29
        			//even if it is a leap year this has to throw an exception
        			throw new InvalidDateException(getFirstName() + " " 
                			+ getLastName() + "'s join date " 
                			+ getErrorJoinDate() + " is invalid."
                			+ "\n\nReason: This is a leap year. However February only has 29 days this year.\n");
        		}
        	} else if((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
        		//check is the month April, June, September or November 
        		//these months have 30 days so check if more than 30 was entered
        		//if this is true through an exception
        		throw new InvalidDateException(getFirstName() + " " 
            			+ getLastName() + "'s join date " 
            			+ getErrorJoinDate() + " is invalid."
            			+ "\n\nReason: 30 Days had September, April, June and November\n");
        	} else if(day > 31) {
        		//check if more than 31 was entered
        		//this is the max val for days in a month so throw an exception
        		throw new InvalidDateException(getFirstName() + " " 
            			+ getLastName() + "'s join date " 
            			+ getErrorJoinDate() + " is invalid."
            			+ "\n\nReason: No Month has more than 31 days\n");
        	} else if(day < 1) {
        		//if the day entered is less than 1 throw an exception
        		throw new InvalidDateException(getFirstName() + " " 
            			+ getLastName() + "'s join date " 
            			+ getErrorJoinDate() + " is invalid."
            			+ "\n\nReason: No month has less than 1 days\n");
        	}
        } else if(hour < 9 || hour > 18 || (hour == 18 && minute > 0)) {
        	//check is the hour less than 9
        	//check if the hour is greater than 18
        	//check if the hour is equal to 18 but the minute is greater than 0
        	//if one of these is true throw an exception
        	throw new InvalidDateException(getFirstName() + " " 
        			+ getLastName() + "'s join date " 
        			+ getJoinDate() + " is invalid."
        			+ "\n\nReason: Hour outside range [9:00, 18:00]\n");
        } else if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
        	//check if dayOfWeek val equals Saturday or Sunday (Ie a weekend)
        	//if it is throw an exception
        	throw new InvalidDateException(getFirstName() + " " 
        			+ getLastName() + "'s join date " 
        			+ getJoinDate() + " is invalid."
        			+ "\n\nReason: Working on a weekend\n");
        }
              
        //store the date they joined in a local variable 
        this.myID = IDNumber;
        //give an employee their specific ID
        IDNumber++;
        //increment the IDNumber variable everytime an ID is used
        //this is to make sure no two employee's have the same ID
    }
    
    //get IDNumber
    public long getMyID() {
    	return myID; 
    	//this is used to return an employee's specific ID Number
    }
    
    public String getErrorJoinDate() {
    	return getJoinDay() + "-" + (getJoinMonth()+1) + "-" 
    			+ getJoinYear() + " " + getJoinHour() + ":" + getJoinMinute();
    	//this returns the values passed in so that the user can see where they went wrong
    	//Note calendar uses a range of [0,11] for months
    	//in joinMonth() we take away one for this reason
    	//this is why there is a plus 1 in the line (getJoinMonth()+1)
    }
    //get join date
    public String getJoinDate() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    	//format the way that the date is returned
    	return sdf.format(joinDate); 
    	//this is used to return the specific date an employee joined the company
    }

    private boolean isLeapYear(int year) {
    	//check to see if it is a leap year
    	Calendar cal = Calendar.getInstance();
    	//create a calendar
    	cal.set(Calendar.YEAR, year);
    	//set the year for the calendar
    	return cal.getActualMaximum(cal.DAY_OF_YEAR) > 365;
    	//check how many days there are in a year
    	//if the year is a leap year there will be 366 days 
    	//thus this function returns true
    }
    // get first name
    public String getFirstName() {
        return firstName;
        //this is used to return an Employee's first name
    }
    
    public int getJoinYear() {
    	//used if the year needs to be called
        return joinYear;
    }
    
    public int getJoinMonth() {
    	//used if the month needs to be called
        return joinMonth-1;
        //Note calendar uses a range of [0,11] for months
    	//the joinMonth val was stored as the value passed in as month
        //this is in the range [1,12]
    	//this is why there is a minus 1 in case calendar calls getJoinMonth
    }
    
    public int getJoinDay() {
        return joinDay;
        //used if the join day needs to be called
    }
    
    public int getJoinHour() {
    	//used if the join hour needs to be called 
    	return joinHour;
    }
    
    public int getJoinMinute() {
    	//used if the join minute needs to be called
    	return joinMinute;
    }

    // get last name
    public String getLastName() {
        return lastName;
      //this is used to return an Employee's second name
    }

    public String toString() {
        return firstName + ' ' + lastName;
        //used to print an employee's first and second name
    }

    public abstract double earnings() throws SalaryOutOfBoundsException; 
    //throws an exception to handle negative salary values
}
