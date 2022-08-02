package com.productsite.productms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productsite.productms.dto.ProductDTO;
import com.productsite.productms.entity.Product;
import com.productsite.productms.service.ProductService;

@RestController
@CrossOrigin

public class ProductController 
{
	@Autowired
	ProductService productService;
	
	//Upload a perfume
	@PostMapping(value="/products", consumes= MediaType.APPLICATION_JSON_VALUE)
	public String uploadProduct(@RequestBody ProductDTO product ) 
	{
		Product productEntity = productService.uploadProduct(product);
		return "Uploaded "+productEntity.getProduct_name()+" to the database successfully.";
	}
	
	//Delete perfume from the database
	@DeleteMapping(value="/products/{prod_id}")
	public String deleteProduct(@PathVariable int prod_id)
	{
		 ProductDTO product= productService.deleteProduct(prod_id);
		 return "Successfully deleted "+product.getProduct_name()+" from the database."; 
	}
	
	//Fetch all products
	@GetMapping(value="/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProducts()
	{
		return productService.getAllProduct();
	}
	
	//Fetch perfume by productId
	@GetMapping(value="/products/{prod_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO findById(@PathVariable int prod_id)
	{
		return productService.getProductById(prod_id);
	}
	
//	//Fetch perfumes by gender
//	@GetMapping(value="/perfumes/gender/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<ProductDTO> getAllPerfumeByGender(@PathVariable String category)
//	{
//		return productService.getPerfumeByGender(category);
//	}
//	
//	//seller increasing perfume stock
//	@PutMapping(value="seller/{productId}/{stock}")
//	public String sellerPerfumeStock(@PathVariable int productId, @PathVariable int stock)
//	{
//		return productService.sellerPerfumeStock(productId, stock);
//	}
//	
//	//buyer decreasing perfume stock
//	@PutMapping(value="buyer/{productId}/{stock}")
//	public String buyerPerfumeStock(@PathVariable int productId, @PathVariable int stock)
//	{
//		return productService.buyerPerfumeStock(productId, stock);
//	}
	
	//Delete the perfume based on the seller --> sub-routine for USER API
	@DeleteMapping(value="{seller_id}/products")
	public String deleteSellerPerfumes(@PathVariable int seller_id)
	{
		return productService.deleteSellerProducts(seller_id);
	}
}
