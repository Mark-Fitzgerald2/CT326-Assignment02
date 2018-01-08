//Mark Fitzgerald Student ID 15456198

// Boss class derived from Employee.

public final class Boss extends Employee {

    private double weeklySalary;

    // constructor for class Boss
    public Boss(String first, String last, double salary, 
    		int year, int month, int day, int hour, int minute) throws InvalidDateException {
    	//pass in the year, month,day, hour and minute values
    	//specify that this can throw a date exception
        super(first, last, year, month, day, hour, minute); // call superclass constructor
        //the date and time they joined is stored in the Employee class
        setWeeklySalary(salary);
    }

    // set Boss's salary
    public void setWeeklySalary(double salary) {
    	//weeklySalary = (salary > 0 ? salary : 0);
    	//this line is removed to test the exception handling
    	//if it was kept in the weeklySalary would be zero not negative
    	weeklySalary = salary;
    }

    // get Boss's pay
    public double earnings() throws SalaryOutOfBoundsException {
        if(weeklySalary < 0) {
        	//if this value is less than zero throw an exception
        	throw new SalaryOutOfBoundsException(getFirstName() + " " 
        			+ getLastName() + "'s monthly wage is less than zero. The calculated wage is: " 
        			+ (weeklySalary * 4));
        	//print out the reason for the error with the employee name and their wage
        }
    	return weeklySalary * 4; 
    	//otherwise return the weekly salary by four to get the monthly salary
    }

    // get String representation of Boss's name
    public String toString() {
        return "Boss: " + super.toString();
    }
} // end class Boss
