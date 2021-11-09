package de.hbrs.concrete_stack;

import de.hbrs.concrete_stack.control.SalesMan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SalesManTest {


    private SalesMan salesman= null;

    @BeforeEach
    public void _init(){
        salesman= new SalesMan(1,"Tobias","Hansen","Marketing");
    }

    @Test
    void firstName() {
        String expact = "Tobias";
        assertEquals(expact, salesman.getFirstName());
    }

    @Test
    void aftertName(){
        String expact="Hansen";
        assertEquals(expact,salesman.getLastName());

    }

    @Test
    void deparment(){
        String expact="Marketing";
        assertEquals(expact,salesman.getDepartment());

    }


}