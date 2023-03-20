package paf2022.day21_workshop.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import paf2022.day21_workshop.Models.Customer;

public class CustomerMapper implements RowMapper<Customer> {

    @Override
    @Nullable
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Customer cus = new Customer();

        cus.setId(rs.getInt("id"));
        cus.setCompany(rs.getString("company"));
        cus.setLastName(rs.getString("last_name"));
        cus.setFirstName(rs.getString("first_name"));
        cus.setEmailAddress(rs.getString("email_address"));
        cus.setJobTitle(rs.getString("job_title"));
        cus.setBusinessPhone(rs.getString("business_phone"));
        cus.setHomePhone(rs.getString("home_phone"));
        cus.setMobilePhone(rs.getString("mobile_phone"));
        cus.setFaxNumber(rs.getString("fax_number"));
        cus.setAddress(rs.getString("address"));
        cus.setCity(rs.getString("city"));
        cus.setStateProvince(rs.getString("state_province"));
        cus.setZipPostalCode(rs.getString("zip_postal_code"));
        cus.setCountryRegion(rs.getString("country_region"));
        cus.setWebPage(rs.getString("web_page"));
        cus.setNotes(rs.getString("notes"));

        return cus;
    }
    
}
