package de.hbrs.concrete_stack.springboot;

import de.hbrs.concrete_stack.control.ManagePersonal;
import de.hbrs.concrete_stack.control.SalesMan;
import de.hbrs.concrete_stack.control.control_class;
import de.hbrs.concrete_stack.services.SalesManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/salesman")
public class SalesmanController {

    ManagePersonal control = new control_class();

    @Autowired
    private SalesManService salenanService;

    @GetMapping
    public List<SalesMan> s1(){
        return salenanService.findAll();
    }

    @GetMapping("/{id}")
    public SalesMan getSingleSalesman(@PathVariable(required = true) long id){return salenanService.getSinge(id);}

    @PostMapping
    public void createPerson(@RequestBody(required = true) SalesMan salesMan){
        salenanService.create(salesMan);
    }

    @PutMapping("/{id}")
    public void updateSalesMan(@PathVariable(required = true) long id,
                               @RequestBody(required = true) SalesMan salesMan){
        salenanService.update(id,salesMan);
    }

    @DeleteMapping("/{id}")
    public void deleateSalesman(@PathVariable(required = true) long id){
        //salenanService.delete(id);
        control.deleteSalesMan((int)id);
    }

}
