package com.perfumesite.userms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.perfumesite.userms.entity.Favourite;
import com.perfumesite.userms.entity.FavouriteCompKey;

public interface FavouriteRepository extends JpaRepository<Favourite,FavouriteCompKey>
{
	@Query("DELETE FROM favourite WHERE buyerId=?1 and productId=?2 ")
	@Modifying
	@Transactional
	Integer delFromFavourite(Integer buyerId, Integer productId);
}
