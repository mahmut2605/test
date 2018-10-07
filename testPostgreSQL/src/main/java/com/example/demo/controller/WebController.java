package com.example.demo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cache.CacheExample;
import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;
 
@RestController
public class WebController {
	@Autowired
	CustomerRepository repository;
	
	@Autowired
	CacheExample cacheExample;
	
	@Autowired
    private CacheManager cacheManager;   
	
	@RequestMapping("/save")
	public String process(){
		// save a single Customer
		repository.save(new Customer("Jack", "Smith"));
		
		// save a list of Customers
		repository.saveAll(Arrays.asList(new Customer("Adam", "Johnson"), new Customer("Kim", "Smith"),
										new Customer("David", "Williams"), new Customer("Peter", "Davis")));
	
		return "Done";
	}
	
	
	@RequestMapping("/findall")
	public String findAll(){
		String result = "";
		
		for(Customer cust : repository.findAll()){
			result += cust.toString() + "<br>";
		}
		
		return result;
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		result = repository.findById(id).toString();
		return result;
	}
	
	@RequestMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName){
		String result = "";
		
		for(Customer cust: repository.findByLastName(lastName)){
			result += cust.toString() + "<br>"; 
		}
		
		return result;
	}
	
	@RequestMapping("/findbyfirstname")
	public String fetchDataFirstByName(@RequestParam("firstname") String firstName){
		String result = "";
		
		for(Customer cust: repository.findByFirstName(firstName)){
			result += cust.toString() + "<br>"; 
		}
		
		return result;
	}
	
	@RequestMapping("/updateCustomerWhereId")
	public String updateCustomer(@RequestParam("id") long id){
		Customer customer ;
		
		customer = repository.findById(id).get();
		
		customer.setFirstName("değiştirdik");
		repository.save(customer);
		
		return "updated";
	}
	

	@RequestMapping("/cacheExample")
	public String regCacheExample(@RequestParam("value") String value){
		 
		return cacheExample.CachMethod(value);
	}
	
	@RequestMapping(value = "clearCache")
    public void clearCache(){
        for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();            // clear cache by name
        }
    }
	
}