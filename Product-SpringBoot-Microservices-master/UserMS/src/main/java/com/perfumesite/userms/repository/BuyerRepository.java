package com.perfumesite.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfumesite.userms.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer,Integer>
{
	Boolean existsByPhoneNumber(Long phoneNumber);
	Boolean existsByEmailId(String emailId);
}
