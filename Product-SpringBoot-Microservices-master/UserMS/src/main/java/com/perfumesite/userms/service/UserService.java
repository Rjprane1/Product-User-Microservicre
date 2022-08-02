package com.perfumesite.userms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfumesite.userms.dto.BuyerDTO;
import com.perfumesite.userms.dto.PerfumeDTO;
import com.perfumesite.userms.dto.SellerDTO;
import com.perfumesite.userms.entity.Buyer;
import com.perfumesite.userms.entity.Cart;
import com.perfumesite.userms.entity.Seller;
import com.perfumesite.userms.entity.Favourite;
import com.perfumesite.userms.repository.BuyerRepository;
import com.perfumesite.userms.repository.CartRepository;
import com.perfumesite.userms.repository.SellerRepository;
import com.perfumesite.userms.repository.FavouriteRepository;

@Service
public class UserService 
{
	@Autowired
	BuyerRepository buyerRepo;
	@Autowired
	CartRepository cartRepo;
	@Autowired
	SellerRepository sellerRepo;
	@Autowired
	FavouriteRepository wishlistRepo;
	
	/*------------------------------------------------------------- FETCH ALL USERS -------------------------------------------------------------*/
	//Fetch all buyers
	public List<BuyerDTO> allbuyers()
	{
		List<BuyerDTO> buyers = new ArrayList<>();
		List<Buyer> buyersEntity = buyerRepo.findAll();
		for(Buyer buyerEntity:buyersEntity)
		{
			BuyerDTO buyer = BuyerDTO.toDTO(buyerEntity);
			buyers.add(buyer);
		}
		return buyers;
	}
	//Fetch all sellers
	public List<SellerDTO> allsellers()
	{
		List<SellerDTO> sellers = new ArrayList<>();
		List<Seller> sellersEntity = sellerRepo.findAll();
		for(Seller sellerEntity:sellersEntity)
		{
			SellerDTO seller = SellerDTO.toDto(sellerEntity);
			sellers.add(seller);
		}
		return sellers;
	}
	
	/*------------------------------------------------------------- CREATE USER -------------------------------------------------------------*/
	//Create a new buyer
	public String addBuyer(BuyerDTO buyerDTO)
	{
		if (buyerRepo.existsByPhoneNumber(buyerDTO.getPhoneNumber()))
		{
			return "Please add an unregistered phone number.";
		}
		else
		{
			Buyer buyer = BuyerDTO.toEntity(buyerDTO);
			buyerRepo.save(buyer);
			return "New buyer "+buyer.getName()+" added successfully.";
		}
	}
	//Create a new seller
	public String addSeller(SellerDTO sellerDTO)
	{
		if(sellerRepo.existsByPhoneNumber(sellerDTO.getPhoneNumber()))
		{
			return "Please add an unregistered phone number.";
		}
		else
		{
			Seller seller = SellerDTO.toEntity(sellerDTO);
			sellerRepo.save(seller);
			return "New buyer "+seller.getName()+" added successfully.";
		}
	}
	
	/*--------------------------------------------------------------- LOGIN ---------------------------------------------------------------*/
	//Buyer login
	public String buyerLogin(BuyerDTO buyer)
	{
		if(buyerRepo.existsByEmailId(buyer.getEmail()))
		{
			Optional <Buyer> optionalEntity = buyerRepo.findById(buyer.getBuyerId());
			Buyer buyerEntity = optionalEntity.get();
			BuyerDTO buyerDTO = BuyerDTO.toDTO(buyerEntity);
			String password = buyerDTO.getPassword();
			if(buyer.getPassword().equals(password))
            {
            	return "Welcome "+buyer.getName();
            }
			else
			{
				return "In-valid password.";
			}
		}
		return "User does not exist in the database";
	}
	//Seller login
	public String sellerLogin(SellerDTO seller)
	{
		if(sellerRepo.existsByEmail(seller.getEmail()))
		{
			Optional<Seller> optionalEntity = sellerRepo.findById(seller.getSellerId());
			Seller sellerEntity = optionalEntity.get();
			SellerDTO sellerDTO = SellerDTO.toDto(sellerEntity);
			String password = sellerDTO.getPassword();
			if(seller.getPassword().equals(password))
			{
				return "Welcome "+seller.getName();
			}
			else
			{ 
				return"In-valid password";
			}
		}
		else
		{
			return "User does not exist in the database";
		}
	}
	
	/*--------------------------------------------------------------- Seller Activate/Deactivate ---------------------------------------------------------------*/
	//Activate a seller account
	public String toActivate(int sellerId)
	{
		Seller seller = sellerRepo.getById(sellerId);
		if(seller.isActivate())
		{
			return "Seller "+seller.getName()+" is already activated.";
		}
		else
		{
			seller.setActivate(true);
			sellerRepo.save(seller);	
			return "Seller "+seller.getName()+" is now activated.";
		}
	}
	//Deactivate a seller account
	public String toDeactive(int sellerId)
	{
		Seller seller = sellerRepo.getById(sellerId);
		if(seller.isActivate())
		{
			seller.setActivate(false);
			sellerRepo.save(seller);	
			return "Seller "+seller.getName()+" is now deactivated.";
		}
		else
		{
			return "Seller "+seller.getName()+" is already deactivated.";
		}
	}
	
	/*------------------------------------------------------------------ Removing a Seller ------------------------------------------------------------------*/
	public boolean delSeller(int sellerId)
	{
		Optional <Seller> optionalEntity = sellerRepo.findById(sellerId);
		Seller sellerEntity = sellerRepo.findById(sellerId).get();
		if(sellerEntity.isActivate())
		{
			return false;
		}
		else
		{
			sellerRepo.delete(sellerEntity);
			return true;
		}
	}

	/*------------------------------------------------------------- Buyer Adding/Removing Product -------------------------------------------------------------*/
	//Add a perfume to favourite
	public String addToFavourites(int buyerId, PerfumeDTO perfume)
	{
		Favourite fav = new Favourite();
		fav.setBuyerId(buyerId);
		fav.setProductId(perfume.getProductId());
		wishlistRepo.save(fav);
		return "Added "+perfume.getProductName()+" to the favourites";
	}
	//Remove a perfume from favourite
	public String removeFromFavourites(int buyerId, PerfumeDTO perfume)
	{
		wishlistRepo.delFromFavourite(buyerId, perfume.getProductId());
		return "Deleted "+perfume.getProductName()+" from favourites";
	}
	//Move perfume from favourite to cart
	public Cart fromFavtoCT(int buyerId, PerfumeDTO perfume)
	{
		Cart cart = new Cart();
		cart.setBuyerId(buyerId);
		cart.setProductId(perfume.getProductId());
		cart.setQuantity(1);
		this.removeFromFavourites(buyerId, perfume);
		cartRepo.save(cart);
		return cart;
	}
	//Add a perfume to Cart
	public Cart addToCart(int buyerId, PerfumeDTO perfume, int quantity)
	{
		Cart cartEntity = new Cart();
		cartEntity.setBuyerId(buyerId);
		cartEntity.setProductId(perfume.getProductId());
		cartEntity.setQuantity(quantity);
		cartRepo.save(cartEntity);
		return cartEntity;
	}
	//Remove a perfume from Cart
	public Cart removeFromCart(int buyerId, PerfumeDTO perfume)
	{
		Cart cartEntity = cartRepo.findByIdQuery(buyerId, perfume.getProductId()).get();
		cartRepo.delFromCart(buyerId, perfume.getProductId());
		return cartEntity;
	}
}
