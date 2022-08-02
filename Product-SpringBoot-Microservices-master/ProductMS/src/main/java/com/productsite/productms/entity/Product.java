package com.productsite.productms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Product")
public class Product 
{
	@Id
	@Column(name="`prod_id`")
	int prod_id;
    @Column(name="`product_name`")
	char product_name;
	int price;
	int stock;
	char description;
	char image;
	@Column(name="`seller_id`")
	int seller_id;
	char category;
	char subcategory;
	int  productrating;
	

	
	public int getProd_id() {
		return prod_id;
	}



	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public char getProduct_name() {
		return product_name;
	}



	public void setProduct_name(char product_name) {
		this.product_name = product_name;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	public char getDescription() {
		return description;
	}



	public void setDescription(char description) {
		this.description = description;
	}



	public char getImage() {
		return image;
	}



	public void setImage(char image) {
		this.image = image;
	}



	public int getSeller_id() {
		return seller_id;
	}



	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}



	public char getCategory() {
		return category;
	}



	public void setCategory(char category) {
		this.category = category;
	}



	public char getSubcategory() {
		return subcategory;
	}



	public void setSubcategory(char subcategory) {
		this.subcategory = subcategory;
	}



	public int getProductrating() {
		return productrating;
	}



	public void setProductrating(int productrating) {
		this.productrating = productrating;
	}



	public Product()
	{
		super();
	}
	

}
