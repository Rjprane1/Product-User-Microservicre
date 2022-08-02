package com.productsite.productms.dto;

import com.productsite.productms.entity.Subscription;

public class SubscriptionDTO 
{
	int buyerId;
	int productId;
	char quantity;
	
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
	public void setQuantity(char quantity) {
		this.quantity = quantity;
	}
	
	//Convert entity into dto
	public static SubscriptionDTO toDTO(Subscription entityValue)
	{
		SubscriptionDTO subscriptionDTOBox = new SubscriptionDTO();
		subscriptionDTOBox.setBuyerId(entityValue.getBuyerId());
		subscriptionDTOBox.setProductId(entityValue.getProductId());
		subscriptionDTOBox.setQuantity(entityValue.getQuantity());
		return subscriptionDTOBox;
	}
	
	//Convert dto into entity
	public Subscription toEntity(SubscriptionDTO dtoValue)
	{
		Subscription subscribeEntityBox = new Subscription();
		subscribeEntityBox.setBuyerId(dtoValue.getBuyerId());
		subscribeEntityBox.setProductId(dtoValue.getProductId());
		subscribeEntityBox.setQuantity((char) dtoValue.getQuantity());
		return subscribeEntityBox;
	}
}
