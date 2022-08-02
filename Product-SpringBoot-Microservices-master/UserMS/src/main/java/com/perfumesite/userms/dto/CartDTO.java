package com.perfumesite.userms.dto;

import com.perfumesite.userms.entity.Cart;

public class CartDTO 
{
	int buyerId;
	int productId;
	int quantity;
	
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public CartDTO toDTO(Cart entity)
	{
		CartDTO dto = new CartDTO();
		dto.setBuyerId(entity.getBuyerId());
		dto.setProductId(entity.getProductId());
		dto.setQuantity(entity.getQuantity());
		return dto; 
	}
	
	public Cart toEntity(CartDTO dto)
	{
		Cart entity = new Cart();
		entity.setBuyerId(dto.getBuyerId());
		entity.setProductId(dto.getProductId());
		entity.setQuantity(dto.getQuantity());
		return entity;
	}

}
