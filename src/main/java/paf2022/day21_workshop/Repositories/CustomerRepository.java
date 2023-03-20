package paf2022.day21_workshop.Repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf2022.day21_workshop.Models.Customer;
import paf2022.day21_workshop.RowMappers.CustomerMapper;

@Repository
public class CustomerRepository {
    
    @Autowired
    JdbcTemplate template;

    private final String getCusSQL = "select * from customers limit ? offset ?;";
    private final String getByIdSql = "select * from customers where id=?";

    public List<Customer> getCus(int limit, int offset){

        PreparedStatementSetter pss = new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, limit);
                ps.setInt(2, offset);
            }
        };

        return template.query(getCusSQL, pss, new CustomerMapper());
    }

    public Optional<Customer> findById(int id){
        // Must use the step below. 
        // Use ofNullable to cater for no record found will still have EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
        SqlRowSet rs =  template.queryForRowSet(getByIdSql,id);
        if(rs.first()){
            return Optional.of(template.queryForObject(getByIdSql, new CustomerMapper(),id));
        } else {
            return Optional.empty();
        }
    }

}
