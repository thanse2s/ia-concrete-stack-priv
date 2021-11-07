package de.hbrs.concrete_stack.control;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class EvaluationRecord {

    private int year;
    private List<SocialPerformance> socialPerformanceList;
    private List<OrdersEvaluation> ordersEvaluationList;

    protected class SocialPerformance {
        protected int targetValue;
        protected int actualValue;
        protected String description;

        protected SocialPerformance(int targetValue, int actualValue, String description) {
            this.targetValue = targetValue;
            this.actualValue = actualValue;
            this.description = description;
        }
        protected SocialPerformance(Document doc) {
            this.targetValue = doc.getInteger("target_value");
            this.actualValue = doc.getInteger("actual_value");
            this.description = doc.getString("description");
        }

        public int getActualValue() {
            return actualValue;
        }
        public String getDescription() {
            return description;
        }
        public int getTargetValue() {
            return targetValue;
        }
    }
     protected class OrdersEvaluation {
        protected String nameOfProduct;
        protected String client;
        protected int clientRanking;
        protected int items;

        protected OrdersEvaluation(String nameOfProduct, String client, int clientRanking, int items) {
            this.nameOfProduct = nameOfProduct;
            this.client = client;
            this.clientRanking = clientRanking;
            this.items = items;
        }
        public OrdersEvaluation(Document doc) {
            this.nameOfProduct = doc.getString("name_of_product");
            this.client = doc.getString( "client");
            this.clientRanking = doc.getInteger("client_ranking");
            this.items = doc.getInteger("items");
        }

        public String getNameOfProduct() {
            return nameOfProduct;
        }
        public String getClient() {
            return client;
        }
        public int getClientRanking() {
            return clientRanking;
        }
        public int getItems() {
            return items;
        }
    }

    public EvaluationRecord (int year, List<SocialPerformance> socialPerformanceList, List<OrdersEvaluation> ordersEvaluationList) {
        this.year = year;
        this.socialPerformanceList = socialPerformanceList;
        this.ordersEvaluationList = ordersEvaluationList;
    }
    public EvaluationRecord(int year) {
        this.year = year;
        this.socialPerformanceList = new ArrayList<>();
        this.ordersEvaluationList = new ArrayList<>();
    }

    public int getYear() {
        return year;
    }

    public List<SocialPerformance> getSocialPerformances() {
        return socialPerformanceList;
    }
    public List<OrdersEvaluation> getOrderEvaluations() {
        return ordersEvaluationList;
    }
    public boolean addNewOrdersEvaluation(Document doc) {
        this.ordersEvaluationList.add(new OrdersEvaluation(doc));
        return true;
    }
    public boolean addNewOrdersEvaluation(String nameOfProduct, String client, int clientRanking, int items) {
        this.ordersEvaluationList.add(new OrdersEvaluation(nameOfProduct, client, clientRanking, items));
        return true;
    }
    public boolean addNewSocialPerformance(Document doc) {
        this.socialPerformanceList.add(new SocialPerformance(doc));
        return true;
    }
    public boolean addNewSocialPerformance(int targetValue, int actualValue, String description) {
        this.socialPerformanceList.add(new SocialPerformance(targetValue, actualValue, description));
        return true;
    }
}
