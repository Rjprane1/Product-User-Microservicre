package com.productsite.productms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productsite.productms.dto.ProductDTO;
import com.productsite.productms.entity.Product;
import com.productsite.productms.repository.ProductRepository;

@Service
public class ProductService
{
	@Autowired
	ProductRepository productRepo;
	
	//Upload a new perfume
	public Product uploadProduct(ProductDTO productData)
	{
		Product productEntity = productData.toEntity(productData);
		productRepo.save(productEntity);
		return productEntity;
	}
	
	//Perfume delete 
	public ProductDTO deleteProduct(int prod_id)
	{
		ProductDTO product = this.getProductById(prod_id);
		productRepo.deleteById(prod_id);
		return product;
	}
	
	//Fetch all perfumes
	public List<ProductDTO> getAllProduct()
	{
		List<Product> allProductsEntity = productRepo.findAll();
		List<ProductDTO> allProductsDTO = new ArrayList<>();
		for(Product productEntity: allProductsEntity )
		{
			ProductDTO productDTO = ProductDTO.toDTO(productEntity);
			allProductsDTO.add(productDTO);
		}
		return allProductsDTO;
	}
	
	//Fetch single perfume based on productId
	public ProductDTO getProductById(int prod_id)
	{
		Optional<Product> optionalData = productRepo.findById(prod_id);
		Product productEntity = optionalData.get();
		return ProductDTO.toDTO(productEntity);
	}
	
//	//Fetch perfume by gender
//	public List<ProductDTO> getPerfumeByGender(String gender)
//	{
//		List<ProductDTO> allPerfumes= new ArrayList<>();
//		List<Product> allPerfumeEntity= perfumeRepo.findByGender(gender) ;
//		for(Product perfumeEntity:allPerfumeEntity)
//		{
//			ProductDTO perfume = ProductDTO.toDTO(perfumeEntity);
//			allPerfumes.add(perfume);
//		}
//		return allPerfumes;
//	}
//	
//	//Stock update when seller adds a stock
//	public String sellerPerfumeStock(int perfumeId, int stockValue)
//	{
//		Optional<Product> optional = perfumeRepo.findById(perfumeId);
//		Product perfumeEntity = optional.get();
//		if(stockValue<=0)
//		{
//			return "Please enter a valid amount";
//		}
//		else
//		{
//			stockValue=perfumeEntity.getStock()+stockValue;
//			perfumeEntity.setStock(stockValue);
//			perfumeRepo.save(perfumeEntity);
//			return perfumeEntity.getProductName()+" has been restocked to "+perfumeEntity.getStock()+" units.";
//		}
//	}
//	
//	//Stock update when buyer purchases a stock
//			public String buyerPerfumeStock(int perfumeId, int stockValue)
//			{
//				Optional<Product> optional = perfumeRepo.findById(perfumeId);
//				Product perfumeEntity = optional.get();
//				if (perfumeEntity.getStock().equals(0))
//				{
//					return perfumeEntity.getProductName()+" is out of stock";
//				}
//				else if (stockValue<0 || perfumeEntity.getStock()-stockValue<0)
//				{
//					return "Currently, "+perfumeEntity.getStock()+" "+perfumeEntity.getProductName()+" are available.";
//				}
//				else
//				{
//					int temp =stockValue;
//					stockValue = perfumeEntity.getStock()-stockValue;
//					perfumeEntity.setStock(stockValue);
//					perfumeRepo.save(perfumeEntity);
//					return "You bought "+temp+" "+perfumeEntity.getProductName()+" and currently "+perfumeEntity.getStock()+" are available.";
//				}
//			}

			public String deleteSellerProducts(int seller_id)
			{
				List<Product> productsEntity = productRepo.findBySeller(seller_id);
				for(Product productEntity: productsEntity)
				{
					productRepo.delete(productEntity);
				}
				return "Successfully deleted perfumes which were sold by "+seller_id;
			}

			
	
}
