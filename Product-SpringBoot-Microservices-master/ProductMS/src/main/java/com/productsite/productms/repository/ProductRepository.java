package com.productsite.productms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.productsite.productms.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>
{

List<Product> findBySeller(int seller_id);

	

}
