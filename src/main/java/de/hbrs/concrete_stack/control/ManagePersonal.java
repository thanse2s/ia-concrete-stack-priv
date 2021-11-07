package de.hbrs.concrete_stack.control;

import java.util.List;

public interface ManagePersonal {

    public void createSalesMan( SalesMan record );    //Create (C)

    public void addEvaluationRecord( EvaluationRecord record , int sid ); //Create (C)

    public SalesMan readSalesMan( int sid ); //Read (R)

    public List<SalesMan> querySalesMan(String attribute , String key );   //Read (R)

    public EvaluationRecord readEvaluationRecords(int sid, int year);   //Read (R)

    public void deleteSalesMan(int sid);

    public void changeSalesMan(int sid, String attribute, String value);

}