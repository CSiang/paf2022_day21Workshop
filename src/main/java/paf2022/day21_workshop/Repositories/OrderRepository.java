package paf2022.day21_workshop.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paf2022.day21_workshop.Models.Order;
import paf2022.day21_workshop.RowMappers.OrderMapper;

@Repository
public class OrderRepository {
    
    @Autowired
    JdbcTemplate template;

    private final String getByIdSql = "select * from orders where customer_id = ?";

    public List<Order> getById(int id){

        return template.query(getByIdSql, new OrderMapper(), id);
    }

}
