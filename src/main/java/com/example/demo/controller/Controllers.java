package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AddAddress;
import com.example.demo.model.Books;
import com.example.demo.model.CartSessionInfo;
import com.example.demo.model.OldPassword;
import com.example.demo.model.OrderDetails;
import com.example.demo.model.Orders;
import com.example.demo.model.ProductFullData;
import com.example.demo.model.SelectDeliveryAddress;
import com.example.demo.repository.BookPreviewRepository;
import com.example.demo.service.Services;

@RestController  
public class Controllers {

	    @Autowired  
	    JdbcTemplate jdbc;    
	    
	    @Autowired
	    Services service;
	    
	    @Autowired
		private BookPreviewRepository bprepository;
		
		@RequestMapping("/preview/{category}")
		public List<Books> getBookDetails(@PathVariable int category)
		{
			
			
			return bprepository.findBycategory(category);
			
		}
		
	    
	    
	    //*******************************CART SESSION CONTROLLER*************************************************************************
	    
	    @RequestMapping("/cart/{user_id}")  
	    @Produces(MediaType.APPLICATION_XML)
	    public List<CartSessionInfo> getCartData(@PathVariable int user_id){  
	        return service.getCartDetails(user_id);  
	    }  
	    
	    
	    //************************PRODUCT FULL VIEW DATA*******************************************************
	    @RequestMapping("/productfullview/{book_id}")  
	    @Produces(MediaType.APPLICATION_XML)
	    public List<ProductFullData> getProductFullData(@PathVariable int book_id){  
	        return service.getFullProductDetails(book_id);
	    }  
	    
	    //************************PRODUCT FULL VIEW DATA*******************************************************
	    @RequestMapping("/selectadd/{user_id}")  
	    @Produces(MediaType.APPLICATION_XML)
	    public List<SelectDeliveryAddress> getDeliveryAddress(@PathVariable int user_id){  
	        return service.getDeliveryAddress(user_id);
	    }  
	    
	    //************************PRODUCT FULL VIEW DATA*******************************************************
	    @RequestMapping("/my_orders/{user_id}")  
	    @Produces(MediaType.APPLICATION_XML)
	    public List<Orders> getOrders(@PathVariable int user_id){  
	        return service.getOrders(user_id);
	    }  
	    
	  //************************Order Details*******************************************************
	    @RequestMapping("/order_details/{order_id}")  
	    @Produces(MediaType.APPLICATION_XML)
	    public List<OrderDetails> getOrderDetails(@PathVariable int order_id){  
	        return service.getOrderDetails(order_id);
	    }  
	    
	  //************************GET Old Password*******************************************************
	    @RequestMapping("/oldpass/{id}")  
	    @Produces(MediaType.APPLICATION_XML)
	    public List<OldPassword> getOldPass(@PathVariable int id){  
	        return service.getOldPassword(id);
	    }  
	    
	    //**********************************INSERT ADDRESS**************************************
	    
	    @PostMapping("/address_insert/{user_id}/{rec_name}/{pincode}/{address}/{landmark}/{state}/{city}/{phone_no}")
	    @Produces(MediaType.APPLICATION_XML)
	    public void insertAddress(@PathVariable("user_id") int user_id,@PathVariable("rec_name") String rec_name,@PathVariable("pincode") int pincode,
	    		@PathVariable("address") String address,@PathVariable("landmark") String landmark,@PathVariable("state") String state,@PathVariable("city") String city,@PathVariable("phone_no") long phone_no)
	    {
	    	service.addAddress(user_id, rec_name, pincode, address, landmark, state, city, phone_no);
	    }
	    
	    @PostMapping("/old_pass/{id}/{pass}")
	    @Produces(MediaType.APPLICATION_XML)
	    public void insertPassword(@PathVariable("id") int id,@PathVariable("pass") String pass)
	    {
	    	service.changePass(id,pass);
	    }
	
}
