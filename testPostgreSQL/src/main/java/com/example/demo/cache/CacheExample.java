package com.example.demo.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheExample {

	@Cacheable(value="customers", key="#value")
	public String CachMethod(String value) {
		 System.out.println("Service processing...");
	        try{
	            Thread.sleep(3000); 
	        }catch(Exception e){
	       }
		return "caschden mi geldi ? " + value ;
	}
}
