package de.hbrs.concrete_stack.services.imp;

import de.hbrs.concrete_stack.control.ManagePersonal;
import de.hbrs.concrete_stack.control.SalesMan;
import de.hbrs.concrete_stack.control.control_class;
import de.hbrs.concrete_stack.services.SalesManService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleManServiceImplement implements SalesManService {

    List<SalesMan> salesMans = new ArrayList<>();
    ManagePersonal control = new control_class();

    @PostConstruct
    public void postConstruct(){
        SalesMan s1  = new SalesMan();
        s1.setEmployeeId(66);
        s1.setFirstname("Tobias");
        s1.setLastname("Hansen");
        s1.setdepartment("JUnit");
        salesMans.add(s1);
        control.createSalesMan(s1);
    }

    @Override
    public List<SalesMan> readAll() {
        salesMans = control.querySalesMan("employeeId", "");
        return salesMans;
    }

    @Override
    public SalesMan read(long id) {
        return control.readSalesMan((int)id);
    }

    @Override
    public boolean delete(long id) {
        /*for(SalesMan s: salesMans){
            if(s.getEmployeeId()==id)
                this.salesMans.remove(s);
                return true;
        } */
        control.deleteSalesMan((int)id);
        return true;
    }

    @Override
    public boolean update(long id, SalesMan salesMan) {
        try{
            SalesMan tmp = control.readSalesMan((int)id);
            if(!tmp.getFirstName().equals(salesMan.getFirstName())) {
                control.changeSalesMan((int) id, "firstname", salesMan.getFirstName());
            }
            if(!tmp.getFirstName().equals(salesMan.getFirstName())) {
                control.changeSalesMan((int) id, "lastname", salesMan.getFirstName());
            }
            if(!tmp.getFirstName().equals(salesMan.getFirstName())) {
                control.changeSalesMan((int) id, "department", salesMan.getFirstName());
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean create(SalesMan salesMan) {
        //this.salesMans.add(salesMan);
        control.createSalesMan(salesMan);
        return true;
    }
}
