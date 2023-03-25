package entity;

public enum OrderStatus {
    NEW ("new"), 
    IN_PROGRESS("in progress"), 
    COMPLETED("completed"), 
    CANCELLED ("cancelled"), 
    RETURNED("returned"), 
    PARTIALLY_SHIPPED("partially shipped"), 
    SHIPPED("shipped"), 
    WAITING_EXCHANGE("waiting exchange");
    private final String status;

    private OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
