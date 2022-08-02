package com.perfumesite.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfumesite.userms.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller,Integer>
{
	Boolean existsByPhoneNumber(long phoneNumber);
	Boolean existsByEmail(String email);
}
