package com.perfumesite.userms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.perfumesite.userms.entity.Cart;
import com.perfumesite.userms.entity.CartCompKey;

public interface CartRepository extends JpaRepository<Cart,CartCompKey>
{
	@Query("SELECT c from cart c WHERE buyerId=?1 AND productId=?2")
	Optional<Cart> findByIdQuery(Integer buyerId, Integer productId);
	
	
	@Query("DELETE FROM cart WHERE buyerId=?1 AND productId=?2")
	@Modifying
	@Transactional
	Integer delFromCart(int buyerId, int productId);
}
