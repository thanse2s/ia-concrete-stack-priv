package de.hbrs.concrete_stack.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


public class SalesMan {

    private int employeeId;
    private String firstname;
    private String lastname;
    private String department;

    public SalesMan(){}
    public SalesMan(int employeeId, String firstname, String lastname, String department) {
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setEmployeeId(int id){employeeId=id;}
    public void setFirstname(String firstname){this.firstname=firstname;}
    public void setLastname(String lastname){this.lastname=lastname;}
    public void setdepartment(String department){this.department=department;}
}