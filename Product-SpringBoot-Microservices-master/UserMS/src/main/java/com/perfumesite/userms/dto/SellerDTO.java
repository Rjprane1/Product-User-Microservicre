package com.perfumesite.userms.dto;

import com.perfumesite.userms.entity.Seller;

public class SellerDTO 
{
	int sellerId;
	String name;
	String email;
	long phoneNumber;
	String password;
	boolean activate;
	
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean isActivate() {
		return activate;
	}
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	public static SellerDTO toDto(Seller entity)
	{
		SellerDTO seller = new SellerDTO();
		seller.setSellerId(entity.getSellerId());
		seller.setName(entity.getName());
		seller.setEmail(entity.getEmail());
		seller.setPhoneNumber(entity.getPhoneNumber());
		seller.setPassword(entity.getPassword());
		seller.setActivate(entity.isActivate());
		return seller;
	}
	
	public static Seller toEntity(SellerDTO dto)
	{
		Seller seller = new Seller();
		seller.setSellerId(dto.getSellerId());
		seller.setName(dto.getName());
		seller.setEmail(dto.getEmail());
		seller.setPhoneNumber(dto.getPhoneNumber());
		seller.setPassword(dto.getPassword());
		seller.setActivate(dto.isActivate());
		return seller;
	}
}
