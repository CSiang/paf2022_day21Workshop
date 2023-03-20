package paf2022.day21_workshop.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import paf2022.day21_workshop.Models.Order;
import paf2022.day21_workshop.Repositories.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository ordRepo;

    public JsonObject toJson(Order order){

        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id",order.getId()).add("customerId",order.getCustomerId())
        .add("orderDate", dateToString(order.getOrderDate()))
        .add("shipDate", dateToString(order.getShipDate()));

        return job.build();
    }

    public String dateToString(java.sql.Date date){
        if(null == date){
            return "";
        } else{
            return date.toString();
        }


    }

    public Date toUtilDate(java.sql.Date date){

        return new java.util.Date(date.getTime());
    }

    public java.sql.Date toSqlDate(java.util.Date date){

        return new java.sql.Date(date.getTime());
    }

    public java.sql.Date fromJsonDate(String dateString) throws ParseException{

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

        return toSqlDate(date);
    }

}
