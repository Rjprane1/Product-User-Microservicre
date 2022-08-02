package com.productsite.productms.dto;

import com.productsite.productms.entity.Product;

public class ProductDTO
{
	int prod_id;
	char product_name;
	int price;
	int stock;
	char description;
	char image;
	int seller_id;
	char category;
	char subcategory;
	int productrating;
	
   
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

	//Convert entity into dto
	public static ProductDTO toDTO(Product entityValue)
	{
		ProductDTO perfumeDTOBox = new ProductDTO();
		perfumeDTOBox.setProd_id(entityValue.getProd_id());
		perfumeDTOBox.setProduct_name(entityValue.getProduct_name());
		perfumeDTOBox.setPrice(entityValue.getPrice());
		perfumeDTOBox.setStock(entityValue.getStock());
		perfumeDTOBox.setDescription(entityValue.getDescription());
		perfumeDTOBox.setImage(entityValue.getImage());
		perfumeDTOBox.setSeller_id(entityValue.getSeller_id());
		perfumeDTOBox.setCategory(entityValue.getCategory());
		perfumeDTOBox.setSubcategory(entityValue.getSubcategory());
		perfumeDTOBox.setProductrating(entityValue.getProductrating());
		return perfumeDTOBox;
	}
	
	//Convert dto into entity
	public Product toEntity(ProductDTO dtoValue)
	{
		Product perfumeEntityBox = new Product();
		perfumeEntityBox.setProd_id(dtoValue.getProd_id());
		perfumeEntityBox.setProduct_name(dtoValue.getProduct_name());
		perfumeEntityBox.setPrice(dtoValue.getPrice());
		perfumeEntityBox.setStock(dtoValue.getStock());
		perfumeEntityBox.setDescription(dtoValue.getDescription());
		perfumeEntityBox.setImage(dtoValue.getImage());
		perfumeEntityBox.setSeller_id(dtoValue.getSeller_id());
		perfumeEntityBox.setCategory(dtoValue.getCategory());
		perfumeEntityBox.setSubcategory(dtoValue.getSubcategory());
		perfumeEntityBox.setProductrating(dtoValue.getProductrating());
		return perfumeEntityBox;
	}
	
	
	
	
	
	
	
	
	

	
}
