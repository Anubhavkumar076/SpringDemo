package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AddAddress;
import com.example.demo.model.Books;
import com.example.demo.model.CartSessionInfo;
import com.example.demo.model.Cities;
import com.example.demo.model.DonationRequest;
import com.example.demo.model.OldPassword;
import com.example.demo.model.OrderDetails;
import com.example.demo.model.Orders;
import com.example.demo.model.ProductFullData;
import com.example.demo.model.SelectDeliveryAddress;
import com.example.demo.model.SignIn;
import com.example.demo.model.States;
import com.example.demo.model.Users;
import com.example.demo.repository.BookPreviewRepository;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.DonationRequestsRepository;
import com.example.demo.repository.StateRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.Services;

@RestController  
public class Controllers {

	    @Autowired  
	    JdbcTemplate jdbc;    
	    
	    @Autowired
	    Services service;
	    
	    
	    
	  //****************For create new user***************//
	    @PostMapping("/userpost/{email}")
	      public Users createUser(@RequestBody Users users, @PathVariable String email) {
	          int id=usersRepository.findTopByOrderByIdDesc().getId();
	          users.setId(id+1);
	          users.setEmail(email);
	          Users savedUsers = usersRepository.save(users);
	          return savedUsers;

	      }
	    
	    
	    //***************************DONATION REQUEST*****************************
	    @Autowired
	    private DonationRequestsRepository donationRequestsRepository;
	@PostMapping("/donationpost/{donor_id}")
	    public DonationRequest createDonationRequest(@RequestBody DonationRequest donationRequest, @PathVariable String donor_id) {
	    	donationRequest.setDonor_id(donor_id);
	    	int id=donationRequestsRepository.findTopByOrderByIdDesc().getId();
	    	donationRequest.setId(id+1);
	        DonationRequest savedDonationRequest = donationRequestsRepository.save(donationRequest);
	        return savedDonationRequest;

	    }
	    
	    //**********************USERS DATA**************************************
	    
	    @Autowired
	    private UsersRepository usersRepository;
	@GetMapping("/getUsers/{email}")
	    public Users getUsersByEmail(@PathVariable String email)
	    {
	    return usersRepository.findUsersByEmail(email);
	    }
	    
	    
	    //********************GET CITY*********************************************
	    
	    @Autowired
	    private CityRepository cityRepository;
	@GetMapping("/getCity")
	    public List<Cities> getCities(){
	    	return cityRepository.findAll();
	    }
	    
	    
	    
	    //************************GET STATE************************************8
	    @Autowired
	    private StateRepository stateRepository;
	@GetMapping("/getState")
	    public List<States> getStates()
	    {
	    return stateRepository.findAll();
	    }
	    
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
	    
	    
	    
 //*******************************CART SESSION CONTROLLER*************************************************************************
	    
	    @RequestMapping("/getbooks")  
	    @Produces(MediaType.APPLICATION_XML)
	    public List<Books> getAllBooks(){  
	        return service.getAllBooks();  
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
	    
	    //*****************************CHANGE PASSWORD***********************************************
	    
	    @PostMapping("/old_pass/{id}/{pass}")
	    @Produces(MediaType.APPLICATION_XML)
	    public void insertPassword(@PathVariable("id") int id,@PathVariable("pass") String pass)
	    {
	    	service.changePass(id,pass);
	    }
	    
	    
	    //**************************CART SESSION ENTRY************************************
	    @PostMapping("/cart_insert/{id}/{book_id}")
	    @Produces(MediaType.APPLICATION_XML)
	    public void insertPassword(@PathVariable("id") int id,@PathVariable("book_id") int book_id)
	    {
	    	service.insertCartValue(id, book_id);
	    }
	    
	  //**************************UPDATE ADDRESS************************************
	    @PostMapping("/address_update/{address_id}/{user_id}/{rec_name}/{pincode}/{address}/{landmark}/{state_id}/{city_id}/{phone_no}")
	    @Produces(MediaType.APPLICATION_XML)
	    public void updateAddress(@PathVariable("address_id") int address_id,@PathVariable("user_id") int user_id,@PathVariable("rec_name") String rec_name,@PathVariable("pincode") int pincode,
	    		@PathVariable("address") String address,@PathVariable("landmark") String landmark,@PathVariable("state_id") int state_id,@PathVariable("city_id") int city_id,@PathVariable("phone_no") long phone_no)
	    {
	    	service.updateAddress(address_id,user_id, rec_name, pincode, address, landmark, state_id, city_id, phone_no);
	    }
	    
	  //**************************REMOVE BOOKS FROM CART************************************
	    @PostMapping("/remove_cart/{book_id}/{user_id}")
	    @Produces(MediaType.APPLICATION_XML)
	    public void removeBooks(@PathVariable("book_id") int book_id,@PathVariable("user_id") int user_id)
	    {
	    	service.removeBooks(book_id, user_id);
	    }
	    
	  //**************************MINUS FROM CART************************************
	    @PostMapping("/remove_book/{book_id}/{user_id}")
	    @Produces(MediaType.APPLICATION_XML)
	    public void removeBook(@PathVariable("book_id") int book_id,@PathVariable("user_id") int user_id)
	    {
	    	service.removeBook(book_id, user_id);
	    }
	    
	    //*************************ADD TO WISHLIST***************************************
	    @PostMapping("/add_wishlist/{book_id}/{user_id}")
	    @Produces(MediaType.APPLICATION_XML)
	    public void addWishlist(@PathVariable("book_id") int book_id,@PathVariable("user_id") int user_id)
	    {
	    	service.addWishlist(book_id, user_id);
	    }
	    
	    
	    //************************Sign In***************************************************
	    @PostMapping("/user_signin/{user_id}")
	    @Produces(MediaType.APPLICATION_XML)
	    public List<SignIn> signIn(@PathVariable("user_id") String user_id)
	    {
	    	return service.userSignin(user_id);	
	    }
	    
	  //************************Google and Fb Sign In***************************************************
	    @PostMapping("/google_signin/{user_id}/{first_name}/{last_name}")
	    @Produces(MediaType.APPLICATION_XML)
	    public void googleSignIn(@PathVariable("user_id") String user_id,@PathVariable("first_name") String first_name,@PathVariable("last_name") String last_name)
	    {
	    	 service.googleSignIn(user_id, first_name, last_name);
	    	
	    	
	    }
	    
	    
	
}
