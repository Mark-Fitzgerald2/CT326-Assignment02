//Mark Fitzgerald Student ID 15456198

// CommissionWorker class derived from Employee

public final class CommissionWorker extends Employee {

    private double salary; // base salary per week
    private double commission; // amount per item sold
    private int quantity; // total items sold for week

    // constructor for class CommissionWorker
    public CommissionWorker(String first, String last, double salary, double commission, int quantity, 
    		int year, int month, int day, int hour, int minute) throws InvalidDateException {
    	//pass in the year, month,day, hour and minute values
    	//specify that this can throw a date exception
        super(first, last, year, month, day, hour, minute); // call superclass constructor
        //the date and time they joined is stored in the Employee class
        setSalary(salary); //set the salary
        setCommission(commission); //set the commission
        setQuantity(quantity); //set the quantity
    }

    // set CommissionWorker's weekly base salary
    public void setSalary(double weeklySalary) {
    	//salary = (weeklySalary > 0 ? weeklySalary : 0);
    	//this line is removed to test the exception handling
    	//if it was kept in the salary would be zero not negative
    	salary = weeklySalary;
    }

    // set CommissionWorker's commission
    public void setCommission(double itemCommission) {
        //commission = (itemCommission > 0 ? itemCommission : 0);
    	//this line is removed to test the exception handling
    	//if it was kept in the commission would be zero not negative
    	commission = itemCommission;
    }

    // set CommissionWorker's quantity sold
    public void setQuantity(int totalSold) {
        //quantity = (totalSold > 0 ? totalSold : 0);
    	//this line is removed to test the exception handling
    	//if it was kept in the quantity would be zero not negative
    	quantity = totalSold;
    }

    // determine CommissionWorker's earnings
    public double earnings() throws SalaryOutOfBoundsException {
    	if(salary  < 0) {
    		//check if salary is less than zero
        	throw new SalaryOutOfBoundsException(getFirstName() + " " 
        			+ getLastName() + "'s monthly salary value is less than zero. The salary value is: " 
        			+ (salary*4));
        	//if the salary value is less than zero throw exception with explanation
        } else if (commission * quantity < 0) {
        	//checks the second half of the wage calculation
        	throw new SalaryOutOfBoundsException(getFirstName() + " " 
        			+ getLastName() + "'s commission value is less than zero. The commision value is: " 
        			+ (commission * quantity) 
        			+ "\nOne of the following is causing the issue:\nCommission " 
        			+ commission + "\nquantity " + quantity);
        	//if this is less than zero throw an exception
        }
        return (salary + commission * quantity) * 4; 
        //otherwise return the weekly salary by four to get the monthly salary
    }

    // get String representation of CommissionWorker's name
    public String toString() {
        return "Commission worker: " + super.toString();
    }
} // end class CommissionWorker
