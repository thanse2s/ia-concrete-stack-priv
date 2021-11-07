/*
 * @author Frederick Behringer
 * @version 21.10.2021
 */

package de.hbrs.concrete_stack.control;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import com.mongodb.client.FindIterable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class control_class implements ManagePersonal {

    /* Configuration
     *
     */
    final boolean setLogging = true;
    private final MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");

    private boolean connected;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> salesman;
    private MongoCollection<Document> performanceRecords;
    private MongoCollection<Document> goals;

    /*
     * Sets up a MongoClient, MongoDatabase and MongoCollections.
     * Must be run at least once!
     */
    public control_class() {
        if (setLogging) {
            Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.SEVERE);
        }
        connected = false;
    }

    /*
     * Use connectAndOpen and disconnectAndClose to open and close the connection after every access.
     */
    private void connectAndOpen() {
        while(!connected) {
            mongoClient = new MongoClient(connectionString);
            database = mongoClient.getDatabase("integration_architecture");
            salesman = database.getCollection("salesman");
            performanceRecords = database.getCollection("performance_records");
            goals = database.getCollection("goals");
            connected = (goals != null) && (performanceRecords != null) && (salesman != null);
        }
    }
    private void disconnectAndClose() {
        mongoClient.close();
        database = null;
        salesman = null;
        performanceRecords = null;
        goals = null;
        connected = false;
    }
    // For Testing
    public boolean isConnected() {
        return connected;
    }

    /*
     * Inserts a SalesMan to the Database.
     */
    public void createSalesMan(SalesMan record) {
        this.connectAndOpen();
        salesman.insertOne(salesManToDocument(record));
        this.disconnectAndClose();
    }
    /*
     * Transforms a SalesMan Java-Object to a corresponding BSON Document.
     */
    private Document salesManToDocument(SalesMan record) {
        return new Document("employee_id", record.getEmployeeId())
                .append("firstname", record.getFirstName())
                .append("lastname", record.getLastName())
                .append("department", record.getDepartment());
    }

    /*
     * Queries the first SalesMan from the Database with the given employee_id.
     */
    public SalesMan readSalesMan(int employeeId) {
        this.connectAndOpen();
        FindIterable<Document> findIterable = salesman.find(eq("employee_id", employeeId));
        Document doc = findIterable.first();
        this.disconnectAndClose();
        return documentToSalesMan(doc);
    }
    private SalesMan documentToSalesMan(Document doc) {
        return new SalesMan(doc.getInteger("employee_id"),
                doc.getString("firstname"),
                doc.getString("lastname"),
                doc.getString("department"));
    }

    /*
     * Adds an Evaluation record to the performanceRecords Collection. If the goal is not already in the
     * goals Collection, it will be inserted in this too.
     */
    // TODO should work with _id in the future
    public void addEvaluationRecord(EvaluationRecord record , int employeeId){
        this.connectAndOpen();
        if (performanceRecords.count(eq("year", record.getYear())) == 0)
            performanceRecords.insertOne(new Document("year", record.getYear()));
        record.getSocialPerformances().forEach(x -> {
            Document socialPerformance = new Document("actual_value", x.getActualValue())
                    .append("employee_id", employeeId)
                    .append("description", x.getDescription())
                    .append("target_value", x.getTargetValue());
            performanceRecords.updateOne(
                    eq("year", record.getYear()),
                    addToSet("social_performance", socialPerformance));
        });
        record.getOrderEvaluations().forEach(x -> {
            Document ordersEvaluation = new Document("name_of_product", x.getNameOfProduct())
                    .append("employee_id", employeeId)
                    .append("client", x.getClient())
                    .append("client_ranking", x.getClientRanking())
                    .append("items", x.getItems());
            performanceRecords.updateOne(
                    eq("year", record.getYear()),
                    addToSet("orders_evaluation", ordersEvaluation));
        });
        this.disconnectAndClose();
    }

    /*
     * Returns the EvaluationRecord of a given employee in the specified year.
     */
    public EvaluationRecord readEvaluationRecords(int employeeId, int year) {
        this.connectAndOpen();
        EvaluationRecord result = new EvaluationRecord(year);
        Document tmp = performanceRecords.find(eq("year", year)).first();
        if (tmp == null) return null;
        ((ArrayList<Document>)(tmp.get("orders_evaluation"))).stream()
                .filter(x -> x.getInteger("employee_id") == employeeId)
                .forEach(x -> result.addNewOrdersEvaluation(x));
        ((ArrayList<Document>)(tmp.get("social_performance"))).stream()
                .filter(x -> x.getInteger("employee_id") == employeeId)
                .forEach(x -> result.addNewSocialPerformance(x));
        this.disconnectAndClose();
        return result;
    }

    /*
     * Deletes an EvaluationRecord form the performance_records Collection. Returns True if successfull.
     */
    public boolean deleteEvaluationRecord(int employeeId, int year) {
        this.connectAndOpen();
        performanceRecords.updateMany(eq("year", year), pull("orders_evaluation", eq("employee_id", employeeId)));
        performanceRecords.updateMany(eq("year", year), pull("social_performance", eq("employee_id", employeeId)));
        // Check if it is an empty year
        Document found = performanceRecords.find(eq("year", year)).first();
        if (found == null) {
            this.disconnectAndClose();
            return false;
        }
        if (((ArrayList<Document>)found.get("orders_evaluation")).isEmpty() &&
                ((ArrayList<Document>)found.get("social_performance")).isEmpty())
            performanceRecords.deleteMany(eq("year", year));
        this.disconnectAndClose();
        return true;
    }

    /*
     * Updates the EvaluationRecord of a given employeeId to the given record.
     */
    public boolean updateEvaluationRecord(EvaluationRecord record, int employeeId) {
        this.deleteEvaluationRecord(employeeId, record.getYear());
        this.addEvaluationRecord(record, employeeId);
        return true;
    }

    /*
     * Queries the Database with the given attribute and key. It returns a List of
     * Salesman Objects.
     */
    public List<SalesMan> querySalesMan(String attribute , String key ) {
        this.connectAndOpen();
        List<SalesMan> result = new ArrayList<SalesMan>();
        FindIterable<Document> findIterable = salesman.find(eq(attribute, key));
        for (Document doc : findIterable) {
            result.add(documentToSalesMan(doc));
        }
        this.disconnectAndClose();
        return result.isEmpty() ? null : result;
    }

    /*
     * Deletes the SalesMan with the given employeeId.
     */
    public void deleteSalesMan(int employeeId){
        this.connectAndOpen();
        salesman.deleteMany(eq("employee_id", employeeId));
        this.disconnectAndClose();
    }

    /*
     * Changes the value of attribute to value from the first SalesMan with the given employeeId.
     */
    public void changeSalesMan(int employeeId, String attribute, String value){
        this.connectAndOpen();
        salesman.updateOne(eq("employee_id", employeeId), combine(set(attribute, value)));
        this.disconnectAndClose();
    }


}