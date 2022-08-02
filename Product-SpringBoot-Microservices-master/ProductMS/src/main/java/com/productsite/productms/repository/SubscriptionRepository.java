package com.productsite.productms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productsite.productms.entity.CompKey;
import com.productsite.productms.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription,CompKey>
{

}
