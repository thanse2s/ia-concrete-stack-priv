package de.hbrs.concrete_stack.springboot_controller;

import de.hbrs.concrete_stack.control.ManagePersonal;
import de.hbrs.concrete_stack.control.SalesMan;
import de.hbrs.concrete_stack.control.control_class;
import de.hbrs.concrete_stack.services.SalesManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesman")
public class SalesmanController {

    @Autowired
    private SalesManService salesManService;

    @GetMapping
    public List<SalesMan> getAllSalesman(){
        return salesManService.readAll();
    }

    @GetMapping("/{id}")
    public SalesMan getSingleSalesman(@PathVariable(required = true) long id){return salesManService.read(id);}

    @PostMapping
    public void createPerson(@RequestBody(required = true) SalesMan salesMan){
        salesManService.create(salesMan);
    }

    @PutMapping("/{id}")
    public void updateSalesMan(@PathVariable(required = true) long id,
                               @RequestBody(required = true) SalesMan salesMan){
        salesManService.update(id,salesMan);
    }

    @DeleteMapping("/{id}")
    public void deleteSalesman(@PathVariable(required = true) long id){
        salesManService.delete(id);
    }

}