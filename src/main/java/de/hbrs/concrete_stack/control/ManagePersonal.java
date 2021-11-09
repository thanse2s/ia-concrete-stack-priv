package de.hbrs.concrete_stack.control;

import org.bson.conversions.Bson;

import java.util.List;

public interface ManagePersonal {

    public void createSalesMan( SalesMan record );    //Create (C)

    public void addEvaluationRecord( EvaluationRecord record , int sid ); //Create (C)

    public SalesMan readSalesMan( int sid ); //Read (R)

    public List<SalesMan> querySalesMan(String attribute , String key );   //Read (R)
    public List<SalesMan> querySalesMan(Bson filter);   //Read (R)

    public EvaluationRecord readEvaluationRecords(int sid, int year);   //Read (R)

    public List<EvaluationRecord> readAllEvaluationRecords();   //Read (R)

    public void deleteSalesMan(int sid);    //Delete (D)

    public boolean deleteEvaluationRecord(int employeeId, int year);    //Delete (D)

    public void changeSalesMan(int sid, String attribute, String value);    //Update (U)

    public boolean updateEvaluationRecord(EvaluationRecord record, int employeeId); //Update (U)
}