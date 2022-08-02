package com.perfumesite.userms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CartCompKey.class)
public class Cart
{
	@Id
	@Column(name="`buyer id`")
	int buyerId;
	@Id
	@Column(name="`product id`")
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
	
}
