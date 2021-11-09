package de.hbrs.concrete_stack.services.imp;

import de.hbrs.concrete_stack.control.EvaluationRecord;
import de.hbrs.concrete_stack.control.ManagePersonal;
import de.hbrs.concrete_stack.control.control_class;
import de.hbrs.concrete_stack.services.EvaluationRecordService;

import java.util.List;

public class EvaluationRecordServiceImplement implements EvaluationRecordService {

    ManagePersonal control = new control_class();

    @Override
    public EvaluationRecord read(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean create(EvaluationRecord evaluationRecord) {
        return false;
    }

    public boolean create(long id, EvaluationRecord evaluationRecord) {
        try {
            control.addEvaluationRecord(evaluationRecord, (int)id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List readAll() {
        return control.readAllEvaluationRecords();
    }

    @Override
    public EvaluationRecord read(long id, int year) {
        return control.readEvaluationRecords((int)id, year);
    }

    @Override
    public boolean delete(long id, int year) {
        return control.deleteEvaluationRecord((int)id, year);
    }

    @Override
    public boolean update(long id, EvaluationRecord evaluationRecord) {
        return control.updateEvaluationRecord(evaluationRecord, (int)id);
    }


}
