//Mark Fitzgerald Student ID 15456198

// PieceWorker class derived from Employee

public final class PieceWorker extends Employee {

    private double wagePerPiece; // wage per piece output
    private int quantity; // output for week

    // constructor for class PieceWorker
    public PieceWorker(String first, String last, double wage, int numberOfItems, 
    		int year, int month, int day, int hour, int minute) throws InvalidDateException {
    	//pass in the year, month,day, hour and minute values
    	//specify that this can throw a date exception
        super(first, last, year, month, day, hour, minute); // call superclass constructor
        //the date and time they joined is stored in the Employee class
        setWage(wage); //set the value of wage
        setQuantity(numberOfItems); //set the value of quantity
    }

    // set PieceWorker's wage
    public void setWage(double wage) {
    	//wagePerPiece = (wage > 0 ? wage : 0);
    	//this line is removed to test the exception handling
    	//if it was kept in the wage would be zero not negative
    	wagePerPiece = wage;
    	//set the wage value
    }

    // set number of items output
    public void setQuantity(int numberOfItems) {
        //quantity = (numberOfItems > 0 ? numberOfItems : 0);
    	//this line is removed to test the exception handling
    	//if it was kept in the quantity value would be zero not negative
    	quantity = numberOfItems;
    	//give quantity a value
    }

    // determine PieceWorker's earnings
    public double earnings() throws SalaryOutOfBoundsException {
    	if((quantity * wagePerPiece) < 0) {
    		//if this multiplication is less than zero throw an exception
        	throw new SalaryOutOfBoundsException(getFirstName() + " " 
        			+ getLastName() + "'s monthly wage is less than zero. The calculated wage is: " 
        			+ (quantity * wagePerPiece * 4) + "\nOne of the following is causing the issue:\nquantity" 
        			+ quantity + "\nwagePerPiece" + wagePerPiece);
        	//print out the reason for the error with the employee name and their wage
        }
    	return quantity * wagePerPiece * 4;
    	//if the value is positive return the weekly salary multiplied by 4 to get the monthly value
    }

    public String toString() {
        return "Piece worker: " + super.toString();
    }
}
