package com.productsite.productms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompKey.class)
public class Subscription 
{
	@Id
	@Column(name="buyer_id")
	int buyerId;
	@Id
	@Column(name="product_id")
	int productId;
	@Column(name="quantity")
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
	public char getQuantity() {
		return quantity;
	}
	public void setQuantity(char quantity) {
		this.quantity = quantity;
	}
	
	
}
