package com.perfumesite.userms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.perfumesite.userms.dto.BuyerDTO;
import com.perfumesite.userms.dto.PerfumeDTO;
import com.perfumesite.userms.dto.SellerDTO;
import com.perfumesite.userms.entity.Cart;
import com.perfumesite.userms.service.UserService;

@RestController
@CrossOrigin

public class UserController
{
	@Autowired
	UserService userService;
	
	/*------------------------------------------------------------------- FETCH ALL USERS -------------------------------------------------------------------*/
	//Fetch all buyers
	@GetMapping(value="/buyers",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<BuyerDTO> getAllBuyers()
	{
		return userService.allbuyers();
	}
	//Fetch all sellers
	@GetMapping(value="/sellers",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SellerDTO> getAllSellers()
	{
		return userService.allsellers();
	}
	
	/*--------------------------------------------------------------------- CREATE USER ---------------------------------------------------------------------*/
	//Create a new buyer
	@PostMapping(value="/buyers", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String newBuyer(@RequestBody BuyerDTO user)
	{
		return userService.addBuyer(user);
	}
	//Create a new seller
	@PostMapping(value="/sellers",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String newSeller(@RequestBody SellerDTO user)
	{
		return userService.addSeller(user);
	}
	
	/*------------------------------------------------------------------------- LOGIN -------------------------------------------------------------------------*/
	//Buyer login
	@PostMapping(value="/buyerlogin")
	public String buyerLogin(@RequestBody BuyerDTO buyer)
	{
		return userService.buyerLogin(buyer);
	}
	//Seller login
	@PostMapping(value="/sellerlogin")
	public String sellerLogin(@RequestBody SellerDTO seller)
	{
		return userService.sellerLogin(seller);
	}
	
	/*--------------------------------------------------------------- Seller Activate/Deactivate ---------------------------------------------------------------*/
	//Activate a seller account
	//NOTE: In database 0 is false and 1 is true for activate column.
	@PutMapping(value="activate/sellers/{sellerId}")
	public String toActivated(@PathVariable int sellerId)
	{
		return userService.toActivate(sellerId);
	}
	//Deactivate a seller account
	@PutMapping(value="deactivate/sellers/{sellerId}")
	public String toDeactivate(@PathVariable int sellerId)
	{
		return userService.toDeactive(sellerId);
	}
	
	/*------------------------------------------------------------------ Removing a Seller ------------------------------------------------------------------*/
	@DeleteMapping(value="/sellers/{sellerId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public String removeSeller(@PathVariable int sellerId)
	{
		boolean boolReturn = userService.delSeller(sellerId);
		if(boolReturn == true)
		{
			new RestTemplate().delete("http://localhost:8100/"+sellerId+"/perfumes");
			return "The seller id "+sellerId+" is an deactivated seller and it's products has been deleted from the product db.";
		}
		else
		{
			return"The seller id "+sellerId+" is an activated seller, please deactivate the account.";
		}
	}
	
	/*----------------------------------------------------------- Buyer Adding/Removing a Product -----------------------------------------------------------*/
	//Add to favourites
	@PostMapping(value="/favourites/{buyerId}/{perfumeId}")
	public String addToFavourites(@PathVariable int buyerId, @PathVariable int perfumeId)
	{
		PerfumeDTO perfume = new RestTemplate().getForObject("http://localhost:8100/perfumes/"+perfumeId,PerfumeDTO.class);
		return userService.addToFavourites(buyerId, perfume);
	}
	//Delete from favourites
	@DeleteMapping(value="/favourites/{buyerId}/{perfumeId}")
	public String removeFromFavourites(@PathVariable int buyerId, @PathVariable int perfumeId)
	{
		PerfumeDTO perfume = new RestTemplate().getForObject("http://localhost:8100/perfumes/"+perfumeId,PerfumeDTO.class);
		return userService.removeFromFavourites(buyerId, perfume);
	}
	//Move perfume from favourite to cart and show changes in perfume database
	@PutMapping(value="/favourites/{buyerId}/{perfumeId}")
	public String fromFavtoCT (@PathVariable int buyerId, @PathVariable int perfumeId)
	{
		System.out.println("AP1");
		PerfumeDTO perfume = new RestTemplate().getForObject("http://localhost:8100/perfumes/"+perfumeId,PerfumeDTO.class);
		System.out.println("AP2");
		PerfumeDTO perfumeToUpdate = new RestTemplate().getForObject("http://localhost:8100/perfumes/"+perfumeId,PerfumeDTO.class);
		perfumeToUpdate.setStock(1);
		if(perfume.getStock()>= 1)
		{
			System.out.println("Going inside cart");
			Cart cartEntity = userService.fromFavtoCT(buyerId, perfume);
			System.out.println("done with cart");
			new RestTemplate().put("http://localhost:8100/buyer/"+perfumeId+"/"+1,perfumeToUpdate);
			return perfume.getCompanyName()+" "+perfume.getProductName()+" added to cart. Currently "+(perfume.getStock()-1)+" "+perfume.getProductName()+" available in stock but previously "+perfume.getStock()+" were in stock.";
		}
		else
		{
			return perfume.getProductName()+" is out of stock.";
		} 
	}
	//Add to cart
	@PutMapping(value="/cart/{buyerId}/{perfumeId}/{quantity}")
	public String addTOCart(@PathVariable int buyerId, @PathVariable  int perfumeId, @PathVariable  int quantity)
	{
		PerfumeDTO perfume = new RestTemplate().getForObject("http://localhost:8100/perfumes/"+perfumeId,PerfumeDTO.class);
		PerfumeDTO perfumeToUpdate = new RestTemplate().getForObject("http://localhost:8100/perfumes/"+perfumeId,PerfumeDTO.class);
		perfumeToUpdate.setStock(quantity);
		if(perfume.getStock()>= quantity)
		{
			new RestTemplate().put("http://localhost:8100/buyer/"+perfumeId+"/"+quantity,perfumeToUpdate);
			Cart inCart = userService.addToCart(buyerId, perfumeToUpdate, quantity);
			return perfume.getCompanyName()+" "+perfume.getProductName()+" added to cart. Currently "+(perfume.getStock()-quantity)+" "+perfume.getProductName()+" available in stock but previously "+perfume.getStock()+" were in stock.";
		}
		else
		{
			return "Available stock is "+perfume.getStock();
		}
	}
	//Delete from cart
	@DeleteMapping(value="/cart/{buyerId}/{perfumeId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public String removeFromCart(@PathVariable int buyerId, @PathVariable int perfumeId)
	{
		PerfumeDTO perfume = new RestTemplate ().getForObject("http://localhost:8100/perfumes/"+perfumeId, PerfumeDTO.class);
		Cart cart = userService.removeFromCart(buyerId,perfume);
		PerfumeDTO perfumeToUpload = new RestTemplate ().getForObject ("http://localhost:8100/perfumes/"+perfumeId, PerfumeDTO.class);
		perfumeToUpload.setStock(perfume.getStock()+cart.getQuantity());
		new RestTemplate().put("http://localhost:8100/seller/"+perfumeId+"/"+cart.getQuantity(),perfumeToUpload);
		return "Deleted "+perfume.getProductName()+" from cart database. Currently "+perfumeToUpload.getStock()+" "+perfume.getProductName()+" are available in stock. Previously "+perfume.getStock()+" were avaialable.";
	}
}
