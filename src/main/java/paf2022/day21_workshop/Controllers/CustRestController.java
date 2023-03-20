package paf2022.day21_workshop.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import paf2022.day21_workshop.Models.Customer;
import paf2022.day21_workshop.Models.Order;
import paf2022.day21_workshop.Repositories.CustomerRepository;
import paf2022.day21_workshop.Repositories.OrderRepository;
import paf2022.day21_workshop.Services.CustomerService;
import paf2022.day21_workshop.Services.OrderService;

@RestController
@RequestMapping(path = "/api")
public class CustRestController {
    
    @Autowired
    CustomerRepository cusRepo;

    @Autowired
    CustomerService cusSvc;

    @Autowired
    OrderRepository ordRepo;

    @Autowired
    OrderService ordSvc;


    @GetMapping(path = "/customers")
    public ResponseEntity<String> getCusts(@RequestParam(defaultValue = "5") int limit, @RequestParam(defaultValue = "0") int offset){

        List<Customer> custs =  cusRepo.getCus(limit, offset);
        if(custs.size() >0){
           
            List<JsonObject> jos = custs.stream().map(e -> cusSvc.toJson(e)).collect(Collectors.toList()); 

            JsonArrayBuilder jab = Json.createArrayBuilder();

            for(JsonObject jo: jos){
                jab.add(jo);
            }

            return new ResponseEntity<String>(jab.build().toString(), HttpStatus.OK);

        } else {

            String errMsg = Json.createObjectBuilder().add("message","No Record Found!!!").build().toString();

            return new ResponseEntity<String>(errMsg, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/customer/{id}")
    public ResponseEntity<String> getbyId(@PathVariable("id") int id ){


        Optional<Customer> opt = cusRepo.findById(id);

        if(opt.isEmpty()){
            
            String errMsg = Json.createObjectBuilder().add("message","Customer with ID: %d is not found.".formatted(id)).build().toString();
            
            return ResponseEntity.status(404).body(errMsg);

        } else {
            String payload =  cusSvc.toJson(opt.get()).toString();

            return ResponseEntity.ok().body(payload);
        }
    }

    @GetMapping(path = "/customer/{id}/orders")
    public ResponseEntity<String> getOrdersById(@PathVariable int id){

        Optional<Customer> opt = cusRepo.findById(id);

        if(opt.isEmpty()){
            
            String errMsg = Json.createObjectBuilder().add("message","Customer with ID: %d is not found.".formatted(id)).build().toString();
            
            return ResponseEntity.status(404).body(errMsg);
        }

        List<Order> orders = ordRepo.getById(id);

        if(orders.size()>0){

            List<JsonObject> ordJson = orders.stream().map(e -> ordSvc.toJson(e)).collect(Collectors.toList());
            JsonArrayBuilder jab = Json.createArrayBuilder();
            for(JsonObject ord: ordJson){
                jab.add(ord);
            }

            return ResponseEntity.ok().body(jab.build().toString());
        } else {

            JsonArray emptyArray = Json.createArrayBuilder().build();

            return ResponseEntity.status(404).body(emptyArray.toString());
        }

        

    }

}
