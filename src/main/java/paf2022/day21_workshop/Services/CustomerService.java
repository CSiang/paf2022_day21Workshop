package paf2022.day21_workshop.Services;

import java.io.StringReader;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import paf2022.day21_workshop.Models.Customer;

@Service
public class CustomerService {
    
    public JsonObject toJson(Customer cus){

        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", cus.getId()).add("company",cus.getCompany())
           .add("lastName",cus.getLastName()).add("firstName",cus.getFirstName())
           .add("emailAddress", checkNull(cus.getEmailAddress()))
           .add("jobTitle",cus.getJobTitle()).add("businessPhone",cus.getBusinessPhone()).add("homePhone", checkNull(cus.getHomePhone()) )
           .add("mobilePhone",checkNull(cus.getMobilePhone()) ).add("faxNumber",cus.getFaxNumber())
           .add("address",cus.getAddress()).add("city",cus.getCity())
           .add("stateProvince",cus.getStateProvince()).add("zipPostalCode",cus.getZipPostalCode())
           .add("countryRegion",cus.getCountryRegion()).add("webPage",checkNull(cus.getWebPage()) )
           .add("notes",checkNull(cus.getNotes()));

        return job.build();
    }

    public Customer fromJson(String payload){
        Customer cus = new Customer();

        JsonReader jr = Json.createReader(new StringReader(payload));
        JsonObject jo = jr.readObject();
        
        cus.setId(jo.getInt("id"));
        cus.setCompany(jo.getString("company"));
        cus.setLastName(jo.getString("lastName"));
        cus.setFirstName(jo.getString("firstName"));
        cus.setEmailAddress(jo.getString("emailAddress"));
        cus.setJobTitle(jo.getString("jobTitle"));
        cus.setBusinessPhone(jo.getString("businessPhone"));
        cus.setHomePhone(jo.getString("homePhone"));
        cus.setMobilePhone(jo.getString("mobilePhone"));
        cus.setFaxNumber(jo.getString("faxNumber"));
        cus.setAddress(jo.getString("address"));
        cus.setCity(jo.getString("city"));
        cus.setStateProvince(jo.getString("stateProvince"));
        cus.setZipPostalCode(jo.getString("zipPostalCode"));
        cus.setCountryRegion(jo.getString("countryRegion"));
        cus.setWebPage(jo.getString("webPage"));
        cus.setNotes(jo.getString("notes"));

        return cus;
    }

    public String checkNull(String input){
        if(null == input){
            return "";
        } else {
            return input;
        }
    }



}
