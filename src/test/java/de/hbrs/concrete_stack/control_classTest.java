package de.hbrs.concrete_stack;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.concrete_stack.control.EvaluationRecord;
import de.hbrs.concrete_stack.control.SalesMan;
import de.hbrs.concrete_stack.control.control_class;
import org.bson.Document;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class control_classTest {

    private SalesMan salesman01;
    private EvaluationRecord record01;
    private control_class control = new control_class();
    private MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
    private MongoClient mongoClient = new MongoClient(connectionString);
    private MongoDatabase database = mongoClient.getDatabase("integration_architecture");
    private MongoCollection<Document> salesman = database.getCollection("salesman");
    private MongoCollection<Document> performanceRecords = database.getCollection("performance_records");
    private MongoCollection<Document> goals = database.getCollection("goals");


    @BeforeEach
    public void setup(){
        salesman01 = new SalesMan(-1,"Tobias","Hansen","Marketing");
        record01 = new EvaluationRecord(3000);
        record01.addNewOrdersEvaluation(Document.parse("{'name_of_product':'HooverGo', 'client': 'TelekomAG', client_ranking: 5, 'items': 20}"));
        record01.addNewSocialPerformance(Document.parse("{target_value: 4, actual_value: 5, description: 'communication'}"));
        /*assumeFalse(salesman.count(eq("employee_id", salesman01.getEmployeeId())) > 0,
                "The employeeID that should be used for testing is already in the Database!");
        */
    }

    @Test
    @Order(11)
    void addEvaluationRecordInsertsPerformanceRecordToDB() {
        long beforeSize = getCountOfPerformanceRecords(record01.getYear());
        control.addEvaluationRecord(record01, salesman01.getEmployeeId());
        long afterSize = getCountOfPerformanceRecords(record01.getYear());
        assertEquals(beforeSize + record01.getSocialPerformances().size(), afterSize,
                "Size of performance_records is not correct after insertion!");
        performanceRecords.deleteMany(eq("year", record01.getYear()));
    }


    @Test
    @Order(12)
    void readEvaluationRecordsReadsOnly() {
        control.addEvaluationRecord(record01, salesman01.getEmployeeId());
        assumeTrue(performanceRecords.find(eq("employee_id", salesman01.getEmployeeId())) != null,
                "There was no Record added!");
        long sizeBefore = performanceRecords.count();
        EvaluationRecord result = control.readEvaluationRecords(salesman01.getEmployeeId(), record01.getYear());
        long sizeAfter = performanceRecords.count();
        assertEquals(sizeBefore, sizeAfter, "You lost something!");
        assertNotNull(result, "There should be something! Try harder to find it!");
        assertEquals(record01.getYear(), result.getYear(), "What happened to the Year?!");
        assertEquals(record01.getOrderEvaluations().size(), result.getOrderEvaluations().size());
        performanceRecords.deleteMany(eq("year", record01.getYear()));
        assertEquals(sizeBefore, performanceRecords.count() + 1, "Now nothing was deleted... have you changed the year?");
    }

    @Test
    @Order(13)
    void deleteEvaluationRecordDeletesSomething () {
        control.addEvaluationRecord(record01, salesman01.getEmployeeId());
        long beforeSize = this.getCountOfPerformanceRecords(record01.getYear());
        control.deleteEvaluationRecord(salesman01.getEmployeeId(), record01.getYear());
        long afterSize = this.getCountOfPerformanceRecords(record01.getYear());
        assertEquals(beforeSize - 1, afterSize);
        performanceRecords.deleteMany(eq("year", record01.getYear()));
    }

    @Test
    @Order(1)
    void addSalesman(){
        long beforeSize = salesman.count();
        control.createSalesMan(salesman01);
        long afterCreateSize= salesman.count();
        assertEquals(beforeSize+1,afterCreateSize);
        deleteSalesMan(salesman01.getEmployeeId());
    }

    @Test
    @Order(2)
    void readSalesman(){
        salesman.insertOne(new Document("employee_id", salesman01.getEmployeeId())
                .append("firstname", salesman01.getFirstName())
                .append("lastname", salesman01.getLastName())
                .append("department", salesman01.getDepartment()));
        SalesMan output = control.readSalesMan(salesman01.getEmployeeId());
        assertEquals(output.getFirstName(),salesman01.getFirstName());
        assertEquals(output.getLastName(),salesman01.getLastName());
        assertEquals(output.getDepartment(),salesman01.getDepartment());
        this.deleteSalesMan(salesman01.getEmployeeId());
    }

    @Test
    @Order(3)
    void deleteSalesmanOnlyDeletesOne(){
        salesman.insertOne(new Document("employee_id", salesman01.getEmployeeId()));
        long beforeSize = salesman.count();
        control.deleteSalesMan(salesman01.getEmployeeId());
        long afterDeleteSize = salesman.count();
        assertEquals(beforeSize, afterDeleteSize + 1, "Nothing was deleted!");
        this.deleteSalesMan(salesman01.getEmployeeId());
        afterDeleteSize = salesman.count();
        assertEquals(beforeSize, afterDeleteSize + 1, "Seems like the wrong Document was deleted!");
    }

    @Test
    @Disabled
    void addPerformanceRecordClosesConnection() {
        assertFalse(control.isConnected(), "connection was not closed before Test");
        control.addEvaluationRecord(record01, salesman01.getEmployeeId());
        assertFalse(control.isConnected(), "addPerformanceRecord does not close the connection");
        performanceRecords.deleteMany(eq("year", record01.getYear()));
    }

    //****************************//

    /*
     * Deletes the SalesMan with the given employeeId.
     */
    public void deleteSalesMan(int employeeId){
        salesman.deleteMany(eq("employee_id", employeeId));
    }
    public long getCountOfPerformanceRecords(int year) {
        Document tmp = performanceRecords.find(eq("year", year)).first();
        return tmp == null || tmp.get("social_performance") == null ? 0 : ((ArrayList)tmp.get("social_performance")).size();
    }

}