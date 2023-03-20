package paf2022.day21_workshop.Models;

import java.sql.Date;

public class Order {
    
    private int Id;
    private int customerId;
    private Date orderDate;
    private Date shipDate;
    
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public Date getShipDate() {
        return shipDate;
    }
    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    @Override
    public String toString() {
        return "Order [Id=" + Id + ", customerId=" + customerId + ", orderDate=" + orderDate + ", shipDate=" + shipDate
                + "]";
    }
}
