package com.perfumesite.userms.dto;

import com.perfumesite.userms.entity.Cart;

public class FavouriteDTO 
{
	int buyerId;
	int productId;
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


	public CartDTO toDTO(Cart entity)
	{
		CartDTO dto = new CartDTO();
		dto.setBuyerId(entity.getBuyerId());
		dto.setProductId(entity.getProductId());
		return dto; 
	}
	
	public Cart toEntity(CartDTO dto)
	{
		Cart entity = new Cart();
		entity.setBuyerId(dto.getBuyerId());
		entity.setProductId(dto.getProductId());
		return entity;
	}
}
