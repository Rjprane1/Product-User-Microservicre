package com.perfumesite.userms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="favourite")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(FavouriteCompKey.class)
public class Favourite
{
	@Id
	@Column(name="`buyer id`")
	int buyerId;
	@Id
	@Column(name="`product id`")
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
	
}
