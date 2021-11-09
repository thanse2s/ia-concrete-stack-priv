package de.hbrs.concrete_stack.services;

import de.hbrs.concrete_stack.control.EvaluationRecord;
import de.hbrs.concrete_stack.services.imp.EvaluationRecordServiceImplement;

public interface EvaluationRecordService extends CrudService<EvaluationRecord> {
    EvaluationRecord read(long id, int year);
    boolean delete(long id, int year);
    boolean create(long id, EvaluationRecord evaluationRecord);
}
