package com.test;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Customer {
	private String  id;
	private String name;
	
	public Customer(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonIgnore
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return "HangZhou";
	}
	public String getBirth() {
		return "1995-06-20";
	}
	public static void main(String[] args) throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//		Customer customer = new Customer("23", "minshuaibo");
//		String jsonStr = mapper.writeValueAsString(customer);
//		System.out.println(jsonStr);
//		List<Customer> customers = Arrays.asList(customer,new Customer("88", "saint"));
//		String jsonStr2 = mapper.writeValueAsString(customers);//操作集合
//		System.out.println(jsonStr2);
		Customer customer = new Customer("23", "minshuaibo");
		Gson gson = new Gson();
		String jsonObject = gson.toJson(customer);
		System.out.println(jsonObject);
		String jsonString = "{\"id\":\"怪盗kidou\",\"name\":24}";
		Customer user = gson.fromJson(jsonString, Customer.class);
		System.out.println(user.getId()+" "+user.getName());
	}
}
