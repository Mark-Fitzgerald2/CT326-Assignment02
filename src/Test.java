// Driver for Employee hierarchy

//Mark Fitzgerald Student ID 15456198

// Java core packages
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Calendar;

// Java extension packages
import javax.swing.JOptionPane;

public class Test {

    // test Employee hierarchy
    public static void main(String args[]) throws InvalidDateException  {
        String output = "";
        ArrayList<Employee> emp = new ArrayList<Employee>();
        //create an ArrayList to store Employee Objects
        double monthlyWage;
        //create a variable to print monthlyWage
        
        for(int i =0; i < 8; i++) {
        	//loop to try each of the dates
        	try {
        		if(i==0) {//if block used to select which employee to create
        			//this was used so I could test different employee objects
        			Boss boss = new Boss("Person", "One", 800.0, 1985, 06, 30, 13, 12);
        			//try to create boss
        			emp.add(boss);
        			//add to array if exception isn't thrown
        		} else if(i==1) {
        			CommissionWorker commissionWorker =
        	                new CommissionWorker("Person", "Two", 400.0, 3.0, 150, 2020, 8, 4, 10, 23);
        			//try to create commissionWorker
        			emp.add(commissionWorker);
        			//add to array if exception isn't thrown
        		} else if(i==2) {
        			PieceWorker pieceWorker = new PieceWorker("Person", "Three", 2.5, 200, 1996, 02, 29, 9, 35);
        			//try to create pieceWorker
        			emp.add(pieceWorker);
        			//add to array if exception isn't thrown
        		} else if(i==3) {
        			HourlyWorker hourlyWorker = new HourlyWorker("Person", "Four", 13.75, 40, 2010, 11, 31, 14, 45);
        			//try to create hourlyWorker
        			emp.add(hourlyWorker);
        			//add to array if exception isn't thrown
        		} else if(i==4) {
        			Boss boss2 = new Boss("Person", "Five", 800.0, 2008, 10, 27, 8, 33);
        			//try to create boss
        			emp.add(boss2);
        			//add to array if exception isn't thrown
        		} else if(i==5) {
        			CommissionWorker commissionWorker2 =
        	                new CommissionWorker("Person", "Six", 400.0, 3.0, 150, 2003, 01, 18, 11, 14);
        			//try to create commissionWorker
        			emp.add(commissionWorker2);
        			//add to array if exception isn't thrown
        		} else if(i==6) {
        			HourlyWorker hourlyWorker2 = new HourlyWorker("Person", "Seven", 13.75, 40, 1999, 02, 30, 16, 11);
        			//try to create hourlyWorker
        			emp.add(hourlyWorker2);
        			//add to array if exception isn't thrown
        		} else if(i==7) {
        			PieceWorker pieceWorker2 = new PieceWorker("Preson", "Eigth", 2.5, 200, 2015, 07, 8, 12, 37);
        			//try to create pieceWorker
        			emp.add(pieceWorker2);
        			//add to array if exception isn't thrown
        		}
        	} catch(InvalidDateException e1) {
        		output += "Exception thrown: " + e1.getMessage() + "\n";
        		//add to the output the reason for the exception
        	}
        }
        
        
        //if the exception isn't thrown we calculate their monthly wages from last weeks code by looping through the array
        DecimalFormat precision2 = new DecimalFormat("0.00");
        int i = 0;
        //create an incrementer 
        while(i<emp.size()) { //loop through the ArrayList
        	try { //try this for each Employee Object
        		
        		Calendar cal = Calendar.getInstance();
        		//have to use calendar for this check as we no longer have joda time
            	if/*1*/(emp.get(i).getJoinYear() < (cal.get(Calendar.YEAR)-5) 
            			//is the join year before the current year - 5?
            			|| /*2*/( (emp.get(i).getJoinYear() == (cal.get(Calendar.YEAR)-5)) 
            			//is the join year equal to the current year -5?
            			&&  /*3*/(emp.get(i).getJoinMonth() < cal.get(Calendar.MONTH) 
            			//if so check is the current month great than the join month
            			|| /*4*/(emp.get(i).getJoinMonth() == cal.get(Calendar.MONTH) 
           				//otherwise check is the join month equal to the current month
       					//check is the join day less than the current day
               			&& emp.get(i).getJoinDay() < cal.get(Calendar.DAY_OF_MONTH)/*4*/) /*3*/) /*2*/) /*1*/) {
            			//Note the numbers at brackets are to show which brackets link up
            			//When there is /*1*/ ( this is an opening bracket
            			//for a closed bracket it is /*1*/ )
            		
            		//if the employee is elegible for the bonus add this on
            		monthlyWage = (emp.get(i).earnings()) + 200;
            		
            	} else {
            		//else get their normal salary
            		monthlyWage = (emp.get(i).earnings());
            	}
            
            	output += "\n" + emp.get(i).toString() + " earned $"
                        + precision2.format(monthlyWage) + "\n"
                        + "The date the employee joined is: "
                        + emp.get(i).getJoinDate() + "\n"
                        + "Employee ID Number: " 
                        + emp.get(i).getMyID() + "\n";
            	//add all the details of the employee to output so we can print to screen 
        	}
        	catch(SalaryOutOfBoundsException e) {
        		//if the salary is a negative value throw an exception
                System.out.println("Exception thrown: " + e.getMessage());
                //print to screen that an exception is thrown and why
                e.printStackTrace();
                //print the stack trace
        	}
        	i++; //increment i
        }
      
        JOptionPane.showMessageDialog(null, output,
                "Employee details",
                JOptionPane.INFORMATION_MESSAGE);
        //print the employee details to screen

        System.exit(0);
    }
} // end class Test
