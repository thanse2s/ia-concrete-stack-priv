package de.hbrs.concrete_stack.springboot_controller;

import de.hbrs.concrete_stack.control.EvaluationRecord;
import de.hbrs.concrete_stack.services.EvaluationRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluationrecord")
public class EvaluationRecordController {

    private EvaluationRecordService service;

    @GetMapping
    public List<EvaluationRecord> getAllEvaluation(){ return service.readAll();}

    @GetMapping("/{id}/{year}")
    public EvaluationRecord getSingleEvaluation(@PathVariable(required = true)long id, int year) {
        return service.read(id, year);
    }

    @PostMapping("/{id}")
    public void createEvaluationRecord(@PathVariable long id, @RequestBody EvaluationRecord evaluationRecord) {
        service.create(id,evaluationRecord);
    }

    @PutMapping("/{id}")
    public void updateEvaluationRecord(@PathVariable long id, @RequestBody EvaluationRecord evaluationRecord){
        service.update(id,  evaluationRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteEvaluationRecord(@PathVariable long id) {
        service.delete(id);
    }
}
