package paf2022.day21_workshop.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import paf2022.day21_workshop.Models.Order;

public class OrderMapper implements RowMapper<Order> {

    @Override
    @Nullable
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

        Order ord = new Order();

        ord.setId(rs.getInt("id"));
        ord.setCustomerId(rs.getInt("customer_id"));   
        ord.setOrderDate(rs.getDate("order_date"));
        ord.setShipDate(rs.getDate("shipped_date"));
        
        return ord;
    }
}
