//Mark Fitzgerald Student ID 15456198

// Definition of class HourlyWorker

public final class HourlyWorker extends Employee {

    private double wage; // wage per hour
    private double hours; // hours worked for week

    // constructor for class HourlyWorker
    public HourlyWorker(String first, String last,
            double wagePerHour, double hoursWorked, 
            int year, int month, int day, int hour, int minute) throws InvalidDateException  {
    	//pass in the year, month,day, hour and minute values
    	//specify that this can throw a date exception
        super(first, last, year, month, day, hour, minute); // call superclass constructor
        //the date and time they joined is stored in the Employee class
        setWage(wagePerHour); //set the value of wage
        setHours(hoursWorked); //set the value of hours worked
    }

    // Set the wage
    public void setWage(double wagePerHour) {
    	//wage = (wagePerHour > 0 ? wagePerHour : 0);
    	//this line is removed to test the exception handling
    	//if it was kept in the wage would be zero not negative
    	wage = wagePerHour;
    }

    // Set the hours worked
    public void setHours(double hoursWorked) {
        hours = (hoursWorked < 168? hoursWorked : 0);
        //removed the statement checking if the hours is negative
        //this is to allow testing of exception handling
        //kept the statement checking if their weekly hours is greater than the hours in a week 
    }

    // Get the HourlyWorker's pay
    public double earnings() throws SalaryOutOfBoundsException {
    	if((wage * hours) < 0) {
    		//if this multiplication is less than zero throw an exception
        	throw new SalaryOutOfBoundsException(getFirstName() + " " 
        			+ getLastName() + "'s monthly wage is less than zero. The calculated wage is: " 
        			+ (wage * hours * 4) 
        			+ "\nOne of the following is causing the issue:\nWage " + wage + "\nhours " + hours);
        	//print out the reason for the error with the employee name and their wage
        }
    	return wage * hours * 4; 
    	//if the value is positive return the weekly salary multiplied by 4 to get the monthly value
    }

    public String toString() {
        return "Hourly worker: " + super.toString();
    }
}
