package de.hbrs.concrete_stack;

import de.hbrs.concrete_stack.control.EvaluationRecord;
import de.hbrs.concrete_stack.control.ManagePersonal;
import de.hbrs.concrete_stack.control.SalesMan;
import de.hbrs.concrete_stack.control.control_class;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CmdDisplay {
/*
    public static void main (String args[]) {




       Scanner sc = new Scanner(System.in);
        ManagePersonal managePersonal = new control_class();

        System.out.println("Welcome to our Database connection Page! Please enter Enter the number for the Task you want.");
        System.out.println("Write 1 for creation of a Salesman.");
        System.out.println("Write 2 for creation of an Evaluation record.");
        System.out.println("Write 3 to read a Salesman.");
        System.out.println("Write 4 to read an Evaluation Record.");
        System.out.println("Write 5 to delete a Salesman.");
        System.out.println("Write 6 to Change an Existing Salesman.");
        System.out.println("Please give your input:");

        while(sc.hasNext()) {
            switch (sc.nextInt()) {
                case 1:
                    createSalesman(sc, managePersonal);
                    System.out.println("Successfully created Salesman!");
                    break;
                case 2:
                    createEvaluationRecord(sc, managePersonal);
                    System.out.println("Successfully created Evaluation Record!");
                    break;
                case 3:
                    System.out.println("Enter the Employee-ID:");
                    System.out.println(managePersonal.readSalesMan(sc.nextInt()));
                    break;
                case 4:
                    System.out.println("Enter the Employee-ID and the Year:");
                    System.out.println(managePersonal.readEvaluationRecords(sc.nextInt(), sc.nextInt()));
                    break;
                case 5:
                    System.out.println("Enter the Employee-ID you want to delete:");
                    managePersonal.deleteSalesMan(sc.nextInt());
                    System.out.println("Successfully deleted.");
                    break;
                case 6:
                    System.out.println("Enter the Employee-ID:");
                    int id = sc.nextInt();
                    System.out.println("Enter the attribute which you want to change (firstname|lastname|department):");
                    String attribute = sc.next();
                    System.out.println("Enter the new Value of the Attribute");
                    String newvalue = sc.next();
                    managePersonal.changeSalesMan(id,attribute,newvalue);
                    System.out.println("Salesman successfully updated!");
                    break;
                default:
                    System.out.println("Wrong number.");
                    break;
            }
            System.out.println("Do you want to exit? enter q:");
            String exit = sc.next();
            if (exit.equals("q")) break;
            System.out.println("Write 1|2:");
        }

    }

    private static void createSalesman(Scanner sc, ManagePersonal managePersonal) {
        String firstName;
        String lastName;
        Integer employeeId;
        String department;
        System.out.println("Enter your First name:");
        firstName = sc.next();
        System.out.println("Enter your Last name:");
        lastName = sc.next();
        System.out.println("Enter your employee-ID:");
        employeeId = sc.nextInt();
        System.out.println("Enter your department:");
        department = sc.next();
        SalesMan inputSalesman = new SalesMan(employeeId, firstName, lastName, department);
        managePersonal.createSalesMan(inputSalesman);
    }

    private static void createEvaluationRecord(Scanner sc, ManagePersonal managePersonal) {
        int year;
        int employeeId;
        int targetValue;
        int actualValue;
        String description;
        System.out.println("Enter the current year:");
        year = sc.nextInt();
        System.out.println("Enter your employee-ID:");
        employeeId = sc.nextInt();
        System.out.println("Enter the target value:");
        targetValue = sc.nextInt();
        System.out.println("Enter the actual value:");
        actualValue = sc.nextInt();
        System.out.println("Enter the description:");
        description = sc.next();
        // TODO also read the description from input
        EvaluationRecord inputEvaluationRecord = new EvaluationRecord(year);
        inputEvaluationRecord.addNewSocialPerformance(targetValue, actualValue, "");
        managePersonal.addEvaluationRecord(inputEvaluationRecord, employeeId);


    }
*/
    public static void main (String[] args){
        SpringApplication.run(CmdDisplay.class,args);
    }
}
